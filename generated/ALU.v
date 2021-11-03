module ALU(
  input         clock,
  input         reset,
  input  [31:0] io_xR,
  input  [31:0] io_xL,
  input  [3:0]  io_ALUOp,
  output [31:0] io_xD,
  output        io_status,
  input  [13:0] io_c
);
  wire  _T = 4'h0 == io_ALUOp; // @[Conditional.scala 37:30]
  wire [31:0] _T_2 = io_xL + io_xR; // @[ALU.scala 20:22]
  wire  _T_3 = 4'h1 == io_ALUOp; // @[Conditional.scala 37:30]
  wire [63:0] _T_4 = io_xL * io_xR; // @[ALU.scala 24:22]
  wire  _T_5 = 4'h2 == io_ALUOp; // @[Conditional.scala 37:30]
  wire [31:0] _GEN_17 = {{18'd0}, io_c}; // @[ALU.scala 28:22]
  wire [31:0] _T_7 = io_xL + _GEN_17; // @[ALU.scala 28:22]
  wire  _T_8 = 4'h3 == io_ALUOp; // @[Conditional.scala 37:30]
  wire [31:0] _T_10 = io_xL - _GEN_17; // @[ALU.scala 32:22]
  wire  _T_11 = 4'h4 == io_ALUOp; // @[Conditional.scala 37:30]
  wire  _T_12 = 4'h5 == io_ALUOp; // @[Conditional.scala 37:30]
  wire  _T_13 = 4'h6 == io_ALUOp; // @[Conditional.scala 37:30]
  wire  _T_14 = io_xL == io_xR; // @[ALU.scala 42:18]
  wire  _T_15 = 4'h7 == io_ALUOp; // @[Conditional.scala 37:30]
  wire  _T_16 = io_xL != io_xR; // @[ALU.scala 49:18]
  wire  _T_17 = 4'h8 == io_ALUOp; // @[Conditional.scala 37:30]
  wire  _T_18 = io_xL < io_xR; // @[ALU.scala 56:18]
  wire  _T_19 = 4'h9 == io_ALUOp; // @[Conditional.scala 37:30]
  wire  _GEN_4 = _T_17 ? _T_18 : _T_19; // @[Conditional.scala 39:67]
  wire  _GEN_5 = _T_15 ? _T_16 : _GEN_4; // @[Conditional.scala 39:67]
  wire  _GEN_6 = _T_13 ? _T_14 : _GEN_5; // @[Conditional.scala 39:67]
  wire  _GEN_7 = _T_12 ? 1'h0 : _GEN_6; // @[Conditional.scala 39:67]
  wire  _GEN_8 = _T_11 ? 1'h0 : _GEN_7; // @[Conditional.scala 39:67]
  wire [31:0] _GEN_9 = _T_8 ? _T_10 : 32'h0; // @[Conditional.scala 39:67]
  wire  _GEN_10 = _T_8 ? 1'h0 : _GEN_8; // @[Conditional.scala 39:67]
  wire [31:0] _GEN_11 = _T_5 ? _T_7 : _GEN_9; // @[Conditional.scala 39:67]
  wire  _GEN_12 = _T_5 ? 1'h0 : _GEN_10; // @[Conditional.scala 39:67]
  wire [63:0] _GEN_13 = _T_3 ? _T_4 : {{32'd0}, _GEN_11}; // @[Conditional.scala 39:67]
  wire  _GEN_14 = _T_3 ? 1'h0 : _GEN_12; // @[Conditional.scala 39:67]
  wire [63:0] _GEN_15 = _T ? {{32'd0}, _T_2} : _GEN_13; // @[Conditional.scala 40:58]
  assign io_xD = _GEN_15[31:0]; // @[ALU.scala 16:9 ALU.scala 20:13 ALU.scala 24:13 ALU.scala 28:13 ALU.scala 32:13]
  assign io_status = _T ? 1'h0 : _GEN_14; // @[ALU.scala 17:13 ALU.scala 21:17 ALU.scala 25:17 ALU.scala 29:17 ALU.scala 33:17 ALU.scala 36:17 ALU.scala 39:17 ALU.scala 43:19 ALU.scala 45:19 ALU.scala 50:19 ALU.scala 52:19 ALU.scala 57:19 ALU.scala 59:19 ALU.scala 63:17]
endmodule
