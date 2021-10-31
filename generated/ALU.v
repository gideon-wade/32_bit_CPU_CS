module ALU(
  input        clock,
  input        reset,
  input  [4:0] io_xR,
  input  [4:0] io_xL,
  input  [1:0] io_sel,
  output [9:0] io_xD
);
  wire  _T = 2'h0 == io_sel; // @[Conditional.scala 37:30]
  wire [9:0] _T_1 = io_xL * io_xR; // @[ALU.scala 18:22]
  wire  _T_2 = 2'h1 == io_sel; // @[Conditional.scala 37:30]
  wire [4:0] _T_4 = io_xL + 5'h1; // @[ALU.scala 21:22]
  wire  _T_5 = 2'h2 == io_sel; // @[Conditional.scala 37:30]
  wire [4:0] _T_7 = io_xL - 5'h2; // @[ALU.scala 24:22]
  wire [4:0] _GEN_0 = _T_5 ? _T_7 : 5'h0; // @[Conditional.scala 39:67]
  wire [4:0] _GEN_1 = _T_2 ? _T_4 : _GEN_0; // @[Conditional.scala 39:67]
  assign io_xD = _T ? _T_1 : {{5'd0}, _GEN_1}; // @[ALU.scala 14:9 ALU.scala 18:13 ALU.scala 21:13 ALU.scala 24:13]
endmodule
