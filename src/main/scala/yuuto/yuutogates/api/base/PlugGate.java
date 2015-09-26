package yuuto.yuutogates.api.base;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import yuuto.yuutofacades.api.plugs.EPlugType;
import yuuto.yuutofacades.api.plugs.IPlugableDynamic;
import yuuto.yuutofacades.api.plugs.PlugableHelper;
import yuuto.yuutofacades.api.plugs.PlugableRegistry;
import yuuto.yuutofacades.api.tiles.interfaces.IAttachmentParent;
import yuuto.yuutogates.api.IGate;
import yuuto.yuutogates.api.gates.GateHelper;
import yuuto.yuutogates.api.logic.GateLogicNodeList;
import yuuto.yuutogates.api.material.GateMaterial;
import yuuto.yuutogates.api.material.GateMaterialRegistry;

/**
 * Created by Yuuto on 9/25/2015.
 */
public abstract class PlugGate implements IPlugableDynamic, IGate {
    IAttachmentParent parent=null;
    GateLogicNodeList nodeList;
    GateMaterial material;

    public PlugGate(){}
    public PlugGate(GateMaterial material){
        this.material=material;
        createNodeList();
    }
    public void setMaterial(GateMaterial material){
        this.material=material;
        createNodeList();
    }
    @Override
    public void setNodeList(GateLogicNodeList list){
        nodeList=list;
    }

    @Override
    public void initialize(){}

    @Override
    public void updatePlugable(){
        getNodeList().runLogic();
    }
    @Override
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ){}
    @Override
    public void onParentChanged(){}
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block){}

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt){
        nbt.setString(PlugableHelper.TAG_Plugable_TYPE, PlugableRegistry.getNameFor(this.getClass()));
        nbt.setString(GateHelper.TAG_GATE_MATERIAL, material.getID());
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt){
        GateMaterial gateMaterial = GateMaterialRegistry.getGateMaterial(nbt.getString(GateHelper.TAG_GATE_MATERIAL));
        if(gateMaterial == null)
            gateMaterial=GateMaterialRegistry.defaultMaterial;
        setMaterial(gateMaterial);
    }

    @Override
    public int updateModeMask(){return 9;}
    @Override
    public EPlugType getPlugType(){return EPlugType.PlugSolid;}
    @Override
    public IIcon getIcon(int pass){return Blocks.redstone_block.getIcon(0,0);}
    @Override
    public int getRenderColor(int pass){return 16777215;}
    @Override
    public boolean canRenderInPass(int pass){return pass == 0;};
    @Override
    public void setParent(IAttachmentParent parent){this.parent=parent;}
    @Override
    public boolean canAddAttachment(IAttachmentParent parent, ForgeDirection side){
        return !(parent instanceof IGate);
    }

    @Override
    public Object getTarget(ForgeDirection side){
        if(side==null||side==ForgeDirection.UNKNOWN)
            return parent;
        return  parent.getParentWorldObj().getTileEntity(parent.getX() + side.offsetX, parent.getY() + side.offsetY, parent.getZ() + side.offsetZ);
    }

    @Override
    public GateLogicNodeList getNodeList(){return nodeList;};
    @Override
    public GateMaterial getGateMaterial(){return material;}
}
