module ProgramCounter(
  input         clock,
  input         reset,
  input         io_stop,
  input         io_jump,
  input         io_run,
  input  [15:0] io_programCounterJump,
  output [15:0] io_programCounter
);
`ifdef RANDOMIZE_REG_INIT
  reg [31:0] _RAND_0;
`endif // RANDOMIZE_REG_INIT
  reg [15:0] programCounterNext; // @[ProgramCounter.scala 14:35]
  wire  _T = ~io_run; // @[ProgramCounter.scala 15:9]
  wire  _T_1 = ~io_stop; // @[ProgramCounter.scala 19:25]
  wire  _T_2 = io_run & _T_1; // @[ProgramCounter.scala 19:22]
  wire  _T_3 = ~io_jump; // @[ProgramCounter.scala 19:37]
  wire  _T_4 = _T_2 & _T_3; // @[ProgramCounter.scala 19:34]
  wire [15:0] _T_6 = io_programCounter + 16'h1; // @[ProgramCounter.scala 20:45]
  wire  _T_9 = _T_2 & io_jump; // @[ProgramCounter.scala 21:34]
  assign io_programCounter = programCounterNext; // @[ProgramCounter.scala 24:21]
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
  programCounterNext = _RAND_0[15:0];
`endif // RANDOMIZE_REG_INIT
  `endif // RANDOMIZE
end // initial
`ifdef FIRRTL_AFTER_INITIAL
`FIRRTL_AFTER_INITIAL
`endif
`endif // SYNTHESIS
  always @(posedge clock) begin
    if (reset) begin
      programCounterNext <= 16'h0;
    end else if (_T) begin
      programCounterNext <= io_programCounter;
    end else if (io_stop) begin
      programCounterNext <= io_programCounter;
    end else if (_T_4) begin
      programCounterNext <= _T_6;
    end else if (_T_9) begin
      programCounterNext <= io_programCounterJump;
    end
  end
endmodule
