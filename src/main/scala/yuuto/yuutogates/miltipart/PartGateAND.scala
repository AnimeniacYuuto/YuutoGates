package yuuto.yuutogates.miltipart

import yuuto.yuutogates.api.base.PartGate
import yuuto.yuutogates.api.gates.{GateHelper, IGateAND}
import yuuto.yuutogates.api.material.GateMaterial

/**
 * Created by Yuuto on 9/25/2015.
 */
class PartGateAND extends PartGate with IGateAND{

  def this(material:GateMaterial)={
    this();
    setMaterial(material);
  }

  override def getType:String=GateHelper.PART_ID_GATE_AND;
}
