package yuuto.yuutogates

import cpw.mods.fml.common.{SidedProxy, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import yuuto.yuutogates.proxy.ProxyCommon
import yuuto.yuutogates.ref.ReferenceYG

/**
 * Created by Yuuto on 9/25/2015.
 */
@Mod(modid = ReferenceYG.MOD_ID, name = ReferenceYG.MOD_NAME, version = ReferenceYG.VERSION, dependencies = "after:yuutofacades", modLanguage = "scala")
object YuutoGates {

  val tabGates:CreativeTabs=new CreativeTabs("yuutogates:gates") {
    override def getTabIconItem:Item=Item.getItemFromBlock(Blocks.redstone_block);
  }

  @SidedProxy(clientSide = ReferenceYG.PROXY_CLIENT, serverSide = ReferenceYG.PROXY_SERVER)
  var proxy:ProxyCommon=null;

  @EventHandler
  def preInit(event:FMLPreInitializationEvent){
    proxy.preInit(event);
  }

}
