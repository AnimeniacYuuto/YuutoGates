package yuuto.yuutogates.blocks

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block
import yuuto.yuutogates.YuutoGates
import yuuto.yuutogates.item.ItemGateBase
import yuuto.yuutogates.tile.{TileGateXOR, TileGateOR, TileGateAND}

/**
 * Created by Yuuto on 9/26/2015.
 */
object BlocksYG {
  final val blockGateBasic:Block=new BlockGateBase().setBlockName("yuutogates:gate").setCreativeTab(YuutoGates.tabGates);

  def init(){
    GameRegistry.registerBlock(blockGateBasic, classOf[ItemGateBase], "gateBlockBase");
    GameRegistry.registerTileEntity(classOf[TileGateAND], "container.gateAND");
    GameRegistry.registerTileEntity(classOf[TileGateOR], "container.gateOR");
    GameRegistry.registerTileEntity(classOf[TileGateXOR], "container.gateXOR");
  }

}
