import chisel3._
import chisel3.util._
import scala.util.control.Breaks.break

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
  programCounter.io.stop:=controlUnit.io.done



  ////////////////////////////////////////////
  //Continue here with your connections
  ////////////////////////////////////////////

  controlUnit.io.opcode := programMemory.io.instructionRead(31, 28)
  registerFile.io.aSel := programMemory.io.instructionRead(27, 23)
  registerFile.io.bSel := programMemory.io.instructionRead(22, 18)
  registerFile.io.writeSel := programMemory.io.instructionRead(22,18) //S-EXTEND
  when(controlUnit.io.RegDst){
    registerFile.io.writeSel := programMemory.io.instructionRead(17, 8)
  }
  when(controlUnit.io.ALUSrc){
    alu.io.c := programMemory.io.instructionRead(12, 0) //S-EXTEND
  } .otherwise{
    alu.io.xR := registerFile.io.b
  }
  alu.io.xL := registerFile.io.a
  alu.io.ALUOp := controlUnit.io.ALUOp
  when(alu.io.status && controlUnit.io.Branch){
    programCounter.io.jump := true.B
    programCounter.io.programCounterJump := programMemory.io.address(13, 0)
  }
  when(controlUnit.io.MemtoReg){
    registerFile.io.writeData := dataMemory.io.address
  } .otherwise{
    registerFile.io.writeData := alu.io.xD
  }


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