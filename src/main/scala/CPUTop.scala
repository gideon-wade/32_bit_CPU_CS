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
    ("b0101" +
      "0000001000" +
      "01000" +
      "0000000000000").U(32.W), //store: mem[R8] = 0
    ("b1001" +
      "0000000000000000000001000010").U(32.W), //goto: 66

    ////else1

    //cond1

    ("b0011" +
      "000001001" +
      "00001" +
      "00000000000001").U(32.W), //subi: R9 = R1 - 1
    ("b0001" +
      "0000001010" +
      "01001" +
      "00101" +
      "0000" +
      "0000").U(32.W), //mul: R10 = R9 * R5 (shiftA = NULL /func = NULL)
    ("b0100" +
      "0000001011" +
      "01010" +
      "0000000000000").U(32.W), //load: R11 = mem[R10]
    ("b0110" +
      "0000001011" +
      "01010" +
      "0000000111101").U(32.W), //beq: R11 == R0 -> 61

    //cond2

    ("b0010" +
      "000001100" +
      "00001" +
      "00000000000001").U(32.W), //addi: R12 = R1 + 1
    ("b0001" +
      "0000001101" +
      "01100" +
      "00101" +
      "0000" +
      "0000").U(32.W), //mul: R13 = R12 * R5 (shiftA = NULL /func = NULL)
    ("b0100" +
      "0000001110" +
      "01101" +
      "0000000000000").U(32.W), //load: R14 = mem[R13]
    ("b0110" +
      "0000001110" +
      "00000" +
      "0000000111101").U(32.W), //beq: R11 == R0 -> 61

    //cond3

    ("b0011" +
      "000001111" +
      "00011" +
      "00000000000001").U(32.W), //subi: R15 = R3 - 1
    ("b0001" +
      "0000010000" +
      "01111" +
      "00100" +
      "0000" +
      "0000").U(32.W), //mul: R16 = R15 * R4 (shiftA = NULL /func = NULL)
    ("b0001" +
      "0000010001" +
      "00001" +
      "10000" +
      "0000" +
      "0000").U(32.W), //mul: R17 = R1 * R16 (shiftA = NULL /func = NULL)
    ("b0100" +
      "0000010010" +
      "10001" +
      "0000000000000").U(32.W), //load: R18 = mem[R17]
    ("b0110" +
      "0000010010" +
      "00000" +
      "0000000111101").U(32.W), //beq: R11 == R0 -> 61

    //cond4

    ("b0010" +
      "0000010011" +
      "00011" +
      "00000000000001").U(32.W), //addi: R19 = R3 + 1
    ("b0001" +
      "0000010100" +
      "10011" +
      "00100" +
      "0000" +
      "0000").U(32.W), //mul: R20 = R19 * R4 (shiftA = NULL /func = NULL)
    ("b0001" +
      "0000010101" +
      "00001" +
      "10101" +
      "0000" +
      "0000").U(32.W), //mul: R21 = R1 * R20 (shiftA = NULL /func = NULL)
    ("b0100" +
      "0000010110" +
      "10101" +
      "0000000000000").U(32.W), //load: R22 = mem[R21]
    ("b0110" +
      "0000010010" +
      "00000" +
      "0000000111101").U(32.W), //beq: R22 == R0 -> 61
    // \end cond4

    ("b1001" +
      "0000000000000000000000111110").U(32.W), //goto: 62
    ("b0101" +
      "0000000110" +
      "00000" +
      "0000000000000").U(32.W), //store: mem[R6] = 0
    ("b1001" +
      "0000000000000000000001000010").U(32.W), //goto: 66

    //else2

    ("b0010" +
      "0000010111" +
      "00000" +
      "00000011111111").U(32.W), //addi: R23 = R0 + 255
    ("b0101" +
      "0000000110" +
      "10111" +
      "0000000000000").U(32.W), //store: mem[R6] = R23
    // \end else2

    ("b0010" +
      "0000000011" +
      "00011" +
      "00000000000001").U(32.W), //addi: R3 = R3 + 1
    ("b1001" +
      "0000000000000000000000001000").U(32.W), //goto: 8
    ("b0010" +
      "0000000001" +
      "00001" +
      "00000000000001").U(32.W), //addi: R1 = R1 + 1
    ("b1001" +
      "0000000000000000000000001000").U(32.W), //goto: 3
    "b00000000000000000000000000000000".U(32.W) //END
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