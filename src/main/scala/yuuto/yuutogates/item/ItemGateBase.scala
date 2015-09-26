package yuuto.yuutogates.item

import codechicken.lib.vec.{Vector3, BlockCoord}
import codechicken.multipart.TMultiPart
import cpw.mods.fml.common.Optional
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.IIcon
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection
import yuuto.yuutogates.api.base.{PartGate, PlugGate, ItemBlockGate}
import yuuto.yuutogates.api.material.GateMaterial
import yuuto.yuutogates.miltipart.{PartGateXOR, PartGateAND, PartGateOR}
import yuuto.yuutogates.plugs.{PlugGateXOR, PlugGateAND, PlugGateOR}

/**
 * Created by Yuuto on 9/25/2015.
 */
class ItemGateBase(block:Block) extends ItemBlockGate(block){

  this.hasSubtypes = true;

  override def getIconFromDamage(metadata:Int):IIcon=this.field_150939_a.getIcon(2, metadata);

  override def getUnlocalizedName(stack:ItemStack):String=getUnlocalizedName()+"."+stack.getItemDamage;

  override def getMetadata (damageValue:Int):Int=damageValue;

  @Optional.Method(modid = "yuutofacades")
  override def createGate(stack:ItemStack, material: GateMaterial):PlugGate={
    stack.getItemDamage match {
      case 0=>new PlugGateOR(material);
      case 1=>new PlugGateAND(material);
      case 2=>new PlugGateXOR(material);
      case i=>null;
    }
  }

  @Optional.Method(modid = "McMultipart")
  override def newPart(item:ItemStack, player:EntityPlayer, world:World, pos:BlockCoord, side:Int, vhit:Vector3):TMultiPart= {
    val material: GateMaterial = getGateMaterial(item);
    val part:PartGate={
      item.getItemDamage match {
        case 0 => new PartGateOR(material);
        case 1 => new PartGateAND(material);
        case 2 => new PartGateXOR(material);
        case i => null;
      }
    }
    if(part == null)
      return null;
    part.setOrientation(ForgeDirection.getOrientation(side));
    part;
  }
}
