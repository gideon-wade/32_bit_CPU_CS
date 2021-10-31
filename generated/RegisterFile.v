module RegisterFile(
  input         clock,
  input         reset,
  input  [3:0]  io_aSel,
  input  [3:0]  io_bSel,
  input  [31:0] io_writeData,
  input  [3:0]  io_writeSel,
  output [31:0] io_a,
  output [31:0] io_b,
  input         io_writeEnable
);
`ifdef RANDOMIZE_REG_INIT
  reg [31:0] _RAND_0;
  reg [31:0] _RAND_1;
  reg [31:0] _RAND_2;
  reg [31:0] _RAND_3;
  reg [31:0] _RAND_4;
  reg [31:0] _RAND_5;
  reg [31:0] _RAND_6;
  reg [31:0] _RAND_7;
  reg [31:0] _RAND_8;
  reg [31:0] _RAND_9;
  reg [31:0] _RAND_10;
  reg [31:0] _RAND_11;
  reg [31:0] _RAND_12;
  reg [31:0] _RAND_13;
  reg [31:0] _RAND_14;
  reg [31:0] _RAND_15;
`endif // RANDOMIZE_REG_INIT
  reg [31:0] regs_0; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_1; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_2; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_3; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_4; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_5; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_6; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_7; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_8; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_9; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_10; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_11; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_12; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_13; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_14; // @[RegisterFile.scala 17:17]
  reg [31:0] regs_15; // @[RegisterFile.scala 17:17]
  wire  _T = io_writeSel > 4'h0; // @[RegisterFile.scala 20:22]
  wire [31:0] _GEN_49 = 4'h1 == io_aSel ? regs_1 : regs_0; // @[RegisterFile.scala 24:8]
  wire [31:0] _GEN_50 = 4'h2 == io_aSel ? regs_2 : _GEN_49; // @[RegisterFile.scala 24:8]
  wire [31:0] _GEN_51 = 4'h3 == io_aSel ? regs_3 : _GEN_50; // @[RegisterFile.scala 24:8]
  wire [31:0] _GEN_52 = 4'h4 == io_aSel ? regs_4 : _GEN_51; // @[RegisterFile.scala 24:8]
  wire [31:0] _GEN_53 = 4'h5 == io_aSel ? regs_5 : _GEN_52; // @[RegisterFile.scala 24:8]
  wire [31:0] _GEN_54 = 4'h6 == io_aSel ? regs_6 : _GEN_53; // @[RegisterFile.scala 24:8]
  wire [31:0] _GEN_55 = 4'h7 == io_aSel ? regs_7 : _GEN_54; // @[RegisterFile.scala 24:8]
  wire [31:0] _GEN_56 = 4'h8 == io_aSel ? regs_8 : _GEN_55; // @[RegisterFile.scala 24:8]
  wire [31:0] _GEN_57 = 4'h9 == io_aSel ? regs_9 : _GEN_56; // @[RegisterFile.scala 24:8]
  wire [31:0] _GEN_58 = 4'ha == io_aSel ? regs_10 : _GEN_57; // @[RegisterFile.scala 24:8]
  wire [31:0] _GEN_59 = 4'hb == io_aSel ? regs_11 : _GEN_58; // @[RegisterFile.scala 24:8]
  wire [31:0] _GEN_60 = 4'hc == io_aSel ? regs_12 : _GEN_59; // @[RegisterFile.scala 24:8]
  wire [31:0] _GEN_61 = 4'hd == io_aSel ? regs_13 : _GEN_60; // @[RegisterFile.scala 24:8]
  wire [31:0] _GEN_62 = 4'he == io_aSel ? regs_14 : _GEN_61; // @[RegisterFile.scala 24:8]
  wire [31:0] _GEN_65 = 4'h1 == io_bSel ? regs_1 : regs_0; // @[RegisterFile.scala 25:8]
  wire [31:0] _GEN_66 = 4'h2 == io_bSel ? regs_2 : _GEN_65; // @[RegisterFile.scala 25:8]
  wire [31:0] _GEN_67 = 4'h3 == io_bSel ? regs_3 : _GEN_66; // @[RegisterFile.scala 25:8]
  wire [31:0] _GEN_68 = 4'h4 == io_bSel ? regs_4 : _GEN_67; // @[RegisterFile.scala 25:8]
  wire [31:0] _GEN_69 = 4'h5 == io_bSel ? regs_5 : _GEN_68; // @[RegisterFile.scala 25:8]
  wire [31:0] _GEN_70 = 4'h6 == io_bSel ? regs_6 : _GEN_69; // @[RegisterFile.scala 25:8]
  wire [31:0] _GEN_71 = 4'h7 == io_bSel ? regs_7 : _GEN_70; // @[RegisterFile.scala 25:8]
  wire [31:0] _GEN_72 = 4'h8 == io_bSel ? regs_8 : _GEN_71; // @[RegisterFile.scala 25:8]
  wire [31:0] _GEN_73 = 4'h9 == io_bSel ? regs_9 : _GEN_72; // @[RegisterFile.scala 25:8]
  wire [31:0] _GEN_74 = 4'ha == io_bSel ? regs_10 : _GEN_73; // @[RegisterFile.scala 25:8]
  wire [31:0] _GEN_75 = 4'hb == io_bSel ? regs_11 : _GEN_74; // @[RegisterFile.scala 25:8]
  wire [31:0] _GEN_76 = 4'hc == io_bSel ? regs_12 : _GEN_75; // @[RegisterFile.scala 25:8]
  wire [31:0] _GEN_77 = 4'hd == io_bSel ? regs_13 : _GEN_76; // @[RegisterFile.scala 25:8]
  wire [31:0] _GEN_78 = 4'he == io_bSel ? regs_14 : _GEN_77; // @[RegisterFile.scala 25:8]
  assign io_a = 4'hf == io_aSel ? regs_15 : _GEN_62; // @[RegisterFile.scala 24:8]
  assign io_b = 4'hf == io_bSel ? regs_15 : _GEN_78; // @[RegisterFile.scala 25:8]
