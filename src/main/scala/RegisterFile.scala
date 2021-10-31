import chisel3._
import chisel3.util._

class RegisterFile extends Module {
  val io = IO(new Bundle {
    //Define the module interface here (inputs/outputs)
    var aSel = Input(UInt (4.W))
    var bSel = Input(UInt (4.W))
    var writeData = Input(UInt (32.W))
    var writeSel = Input(UInt (4.W))
    var a = Output(UInt (32.W))
    var b = Output(UInt (32.W))
    val writeEnable = Input(Bool ())
  })

  //Implement this module here
  val regs = Reg(Vec(16, UInt(32.W)))
  regs(0) := 0.U
  when (io.writeEnable){
    when(io.writeSel > 0.U){
      regs(io.writeSel) := io.writeData
    }
  }
  io.a := regs(io.aSel)
  io.b := regs(io.bSel)
}