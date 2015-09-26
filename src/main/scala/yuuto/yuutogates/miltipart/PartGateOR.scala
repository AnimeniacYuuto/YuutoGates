package yuuto.yuutogates.miltipart

import yuuto.yuutogates.api.base.PartGate
import yuuto.yuutogates.api.gates.{GateHelper, IGateOR}
import yuuto.yuutogates.api.material.GateMaterial

/**
 * Created by Yuuto on 9/25/2015.
 */
class PartGateOR extends PartGate with IGateOR{

  def this(material:GateMaterial)={
    this();
    setMaterial(material);
  }

  override def getType:String=GateHelper.PART_ID_GATE_OR;
}
