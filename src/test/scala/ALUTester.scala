import chisel3.iotesters
import chisel3.iotesters.PeekPokeTester

class ALUTester(dut: ALU) extends PeekPokeTester(dut) {

  //Program Counter running for 5 clock cycles
  poke(dut.io.xD, 1)
  poke(dut.io.xL, 3)
  poke(dut.io.xR, 2)
  poke(dut.io.ALUOp, 0)
  step(5)

  //Hold for 5 clock cycles
  poke(dut.io.xD, 1)
  poke(dut.io.xL, 3)
  poke(dut.io.xR, 2)
  poke(dut.io.ALUOp, 1)
  step(5)

  //Hold for 5 clock cycles
  poke(dut.io.xD, 1)
  poke(dut.io.xL, 3)
  poke(dut.io.xR, 2)
  poke(dut.io.c,30)
  poke(dut.io.ALUOp, 2)
  step(5)

  //Hold for 5 clock cycles
  poke(dut.io.xD, 1)
  poke(dut.io.xL, 3)
  poke(dut.io.xR, 2)
  poke(dut.io.c,30)
  poke(dut.io.ALUOp, 3)
  step(5)

}

object ALUTester {
  def main(args: Array[String]): Unit = {
    println("Testing ALU")
    iotesters.Driver.execute(
      Array("--generate-vcd-output", "on",
        "--target-dir", "generated",
        "--top-name", "ALU"),
      () => new ALU()) {
      c => new ALUTester(c)
    }
  }
}