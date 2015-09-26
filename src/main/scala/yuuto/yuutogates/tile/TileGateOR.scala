package yuuto.yuutogates.tile

import yuuto.yuutogates.api.base.TileGate
import yuuto.yuutogates.api.gates.IGateOR
import yuuto.yuutogates.api.material.GateMaterial

/**
 * Created by Yuuto on 9/25/2015.
 */
class TileGateOR extends TileGate with IGateOR{

  def this(material:GateMaterial)={
    this();
    setMaterial(material);
  }

}
