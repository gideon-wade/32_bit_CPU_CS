;buildInfoPackage: chisel3, version: 3.3.2, scalaVersion: 2.12.11, sbtVersion: 1.3.10
circuit RegisterFile : 
  module RegisterFile : 
    input clock : Clock
    input reset : UInt<1>
    output io : {flip aSel : UInt<4>, flip bSel : UInt<4>, flip writeData : UInt<32>, flip writeSel : UInt<4>, a : UInt<32>, b : UInt<32>, flip writeEnable : UInt<1>}
    
    reg regs : UInt<32>[16], clock @[RegisterFile.scala 17:17]
    regs[0] <= UInt<1>("h00") @[RegisterFile.scala 18:11]
    when io.writeEnable : @[RegisterFile.scala 19:24]
      node _T = gt(io.writeSel, UInt<1>("h00")) @[RegisterFile.scala 20:22]
      when _T : @[RegisterFile.scala 20:28]
        regs[io.writeSel] <= io.writeData @[RegisterFile.scala 21:25]
        skip @[RegisterFile.scala 20:28]
      skip @[RegisterFile.scala 19:24]
    io.a <= regs[io.aSel] @[RegisterFile.scala 24:8]
    io.b <= regs[io.bSel] @[RegisterFile.scala 25:8]
    
