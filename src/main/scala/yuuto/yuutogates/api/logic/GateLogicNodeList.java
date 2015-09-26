package yuuto.yuutogates.api.logic;

import net.minecraftforge.common.util.ForgeDirection;
import yuuto.yuutogates.api.IGate;
import yuuto.yuutogates.api.material.GateMaterial;

/**
 * Created by Yuuto on 9/25/2015.
 */
public abstract class GateLogicNodeList {
    IGate parent;
    GateLogicNode[] logicNodes;
    GateMaterial material;

    public GateLogicNodeList(IGate parent, GateMaterial gateMaterial){
        this.parent=parent;
        this.material=gateMaterial;
        this.logicNodes=new GateLogicNode[material.maxNodes];
        for(int i = 0; i < material.maxNodes; i++){
            this.logicNodes[i]=new GateLogicNode(this);
        }
    }
    public IGate getParent(){return parent;}
    public GateLogicNode[] getLogicNodes(){return logicNodes;}
    public int getMaxParams(){return material.maxParameters;}

    public abstract void runLogic();
}
