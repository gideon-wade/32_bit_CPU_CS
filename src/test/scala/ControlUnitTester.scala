import chisel3.iotesters
import chisel3.iotesters.PeekPokeTester

class ControlUnitTester(dut: ControlUnit) extends PeekPokeTester(dut) {

  //Program Counter running for 5 clock cycles
  poke(dut.io.opcode, 1)
  step(5)

  //Hold for 5 clock cycles
  poke(dut.io.opcode, 2)
  step(5)

  poke(dut.io.opcode, 3)
  step(5)

  poke(dut.io.opcode, 4)
  step(5)

}
object ControlUnitTester {
  def main(args: Array[String]): Unit = {
    println("Testing ControlUnit")
    iotesters.Driver.execute(
      Array("--generate-vcd-output", "on",
        "--target-dir", "generated",
        "--top-name", "ControlUnit"),
      () => new ControlUnit()) {
      c => new ControlUnitTester(c)
    }
  }
}
