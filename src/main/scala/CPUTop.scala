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

  //Creating components
  val programCounter = Module(new ProgramCounter())
  val dataMemory = Module(new DataMemory())
  val programMemory = Module(new ProgramMemory())
  val registerFile = Module(new RegisterFile())
  val controlUnit = Module(new ControlUnit())
  val alu = Module(new ALU())

  //Connecting the modules
  //programCounter.io.run := io.run
  //programMemory.io.address := programCounter.io.programCounter

  ////////////////////////////////////////////
  //Continue here with your connections
  ////////////////////////////////////////////

  val program = Array(
    ("b0011" +
      "000000001" +
      "00000" +
      "00000000000000").U(32.W), //addi: R1 = R0 + 0
    ("b0011" +
      "000000010" +
      "00000" +
      "00000000001110").U(32.W), //addi: R2 = R0 + 20
    ("b1000" +
      "000000011" +
      "00010" +
      "00000000000110").U(32.W), //blt: R1 < R2 -> 6
    ("b0100" +
      "000000010" +
      "00001" +
      "00000011111111").U(32.W), //subi: R2 = R1 - 255
    ("b0000" +
      "000000011" +
      "00001" +
      "00010" +
      "00000" +
      "0000").U(32.W) //add: R3 = R1 + R2
  )

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