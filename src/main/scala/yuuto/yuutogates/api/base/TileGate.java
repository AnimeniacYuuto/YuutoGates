package yuuto.yuutogates.api.base;

import cpw.mods.fml.common.Optional;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import yuuto.yuutofacades.api.tiles.interfaces.IFacadeManager;
import yuuto.yuutofacades.api.tiles.managers.bounds.BoundsManager;
import yuuto.yuutofacades.api.tiles.managers.facade.FacadeManagerPanels;
import yuuto.yuutogates.api.IGate;
import yuuto.yuutogates.api.PlugInLoader;
import yuuto.yuutogates.api.gates.GateHelper;
import yuuto.yuutogates.api.gates.IGateFacadeContainer;
import yuuto.yuutogates.api.logic.GateLogicNodeList;
import yuuto.yuutogates.api.material.GateMaterial;
import yuuto.yuutogates.api.material.GateMaterialRegistry;

/**
 * Created by Yuuto on 9/25/2015.
 */
@Optional.Interface(iface = "yuuto.yuutogates.api.gates.IGateFacadeContainer", modid = "yuutofacades")
public abstract class TileGate extends TileEntity implements IGate, IGateFacadeContainer{
    GateLogicNodeList nodeList;
    GateMaterial material;

    BoundsManager boundsManager;
    FacadeManagerPanels facadeManager;
    ForgeDirection orientation=ForgeDirection.DOWN;

    public TileGate(){
        if(PlugInLoader.YUUTO_FACADES){
            boundsManager=new BoundsManager(this);
            facadeManager=new FacadeManagerPanels(boundsManager, this);
        }
    }
    public TileGate(GateMaterial material){
        this();
        this.material=material;
    }
    public void setMaterial(GateMaterial material){
        this.material=material;
        createNodeList();
    }
    public void setOrientation(ForgeDirection direction){
        orientation=direction;
    }
    public ForgeDirection getOrientation(){
        return orientation;
    }
    @Override
    public void setNodeList(GateLogicNodeList list){
        this.nodeList=list;
    }

    @Override
    public Object getTarget(ForgeDirection side){
        if(side==null||side==ForgeDirection.UNKNOWN)
            return null;
        return  getWorldObj().getTileEntity(xCoord+side.offsetX, yCoord+side.offsetY, zCoord+side.offsetZ);
    }

    @Override
    public GateLogicNodeList getNodeList(){return nodeList;};
    @Override
    public GateMaterial getGateMaterial(){return material;}

    @Override
    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
        nbt.setString(GateHelper.TAG_GATE_MATERIAL, material.getID());
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);
        GateMaterial gateMaterial = GateMaterialRegistry.getGateMaterial(nbt.getString(GateHelper.TAG_GATE_MATERIAL));
        if(gateMaterial == null)
            gateMaterial=GateMaterialRegistry.defaultMaterial;
        setMaterial(gateMaterial);
    }

    @Optional.Method(modid = "yuutofacades")
    @Override
    public IFacadeManager getFacadeManager() {
        return facadeManager;
    }

    @Optional.Method(modid = "yuutofacades")
    @Override
    public boolean isSideOpenForFacades(ForgeDirection side) {
        return side != null && side != ForgeDirection.UNKNOWN && side != orientation;
    }

    @Optional.Method(modid = "yuutofacades")
    @Override
    public void onAttachmentsChanged() {

    }

    @Optional.Method(modid = "yuutofacades")
    @Override
    public int getX() {
        return xCoord;
    }

    @Optional.Method(modid = "yuutofacades")
    @Override
    public int getY() {
        return yCoord;
    }

    @Optional.Method(modid = "yuutofacades")
    @Override
    public int getZ() {
        return zCoord;
    }

    @Optional.Method(modid = "yuutofacades")
    @Override
    public World getParentWorldObj(){return getWorldObj();};

    @Optional.Method(modid = "yuutofacades")
    @Override
    public AxisAlignedBB getPlugBounds(ForgeDirection side) {return null;}
}
