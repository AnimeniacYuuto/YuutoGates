package yuuto.yuutogates.api.base;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import yuuto.yuutogates.api.gates.GateHelper;
import yuuto.yuutogates.api.material.GateMaterial;

/**
 * Created by Yuuto on 9/25/2015.
 */
public abstract class BlockGate extends BlockContainer{
    public BlockGate(Material mat) {
        super(mat);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(!(tile instanceof TileGate))
            return;
        GateMaterial material = GateHelper.getGateMaterial(stack);
        TileGate gate = (TileGate) tile;
        gate.setMaterial(material);
        int l = BlockPistonBase.determineOrientation(world, x, y, z, entity);
        gate.setOrientation(ForgeDirection.getOrientation(l));
    }
}
