package yuuto.yuutogates.plugs

import net.minecraft.item.ItemStack
import yuuto.yuutogates.api.base.PlugGate
import yuuto.yuutogates.api.gates.IGateXOR
import yuuto.yuutogates.api.material.GateMaterial

/**
 * Created by Yuuto on 9/25/2015.
 */
class PlugGateXOR extends PlugGate with IGateXOR{

  def this(material:GateMaterial)={
    this();
    setMaterial(material);
  }
  override def getDrop:ItemStack=null;
}
