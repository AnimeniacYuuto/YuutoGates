package yuuto.yuutogates.plugs

import net.minecraft.item.ItemStack
import yuuto.yuutogates.api.base.PlugGate
import yuuto.yuutogates.api.gates.IGateAND
import yuuto.yuutogates.api.material.GateMaterial

/**
 * Created by Yuuto on 9/25/2015.
 */
class PlugGateAND extends PlugGate with IGateAND{

  def this(material:GateMaterial)={
    this();
    setMaterial(material);
  }

  override def getDrop:ItemStack=null
}
