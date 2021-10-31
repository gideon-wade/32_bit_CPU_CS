;buildInfoPackage: chisel3, version: 3.3.2, scalaVersion: 2.12.11, sbtVersion: 1.3.10
circuit ALU : 
  module ALU : 
    input clock : Clock
    input reset : UInt<1>
    output io : {flip xR : UInt<5>, flip xL : UInt<5>, flip sel : UInt<2>, xD : UInt<10>}
    
    io.xD <= UInt<1>("h00") @[ALU.scala 14:9]
    node _T = eq(UInt<1>("h00"), io.sel) @[Conditional.scala 37:30]
    when _T : @[Conditional.scala 40:58]
      node _T_1 = mul(io.xL, io.xR) @[ALU.scala 18:22]
      io.xD <= _T_1 @[ALU.scala 18:13]
      skip @[Conditional.scala 40:58]
    else : @[Conditional.scala 39:67]
      node _T_2 = eq(UInt<1>("h01"), io.sel) @[Conditional.scala 37:30]
      when _T_2 : @[Conditional.scala 39:67]
        node _T_3 = add(io.xL, UInt<1>("h01")) @[ALU.scala 21:22]
        node _T_4 = tail(_T_3, 1) @[ALU.scala 21:22]
        io.xD <= _T_4 @[ALU.scala 21:13]
        skip @[Conditional.scala 39:67]
      else : @[Conditional.scala 39:67]
        node _T_5 = eq(UInt<2>("h02"), io.sel) @[Conditional.scala 37:30]
        when _T_5 : @[Conditional.scala 39:67]
          node _T_6 = sub(io.xL, UInt<2>("h02")) @[ALU.scala 24:22]
          node _T_7 = tail(_T_6, 1) @[ALU.scala 24:22]
          io.xD <= _T_7 @[ALU.scala 24:13]
          skip @[Conditional.scala 39:67]
    
