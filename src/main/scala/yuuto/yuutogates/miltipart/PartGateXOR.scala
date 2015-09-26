package yuuto.yuutogates.miltipart

import yuuto.yuutogates.api.base.PartGate
import yuuto.yuutogates.api.gates.{GateHelper, IGateXOR}
import yuuto.yuutogates.api.material.GateMaterial

/**
 * Created by Yuuto on 9/25/2015.
 */
class PartGateXOR extends PartGate with IGateXOR{

  def this(material:GateMaterial)={
    this();
    setMaterial(material);
  }

  override def getType:String=GateHelper.PART_ID_GATE_XOR;
}
