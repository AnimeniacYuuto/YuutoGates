package yuuto.yuutogates.api.base;

import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Rotation;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.BlockMultipart;
import codechicken.multipart.TItemMultiPart;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.Optional.InterfaceList;
import cpw.mods.fml.common.Optional.Interface;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import yuuto.yuutofacades.api.items.IPlugItem;
import yuuto.yuutofacades.api.plugs.IPlugable;
import yuuto.yuutofacades.api.tiles.interfaces.IPlugBlackLister;
import yuuto.yuutofacades.api.tiles.interfaces.IPlugableContainer;
import yuuto.yuutofacades.api.tiles.interfaces.IPlugableManager;
import yuuto.yuutogates.api.PlugInLoader;
import yuuto.yuutogates.api.gates.GateHelper;
import yuuto.yuutogates.api.material.GateMaterial;

/**
 * Created by Yuuto on 9/25/2015.
 */
@InterfaceList(
        value = {
        @Interface(iface = "yuuto.yuutofacades.api.items.IPlugItem", modid = "yuutofacades"),
        @Interface(iface = "codechicken.multipart.TItemMultiPart", modid = "McMultipart")
        }
)
public abstract class ItemBlockGate extends ItemBlock implements IPlugItem, TItemMultiPart{

    public ItemBlockGate(Block block) {
        super(block);
    }

    public GateMaterial getGateMaterial(ItemStack stack){
        return GateHelper.getGateMaterial(stack);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
        Block  block = world.getBlock(x,y,z);
        TileEntity tile = world.getTileEntity(x,y,z);
        if(PlugInLoader.YUUTO_FACADES){
            if(onPlaceAsPlug(block, tile, stack, player, world, x, y, z, side, hitX, hitY, hitZ))
                return !world.isRemote;
        }
        if(PlugInLoader.FMP){
            if(onPlaceAsMultiPart(block, tile, stack, player, world, x, y, z, side, hitX, hitY, hitZ))
                return !world.isRemote;
        }
        return onPlaceAsBlock(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    public boolean onPlaceAsBlock(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        Block block = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);

        if (block == Blocks.snow_layer && (p_77648_3_.getBlockMetadata(p_77648_4_, p_77648_5_, p_77648_6_) & 7) < 1)
        {
            p_77648_7_ = 1;
        }
        else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush && !block.isReplaceable(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_))
        {
            if (p_77648_7_ == 0)
            {
                --p_77648_5_;
            }

            if (p_77648_7_ == 1)
            {
                ++p_77648_5_;
            }

            if (p_77648_7_ == 2)
            {
                --p_77648_6_;
            }

            if (p_77648_7_ == 3)
            {
                ++p_77648_6_;
            }

            if (p_77648_7_ == 4)
            {
                --p_77648_4_;
            }

            if (p_77648_7_ == 5)
            {
                ++p_77648_4_;
            }
        }

        if (p_77648_1_.stackSize == 0)
        {
            return false;
        }
        else if (!p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
        {
            return false;
        }
        else if (p_77648_5_ == 255 && this.field_150939_a.getMaterial().isSolid())
        {
            return false;
        }
        else if (p_77648_3_.canPlaceEntityOnSide(this.field_150939_a, p_77648_4_, p_77648_5_, p_77648_6_, false, p_77648_7_, p_77648_2_, p_77648_1_))
        {
            int i1 = this.getMetadata(p_77648_1_.getItemDamage());
            int j1 = this.field_150939_a.onBlockPlaced(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_, i1);

            if (placeBlockAt(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_, j1))
            {
                p_77648_3_.playSoundEffect((double)((float)p_77648_4_ + 0.5F), (double)((float)p_77648_5_ + 0.5F), (double)((float)p_77648_6_ + 0.5F), this.field_150939_a.stepSound.func_150496_b(), (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150939_a.stepSound.getPitch() * 0.8F);
                --p_77648_1_.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    @Optional.Method(modid = "yuutofacades")
    public boolean onPlaceAsPlug(Block block, TileEntity tile, ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
        if(!(tile instanceof IPlugableContainer))
            return false;
        IPlugableContainer container = (IPlugableContainer) tile;
        IPlugableManager manager=container.getPlugableManager();
        GateMaterial material = getGateMaterial(stack);
        IPlugable plug= createGate(stack, material);
        ForgeDirection dir = ForgeDirection.getOrientation(side);
        if(!manager.canAddPlug(plug, dir))
            return false;
        if(container instanceof IPlugBlackLister && !((IPlugBlackLister)container).isSideOpenForPlugs(dir))
            return false;
        if(!plug.canAddAttachment(container, dir))
            return false;
        IPlugable oldPlug = manager.setPlug(plug, dir);
        world.func_147479_m(x,y,z);
        if(world.isRemote)
            return true;
        if(player.capabilities.isCreativeMode)
            return true;
        stack.stackSize--;
        if(oldPlug != null){
            ItemStack dropedStack=oldPlug.getDrop();
            if(dropedStack == null)
                return true;
            if(!player.inventory.addItemStackToInventory(dropedStack)){
                player.dropPlayerItemWithRandomChoice(dropedStack, true);
            }
        }
        return true;
    }
    @Optional.Method(modid = "yuutofacades")
    public abstract PlugGate createGate(ItemStack stack, GateMaterial material);

    @Optional.Method(modid = "McMultipart")
    public boolean onPlaceAsMultiPart(Block block, TileEntity tile, ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
        if(!(tile instanceof TileMultipart))
            return false;
        BlockCoord pos = new BlockCoord(x, y, z);
        Vector3 vhit = new Vector3(hitX, hitY, hitZ);
        double d = getHitDepth(vhit, side);
        if(d < 1 && place(stack, player, world, pos, side, vhit))
            return true;

        pos.offset(side);
        return place(stack, player, world, pos, side, vhit);
    }

    @Optional.Method(modid = "McMultipart")
    public boolean place(ItemStack item, EntityPlayer player, World world, BlockCoord pos, int side, Vector3 vhit) {
        TMultiPart part = newPart(item, player, world, pos, side, vhit);
        if(part == null || !TileMultipart.canPlacePart(world, pos, part))
            return false;

        if(!world.isRemote)
            TileMultipart.addPart(world, pos, part);
        if(!player.capabilities.isCreativeMode)
            item.stackSize-=1;
        return true;
    }

    @Optional.Method(modid = "McMultipart")
    @Override
    public double getHitDepth(Vector3 vhit, int side) {
        return vhit.copy().scalarProject(Rotation.axes[side]) + (side%2^1);
    }
}
