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
      "0000000001" +
      "00000" +
      "0000000000000").U(32.W), //addi: R1 = R0 + 0
    ("b0011" +
      "0000000010" +
      "00000" +
      "0000000001110").U(32.W), //addi: R2 = R0 + 20

    //loop1

    ("b1000" +
      "0000000001" +
      "00010" +
      "0000000000110").U(32.W), //blt: R1 < R2 -> 6
    ("b1001" +
      "0000000000000000000001000101").U(32.W), //goto: 69
    ("b0011" +
      "0000000011" +
      "00000" +
      "0000000000000").U(32.W), //addi: R3 = R0 + 0
    ("b0011" +
      "0000000100" +
      "00000" +
      "0000000001110").U(32.W), //addi: R4 = R0 + 20

    //loop2

    ("b1000" +
      "0000000011" +
      "00100" +
      "0000000001011").U(32.W), //blt: R3 < R4 -> 11
    ("b1001" +
      "0000000000000000000001000011").U(32.W), //goto: 67
    ("b0001" +
      "0000000101" +
      "00011" +
      "00010" +
      "0000" +
      "0000").U(32.W), //mul: R5 = R3 * R2 (shiftA = NULL /func = NULL)
    ("b0000" +
      "0000000110" +
      "00001" +
      "00101" +
      "0000" +
      "0000").U(32.W), //add: R6 = R1 + R5 (shiftA = NULL /func = NULL)
    ("b0110" +
      "0000000001" +
      "00000" +
      "0000000010011").U(32.W), //beq: R1 == R0 -> 19
    ("b0110" +
      "0000000011" +
      "00000" +
      "0000000010110").U(32.W), //beq: R3 == R0 -> 22
    ("b0011" +
      "0000000111" +
      "00000" +
      "0000000001101").U(32.W), //addi: R7 = R0 + 19
    ("b0110" +
      "0000000001" +
      "00111" +
      "0000000010110").U(32.W), //beq: R1 == R7 -> 25
    ("b0110" +
      "0000000011" +
      "00111" +
      "0000000011100").U(32.W), //beq: R3 == R7 -> 28
    ("b1001" +
      "0000000000000000000000011111").U(32.W), //goto: 31

    //if1

    ("b0101" +
      "0000000110" +
      "00110" +
      "0000000000000").U(32.W), //store: mem[R6] = 0
    ("b1001" +
      "0000000000000000000000001110").U(32.W), //goto: 14

    //if2

    ("b0101" +
      "0000000110" +
      "00110" +
      "0000000000000").U(32.W), //store: mem[R6] = 0
    ("b1001" +
      "0000000000000000000000001111").U(32.W), //goto: 15

    //if3

    ("b0101" +
      "0000000110" +
      "00110" +
      "0000000000000").U(32.W), //store: mem[R6] = 0
    ("b1001" +
      "0000000000000000000000010001").U(32.W), //goto: 17

    //if4

    ("b0101" +
      "0000000110" +
      "00110" +
      "0000000000000").U(32.W), //store: mem[R6] = 0
    ("b1001" +
      "0000000000000000000000010010").U(32.W), //goto: 18

    //if5

    ("b0100" +
      "0000001000" +
      "00110" +
      "0000000000000").U(32.W), //load: R8 = mem[R6]
    ("b0111" +
      "0000001000" +
      "00000" +
      "0000000100100").U(32.W), //bneq: R8 != R0 -> 36


    ("b0100" +
      "000000010" +
      "00001" +
      "00000011111111").U(32.W), //subi: R2 = R1 - 255 REACHPOINT
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