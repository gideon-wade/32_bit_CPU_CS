import chisel3._
import chisel3.util._

class ControlUnit extends Module {
  val io = IO(new Bundle {
    //Define the module interface here (inputs/outputs)
    val opcode = Input(UInt (4.W))
    val RegDst = Output(Bool())
    val Branch = Output(Bool())
    val MemRead = Output(Bool())
    val MemtoReg = Output(Bool())
    val ALUOp = Output(UInt (4.W))
    val MemWrite = Output(Bool())
    val ALUSrc = Output(Bool())
    val writeEnable = Output(Bool())
    val done = Output(Bool())
  })
  io.done := false.B
  io.RegDst := false.B
  io.Branch := false.B
  io.MemRead := false.B
  io.MemtoReg := false.B
  io.ALUOp := false.B
  io.MemWrite := false.B
  io.ALUSrc := false.B
  io.writeEnable := false.B
  //Implement this module here
  switch(io.opcode){
    is("b0000".U){ //add
      io.RegDst := true.B
      io.Branch := false.B
      io.MemRead := false.B
      io.MemtoReg := false.B
      io.ALUOp := "b0000".U
      io.MemWrite := false.B
      io.ALUSrc := false.B
      io.writeEnable := true.B
    }
    is("b0001".U){ //mul
      io.RegDst := true.B
      io.Branch := false.B
      io.MemRead := false.B
      io.MemtoReg := false.B
      io.ALUOp := "b0001".U
      io.MemWrite := false.B
      io.ALUSrc := false.B
      io.writeEnable := true.B
    }
    is("b0010".U){ //addi
      io.RegDst := true.B
      io.Branch := false.B
      io.MemRead := false.B
      io.MemtoReg := false.B
      io.ALUOp := "b0010".U
      io.MemWrite := false.B
      io.ALUSrc := true.B
      io.writeEnable := true.B
    }
    is("b0011".U){ //subi
      io.RegDst := true.B
      io.Branch := false.B
      io.MemRead := false.B
      io.MemtoReg := false.B
      io.ALUOp := "b0011".U
      io.MemWrite := false.B
      io.ALUSrc := true.B
      io.writeEnable := true.B
    }
    is("b0100".U){ //load
      io.RegDst := true.B
      io.Branch := false.B
      io.MemRead := true.B
      io.MemtoReg := true.B
      io.ALUOp := "b0100".U
      io.MemWrite := true.B
      io.ALUSrc := true.B
      io.writeEnable := true.B
    }
    is("b0101".U){ //store
      io.RegDst := true.B
      io.Branch := false.B
      io.MemRead := true.B
      io.MemtoReg := true.B
      io.ALUOp := "b0101".U
      io.MemWrite := true.B
      io.ALUSrc := true.B
      io.writeEnable := true.B
    }
    is("b0110".U){ //beq
      io.RegDst := false.B
      io.Branch := true.B
      io.MemRead := false.B
      io.MemtoReg := false.B
      io.ALUOp := "b0110".U
      io.MemWrite := false.B
      io.ALUSrc := true.B
      io.writeEnable := false.B
    }
    is("b0111".U){ //bneq
      io.RegDst := false.B
      io.Branch := true.B
      io.MemRead := false.B
      io.MemtoReg := false.B
      io.ALUOp := "b0111".U
      io.MemWrite := false.B
      io.ALUSrc := true.B
      io.writeEnable := false.B
    }
    is("b1000".U){ //blt
      io.RegDst := false.B
      io.Branch := true.B
      io.MemRead := false.B
      io.MemtoReg := false.B
      io.ALUOp := "b1000".U
      io.MemWrite := false.B
      io.ALUSrc := true.B
      io.writeEnable := false.B
    }
    is("b1001".U){ //goto
      io.RegDst := false.B
      io.Branch := true.B
      io.MemRead := false.B
      io.MemtoReg := false.B
      io.ALUOp := "b1001".U
      io.MemWrite := false.B
      io.ALUSrc := true.B
      io.writeEnable := false.B
    }
    is("b1111".U){ //end

    }
  }
}
