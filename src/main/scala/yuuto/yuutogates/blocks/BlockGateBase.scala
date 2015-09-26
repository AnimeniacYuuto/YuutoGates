package yuuto.yuutogates.blocks

import java.util

import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.init.Blocks
import net.minecraft.item.{ItemStack, Item}
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.{IIcon, MovingObjectPosition, Vec3, AxisAlignedBB}
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection
import yuuto.yuutofacades.api.blocks.{IRaytraceBlock, RayTraceResult, BlockAttachmentHelper}
import yuuto.yuutogates.api.base.{TileGate, BlockGate}
import yuuto.yuutogates.api.gates.GateHelper
import yuuto.yuutogates.tile.{TileGateXOR, TileGateAND, TileGateOR}

/**
 * Created by Yuuto on 9/26/2015.
 */
object BlockGateBase{
  val bounds:Array[AxisAlignedBB]=Array[AxisAlignedBB](
    AxisAlignedBB.getBoundingBox(0.25, 0, 0.25, 0.75, 0.125, 0.75),
    AxisAlignedBB.getBoundingBox(0.25, 0.875, 0.25, 0.75, 1, 0.75),
    AxisAlignedBB.getBoundingBox(0.25, 0.25, 0, 0.75, 0.75, 0.125),
    AxisAlignedBB.getBoundingBox(0.25, 0.25, 0.875, 0.75, 0.75, 1),
    AxisAlignedBB.getBoundingBox(0, 0.25, 0.25, 0.125, 0.75, 0.75),
    AxisAlignedBB.getBoundingBox(0.875, 0.25, 0.25, 1, 0.75, 0.75)
  );
}
class BlockGateBase extends BlockGate(Material.rock) with IRaytraceBlock{

  override def createNewTileEntity(world: World, meta:Int):TileEntity={
    meta match {
      case 0=>new TileGateOR();
      case 1=>new TileGateAND();
      case 2=>new TileGateXOR();
      case i=>null;
    }
  }

  override def getSubBlocks(item:Item, tab:CreativeTabs, list:util.List[_]){
    val itemList:util.List[ItemStack]=list.asInstanceOf[util.List[ItemStack]];
    var stack:ItemStack=new ItemStack(this, 1, 0);
    val nbt:NBTTagCompound=new NBTTagCompound();
    nbt.setString(GateHelper.TAG_GATE_MATERIAL, "redstone")
    stack.setTagCompound(nbt);
    itemList.add(stack);
    stack=stack.copy();
    stack.setItemDamage(1);
    itemList.add(stack);
    stack=stack.copy();
    stack.setItemDamage(2);
    itemList.add(stack);
  }

  /*===== Vanilla Raytrace =====*/
  @Override
  override def getIcon(side:Int, meta:Int):IIcon=Blocks.redstone_block.getIcon(0,0);

  override def addCollisionBoxesToList(world:World, x:Int, y:Int, z:Int, axisalignedbb:AxisAlignedBB, arraylist:util.List[_], entity:Entity) {
    val tile:TileEntity=world.getTileEntity(x,y,z);
    BlockAttachmentHelper.addCollisionBoxesToList(this, world, x, y, z, tile, axisalignedbb, arraylist, entity);
  }
  @SideOnly(Side.CLIENT)
  override def getSelectedBoundingBoxFromPool(world:World, x:Int, y:Int, z:Int):AxisAlignedBB={
    val tile:TileEntity=world.getTileEntity(x,y,z);
    val result:RayTraceResult = BlockAttachmentHelper.raytrace(this, world, x, y, z, tile, true);
    if(result != null && result.getBoundsHit != null) {
      System.out.println("Found bounding box");
      return result.getBoundsHit.copy().expand(0.08, 0.08, 0.08);
    }
    System.out.println("Did not find bounding box");
    super.getSelectedBoundingBoxFromPool(world, x, y, z);
  }
  override def collisionRayTrace(world: World, x: Int, y: Int, z: Int, origin: Vec3, direction: Vec3):MovingObjectPosition={
    val tile:TileEntity = world.getTileEntity(x,y,z);
    val result:RayTraceResult = BlockAttachmentHelper.raytrace(this, world, x, y, z, tile, true, origin, direction);
    if(result == null)
      return null;
    result.getHit;
  }
  /*===== Ray Trace Block =====*/
  override def setBlockBounds(blockBounds: AxisAlignedBB){
    setBlockBounds(blockBounds.minX.toFloat, blockBounds.minY.toFloat, blockBounds.minZ.toFloat, blockBounds.maxX.toFloat, blockBounds.maxY.toFloat, blockBounds.maxZ.toFloat)
  }

  override def collisionRayTraceIMP(world: World, x: Int, y: Int, z: Int, origin: Vec3, direction: Vec3): MovingObjectPosition={
    super.collisionRayTrace(world, x, y, z, origin, direction);
  }

  override def getBoundingBoxes(world: World, x: Int, y: Int, z: Int): Array[AxisAlignedBB]={
    val tile:TileEntity=world.getTileEntity(x,y,z);
    if(!tile.isInstanceOf[TileGate])
      return Array(BlockGateBase.bounds(0));
    val dir:ForgeDirection= tile.asInstanceOf[TileGate].getOrientation;
    Array(BlockGateBase.bounds(dir.ordinal()));
  }

  override def addCollisionBoxesToListIMP(world: World, x: Int, y: Int, z: Int, axisalignedbb: AxisAlignedBB, arraylist: util.List[_], entity: Entity){
    super.addCollisionBoxesToList(world, x, y, z, axisalignedbb, arraylist, entity);
  }
}
