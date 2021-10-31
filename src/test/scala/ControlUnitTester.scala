import chisel3.iotesters
import chisel3.iotesters.PeekPokeTester

class ControlUnitTester(dut: ControlUnit) extends PeekPokeTester(dut) {

  //Program Counter running for 5 clock cycles
  poke(dut.io.xD, 1)
  poke(dut.io.xL, 3)
  poke(dut.io.xR, 2)
  poke(dut.io.sel, 0)
  step(5)

  //Hold for 5 clock cycles
  poke(dut.io.xD, 1)
  poke(dut.io.xL, 3)
  poke(dut.io.xR, 2)
  poke(dut.io.sel, 1)
  step(5)

  //Hold for 5 clock cycles
  poke(dut.io.xD, 1)
  poke(dut.io.xL, 3)
  poke(dut.io.xR, 2)
  poke(dut.io.sel, 2)
  step(5)

  //Hold for 5 clock cycles
  poke(dut.io.xD, 1)
  poke(dut.io.xL, 3)
  poke(dut.io.xR, 2)
  poke(dut.io.sel, 3)
  step(5)

}
object ControlUnitTester {
  def main(args: Array[String]): Unit = {
    println("Testing ALU")
    iotesters.Driver.execute(
      Array("--generate-vcd-output", "on",
        "--target-dir", "generated",
        "--top-name", "ControlUnit"),
      () => new ControlUnit()) {
      c => new ControlUnitTester(c)
    }
  }
}
