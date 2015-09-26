package yuuto.yuutogates.tile

import yuuto.yuutogates.api.base.TileGate
import yuuto.yuutogates.api.gates.IGateXOR
import yuuto.yuutogates.api.material.GateMaterial

/**
 * Created by Yuuto on 9/25/2015.
 */
class TileGateXOR extends TileGate with IGateXOR{

  def this(material:GateMaterial)={
    this();
    setMaterial(material);
  }

}
