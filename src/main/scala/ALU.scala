import chisel3._
import chisel3.util._

class ALU extends Module {
  val io = IO(new Bundle {
    //Define the module interface here (inputs/outputs)
    val xR = Input(UInt(32.W))
    val xL = Input(UInt(32.W))
    val ALUOp = Input(UInt(4.W))
    val xD = Output(UInt(32.W)) //ALU result
    val status = Output(Bool ())
    val c = Input(UInt(13.W))
  })

  //Implement this module here
  io.xD := 0.U
  io.status := false.B
  switch(io.ALUOp) {
    is("b0000".U){ //add
      io.xD := io.xL + io.xR
      io.status := false.B
    }
    is("b0001".U) { //mul
      io.xD := io.xL * io.xR
      io.status := false.B
    }
    is("b0010".U) { //addi
      io.xD := io.xL + io.c
      io.status := false.B
    }
    is("b0011".U) { //subi
      io.xD := io.xL - io.c
      io.status := false.B
    }
    is("b0100".U){ //load
      io.status := false.B
    }
    is("b0101".U){ //store
      io.status := false.B
    }
    is("b0110".U){ //beq
      when(io.xL === io.xR){
        io.status := true.B
      } .otherwise {
        io.status := false.B
      }
    }
    is("b0111".U){ //bneq
      when(io.xL =/= io.xR){
        io.status := true.B
      } .otherwise {
        io.status := false.B
      }
    }
    is("b1000".U){ //blt
      when(io.xL < io.xR){
        io.status := true.B
      } .otherwise {
        io.status := false.B
      }
    }
    is("b1001".U){ //branch jump
      io.status := true.B
    }
  }
}