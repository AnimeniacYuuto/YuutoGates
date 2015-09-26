package yuuto.yuutogates.miltipart

import java.util
import java.lang.Iterable

import codechicken.lib.vec.BlockCoord
import codechicken.multipart.MultiPartRegistry.IPartConverter
import codechicken.multipart.TMultiPart
import net.minecraft.block.Block
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import yuuto.yuutogates.api.base.{PartGate, TileGate}
import yuuto.yuutogates.api.gates.GateHelper
import yuuto.yuutogates.api.material.{GateMaterialRegistry, GateMaterial}
import yuuto.yuutogates.blocks.BlocksYG
import yuuto.yuutogates.tile.{TileGateXOR, TileGateOR, TileGateAND}

/**
 * Created by Yuuto on 9/26/2015.
 */
object MultiPartConverter extends IPartConverter{
  val blocks:util.List[Block]=util.Arrays.asList(BlocksYG.blockGateBasic);

  override def blockTypes:Iterable[Block]=blocks;

  override def convert(world: World, pos: BlockCoord):TMultiPart={
    val tile:TileEntity=world.getTileEntity(pos.x, pos.y, pos.z);
    if(!tile.isInstanceOf[TileGate])
      return null;
    val part:PartGate={
      tile match {
        case a:TileGateAND=>new PartGateAND();
        case o:TileGateOR=>new PartGateOR();
        case x:TileGateXOR=>new PartGateXOR();
        case default=>null;
      }
    }
    if(part == null)
      return null;
    val nbt:NBTTagCompound=new NBTTagCompound();
    tile.writeToNBT(nbt);
    part.load(nbt);
    part;
  }
}
