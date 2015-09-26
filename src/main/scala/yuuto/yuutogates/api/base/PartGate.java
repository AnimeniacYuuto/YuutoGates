package yuuto.yuutogates.api.base;

import codechicken.multipart.TMultiPart;
import cpw.mods.fml.common.Optional;
import net.minecraft.nbt.NBTTagCompound;
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
public abstract class PartGate extends TMultiPart implements IGate, IGateFacadeContainer{
    GateLogicNodeList nodeList;
    GateMaterial material;
    ForgeDirection orientation=ForgeDirection.DOWN;

    BoundsManager boundsManager;
    FacadeManagerPanels facadeManager;

    public PartGate(){
        if(PlugInLoader.YUUTO_FACADES){
            boundsManager=new BoundsManager(this);
            facadeManager=new FacadeManagerPanels(boundsManager, this);
        }
    }
    public PartGate(GateMaterial material){
        this();
        setMaterial(material);
    }

    public void setMaterial(GateMaterial material){this.material=material;createNodeList();}
    public void setOrientation(ForgeDirection direction){
        orientation = direction;
    }
    public ForgeDirection getOrientation(){
        return orientation;
    }

    @Override
    public void setNodeList(GateLogicNodeList list){this.nodeList=list;}


    @Override
    public Object getTarget(ForgeDirection side) {
        if(side == null || side == ForgeDirection.UNKNOWN)
            return null;
        return world().getTileEntity(this.x(), this.z(), this.y());
    }

    @Override
    public GateLogicNodeList getNodeList() {
        return nodeList;
    }

    @Override
    public GateMaterial getGateMaterial() {
        return material;
    }

    @Override
    public void save(NBTTagCompound nbt){
        super.save(nbt);
        nbt.setString(GateHelper.TAG_GATE_MATERIAL, material.getID());
    }
    @Override
    public void load(NBTTagCompound nbt){
        super.load(nbt);
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
    public void onAttachmentsChanged() {

    }

    @Optional.Method(modid = "yuutofacades")
    @Override
    public World getParentWorldObj() {
        return world();
    }

    @Optional.Method(modid = "yuutofacades")
    @Override
    public int getX() {
        return x();
    }

    @Optional.Method(modid = "yuutofacades")
    @Override
    public int getY() {
        return y();
    }

    @Optional.Method(modid = "yuutofacades")
    @Override
    public int getZ() {
        return z();
    }

    @Optional.Method(modid = "yuutofacades")
    @Override
    public boolean isSideOpenForFacades(ForgeDirection side) {
        return side != null && side != ForgeDirection.UNKNOWN && side != orientation;
    }

    @Optional.Method(modid = "yuutofacades")
    @Override
    public AxisAlignedBB getPlugBounds(ForgeDirection side) {
        return null;
    }
}
