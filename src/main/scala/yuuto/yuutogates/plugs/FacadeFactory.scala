package yuuto.yuutogates.plugs

import yuuto.yuutofacades.api.plugs.PlugableRegistry

/**
 * Created by Yuuto on 9/26/2015.
 */
object FacadeFactory {

  def init(){
    PlugableRegistry.registerPlugable(classOf[PlugGateAND], "plug.yuutofacades.gateAND");
    PlugableRegistry.registerPlugable(classOf[PlugGateOR], "plug.yuutofacades.gateOR");
    PlugableRegistry.registerPlugable(classOf[PlugGateXOR], "plug.yuutofacades.gateXOR");
  }

}
