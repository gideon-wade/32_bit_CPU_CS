;buildInfoPackage: chisel3, version: 3.3.2, scalaVersion: 2.12.11, sbtVersion: 1.3.10
circuit ProgramCounter : 
  module ProgramCounter : 
    input clock : Clock
    input reset : UInt<1>
    output io : {flip stop : UInt<1>, flip jump : UInt<1>, flip run : UInt<1>, flip programCounterJump : UInt<16>, programCounter : UInt<16>}
    
    reg programCounterNext : UInt<16>, clock with : (reset => (reset, UInt<16>("h00"))) @[ProgramCounter.scala 14:35]
    node _T = eq(io.run, UInt<1>("h00")) @[ProgramCounter.scala 15:9]
    when _T : @[ProgramCounter.scala 15:17]
      programCounterNext <= io.programCounter @[ProgramCounter.scala 16:24]
      skip @[ProgramCounter.scala 15:17]
    else : @[ProgramCounter.scala 17:23]
      when io.stop : @[ProgramCounter.scala 17:23]
        programCounterNext <= io.programCounter @[ProgramCounter.scala 18:24]
        skip @[ProgramCounter.scala 17:23]
      else : @[ProgramCounter.scala 19:46]
        node _T_1 = eq(io.stop, UInt<1>("h00")) @[ProgramCounter.scala 19:25]
        node _T_2 = and(io.run, _T_1) @[ProgramCounter.scala 19:22]
        node _T_3 = eq(io.jump, UInt<1>("h00")) @[ProgramCounter.scala 19:37]
        node _T_4 = and(_T_2, _T_3) @[ProgramCounter.scala 19:34]
        when _T_4 : @[ProgramCounter.scala 19:46]
          node _T_5 = add(io.programCounter, UInt<1>("h01")) @[ProgramCounter.scala 20:45]
          node _T_6 = tail(_T_5, 1) @[ProgramCounter.scala 20:45]
          programCounterNext <= _T_6 @[ProgramCounter.scala 20:24]
          skip @[ProgramCounter.scala 19:46]
        else : @[ProgramCounter.scala 21:45]
          node _T_7 = eq(io.stop, UInt<1>("h00")) @[ProgramCounter.scala 21:25]
          node _T_8 = and(io.run, _T_7) @[ProgramCounter.scala 21:22]
          node _T_9 = and(_T_8, io.jump) @[ProgramCounter.scala 21:34]
          when _T_9 : @[ProgramCounter.scala 21:45]
            programCounterNext <= io.programCounterJump @[ProgramCounter.scala 22:24]
            skip @[ProgramCounter.scala 21:45]
    io.programCounter <= programCounterNext @[ProgramCounter.scala 24:21]
    
