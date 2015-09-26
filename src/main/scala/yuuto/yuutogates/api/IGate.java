package yuuto.yuutogates.api;

import net.minecraftforge.common.util.ForgeDirection;
import yuuto.yuutofacades.api.tiles.interfaces.IAttachmentParent;
import yuuto.yuutofacades.api.tiles.interfaces.IFacadeContainer;
import yuuto.yuutogates.api.logic.GateLogicNodeList;
import yuuto.yuutogates.api.material.GateMaterial;

/**
 * Created by Yuuto on 9/25/2015.
 */
public interface IGate{
    Object getTarget(ForgeDirection side);
    GateLogicNodeList getNodeList();
    GateMaterial getGateMaterial();

    void createNodeList();
    void setNodeList(GateLogicNodeList list);
}
