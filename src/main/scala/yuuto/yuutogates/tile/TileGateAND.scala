package yuuto.yuutogates.tile

import yuuto.yuutogates.api.base.TileGate
import yuuto.yuutogates.api.gates.IGateAND
import yuuto.yuutogates.api.material.GateMaterial

/**
 * Created by Yuuto on 9/25/2015.
 */
class TileGateAND extends TileGate with IGateAND {

  def this(material:GateMaterial)={
    this();
    setMaterial(material);
  }

}
