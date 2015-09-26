package yuuto.yuutogates.proxy

import cpw.mods.fml.common.Loader
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import yuuto.yuutogates.api.PlugInLoader
import yuuto.yuutogates.blocks.BlocksYG
import yuuto.yuutogates.miltipart.MultiPartFactory
import yuuto.yuutogates.plugs.FacadeFactory

/**
 * Created by Yuuto on 9/26/2015.
 */
class ProxyCommon {

  def preInit(event:FMLPreInitializationEvent){
    PlugInLoader.FMP=Loader.isModLoaded("McMultipart");
    PlugInLoader.YUUTO_FACADES=Loader.isModLoaded("yuutofacades");
    BlocksYG.init();
    if(PlugInLoader.FMP){
      MultiPartFactory.registerMultiParts();
    }
    if(PlugInLoader.YUUTO_FACADES){
      FacadeFactory.init();
    }
  }
  def init(event:FMLInitializationEvent){

  }
  def postInit(event:FMLPostInitializationEvent){

  }

}
