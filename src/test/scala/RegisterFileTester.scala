import chisel3.iotesters
import chisel3.iotesters.PeekPokeTester

class RegisterFileTester(dut: RegisterFile) extends PeekPokeTester(dut) {

  //Program Counter running for 5 clock cycles
  poke(dut.io.aSel, 5)
  poke(dut.io.bSel, 1)
  poke(dut.io.writeData, 1)
  poke(dut.io.writeSel, 3)
  poke(dut.io.writeEnable, 1)
  //step(5)

  //Hold for 5 clock cycles
  //poke(dut.io.xD, 1)
  //poke(dut.io.xL, 1)
  //poke(dut.io.xR, 1)
  //poke(dut.io.sel, 1)
  //step(5)

  //Hold for 5 clock cycles
  //poke(dut.io.xD, 1)
  //poke(dut.io.xL, 1)
  //poke(dut.io.xR, 1)
  //poke(dut.io.sel, 2)
  //step(5)


}

object RegisterFileTester {
  def main(args: Array[String]): Unit = {
    println("Testing RegisterFile")
    iotesters.Driver.execute(
      Array("--generate-vcd-output", "on",
        "--target-dir", "generated",
        "--top-name", "RegisterFile"),
      () => new RegisterFile()) {
      c => new RegisterFileTester(c)
    }
  }
}