`ifdef RANDOMIZE_GARBAGE_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_INVALID_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_REG_INIT
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_MEM_INIT
`define RANDOMIZE
`endif
`ifndef RANDOM
`define RANDOM $random
`endif
`ifdef RANDOMIZE_MEM_INIT
  integer initvar;
`endif
`ifndef SYNTHESIS
`ifdef FIRRTL_BEFORE_INITIAL
`FIRRTL_BEFORE_INITIAL
`endif
initial begin
  `ifdef RANDOMIZE
    `ifdef INIT_RANDOM
      `INIT_RANDOM
    `endif
    `ifndef VERILATOR
      `ifdef RANDOMIZE_DELAY
        #`RANDOMIZE_DELAY begin end
      `else
        #0.002 begin end
      `endif
    `endif
`ifdef RANDOMIZE_REG_INIT
  _RAND_0 = {1{`RANDOM}};
  regs_0 = _RAND_0[31:0];
  _RAND_1 = {1{`RANDOM}};
  regs_1 = _RAND_1[31:0];
  _RAND_2 = {1{`RANDOM}};
  regs_2 = _RAND_2[31:0];
  _RAND_3 = {1{`RANDOM}};
  regs_3 = _RAND_3[31:0];
  _RAND_4 = {1{`RANDOM}};
  regs_4 = _RAND_4[31:0];
  _RAND_5 = {1{`RANDOM}};
  regs_5 = _RAND_5[31:0];
  _RAND_6 = {1{`RANDOM}};
  regs_6 = _RAND_6[31:0];
  _RAND_7 = {1{`RANDOM}};
  regs_7 = _RAND_7[31:0];
  _RAND_8 = {1{`RANDOM}};
  regs_8 = _RAND_8[31:0];
  _RAND_9 = {1{`RANDOM}};
  regs_9 = _RAND_9[31:0];
  _RAND_10 = {1{`RANDOM}};
  regs_10 = _RAND_10[31:0];
  _RAND_11 = {1{`RANDOM}};
  regs_11 = _RAND_11[31:0];
  _RAND_12 = {1{`RANDOM}};
  regs_12 = _RAND_12[31:0];
  _RAND_13 = {1{`RANDOM}};
  regs_13 = _RAND_13[31:0];
  _RAND_14 = {1{`RANDOM}};
  regs_14 = _RAND_14[31:0];
  _RAND_15 = {1{`RANDOM}};
  regs_15 = _RAND_15[31:0];
`endif // RANDOMIZE_REG_INIT
  `endif // RANDOMIZE
end // initial
`ifdef FIRRTL_AFTER_INITIAL
`FIRRTL_AFTER_INITIAL
`endif
`endif // SYNTHESIS
  always @(posedge clock) begin
    if (io_writeEnable) begin
      if (_T) begin
        if (4'h0 == io_writeSel) begin
          regs_0 <= io_writeData;
        end else begin
          regs_0 <= 32'h0;
        end
      end else begin
        regs_0 <= 32'h0;
      end
    end else begin
      regs_0 <= 32'h0;
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'h1 == io_writeSel) begin
          regs_1 <= io_writeData;
        end
      end
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'h2 == io_writeSel) begin
          regs_2 <= io_writeData;
        end
      end
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'h3 == io_writeSel) begin
          regs_3 <= io_writeData;
        end
      end
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'h4 == io_writeSel) begin
          regs_4 <= io_writeData;
        end
      end
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'h5 == io_writeSel) begin
          regs_5 <= io_writeData;
        end
      end
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'h6 == io_writeSel) begin
          regs_6 <= io_writeData;
        end
      end
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'h7 == io_writeSel) begin
          regs_7 <= io_writeData;
        end
      end
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'h8 == io_writeSel) begin
          regs_8 <= io_writeData;
        end
      end
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'h9 == io_writeSel) begin
          regs_9 <= io_writeData;
        end
      end
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'ha == io_writeSel) begin
          regs_10 <= io_writeData;
        end
      end
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'hb == io_writeSel) begin
          regs_11 <= io_writeData;
        end
      end
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'hc == io_writeSel) begin
          regs_12 <= io_writeData;
        end
      end
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'hd == io_writeSel) begin
          regs_13 <= io_writeData;
        end
      end
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'he == io_writeSel) begin
          regs_14 <= io_writeData;
        end
      end
    end
    if (io_writeEnable) begin
      if (_T) begin
        if (4'hf == io_writeSel) begin
          regs_15 <= io_writeData;
        end
      end
    end
  end
endmodule
