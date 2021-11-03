import chisel3._
import chisel3.util._

class CPUTop extends Module {
  val io = IO(new Bundle {
    val done = Output(Bool ())
    val run = Input(Bool ())
    //This signals are used by the tester for loading and dumping the memory content, do not touch
    val testerDataMemEnable = Input(Bool ())
    val testerDataMemAddress = Input(UInt (16.W))
    val testerDataMemDataRead = Output(UInt (32.W))
    val testerDataMemWriteEnable = Input(Bool ())
    val testerDataMemDataWrite = Input(UInt (32.W))
    //This signals are used by the tester for loading and dumping the memory content, do not touch
    val testerProgMemEnable = Input(Bool ())
    val testerProgMemAddress = Input(UInt (16.W))
    val testerProgMemDataRead = Output(UInt (32.W))
    val testerProgMemWriteEnable = Input(Bool ())
    val testerProgMemDataWrite = Input(UInt (32.W))
  })


  io.testerProgMemDataRead := 0.U

  //Creating components
  val programCounter = Module(new ProgramCounter())
  val dataMemory = Module(new DataMemory())
  val programMemory = Module(new ProgramMemory())
  val registerFile = Module(new RegisterFile())
  val controlUnit = Module(new ControlUnit())
  val alu = Module(new ALU())

  //Connecting the modules

  programCounter.io.run := io.run
  programMemory.io.address := programCounter.io.programCounter
  when(!io.done){
    controlUnit.io.opcode := programMemory.io.address(32, 28)
    registerFile.io.aSel := programMemory.io.address(28, 23)
    registerFile.io.bSel := programMemory.io.address(23, 18)
    when(controlUnit.io.RegDst && controlUnit.io.writeEnable){
      registerFile.io.writeSel := programMemory.io.address(18, 8)
    } .otherwise{
      registerFile.io.writeSel := programMemory.io.address(23,18) //S-EXTEND
    }

    when(controlUnit.io.ALUSrc){
      alu.io.c := programMemory.io.address(13, 0) //S-EXTEND
    } .otherwise{
      alu.io.xR := registerFile.io.b
    }
    alu.io.xL := registerFile.io.a
    alu.io.ALUOp := controlUnit.io.ALUOp
    when(controlUnit.io.MemWrite && controlUnit.io.MemRead){
      dataMemory.io.address := alu.io.xD
    }
    when(controlUnit.io.Branch && alu.io.status){
      programCounter.io.jump := true.B
    }

  }






  ////////////////////////////////////////////
  //Continue here with your connections
  ////////////////////////////////////////////

  //val program =

  //This signals are used by the tester for loading the program to the program memory, do not touch
  programMemory.io.testerAddress := io.testerProgMemAddress
  io.testerProgMemDataRead := programMemory.io.testerDataRead
  programMemory.io.testerDataWrite := io.testerProgMemDataWrite
  programMemory.io.testerEnable := io.testerProgMemEnable
  programMemory.io.testerWriteEnable := io.testerProgMemWriteEnable
  //This signals are used by the tester for loading and dumping the data memory content, do not touch
  dataMemory.io.testerAddress := io.testerDataMemAddress
  io.testerDataMemDataRead := dataMemory.io.testerDataRead
  dataMemory.io.testerDataWrite := io.testerDataMemDataWrite
  dataMemory.io.testerEnable := io.testerDataMemEnable
  dataMemory.io.testerWriteEnable := io.testerDataMemWriteEnable
}