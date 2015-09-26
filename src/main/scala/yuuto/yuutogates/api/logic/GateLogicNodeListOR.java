package yuuto.yuutogates.api.logic;

import net.minecraftforge.common.util.ForgeDirection;
import yuuto.yuutogates.api.IGate;
import yuuto.yuutogates.api.material.GateMaterial;

/**
 * Created by Yuuto on 9/25/2015.
 */
public class GateLogicNodeListOR extends GateLogicNodeList{

    public GateLogicNodeListOR(IGate parent, GateMaterial gateMaterial) {
        super(parent, gateMaterial);
    }

    @Override
    public void runLogic(){
        ForgeDirection direction=ForgeDirection.UNKNOWN;
        GateLogicNode actionNode=null;
        boolean flag=false;
        for(int i = 0; i < logicNodes.length; i++){
            GateLogicNode node = logicNodes[i];
            if(node.matches(actionNode)){
                flag = flag && node.isConditionTrue();
            }else{
                if(flag)
                    actionNode.runAction();
                actionNode=node;
                flag=actionNode.isConditionTrue();
            }
        }
    }
}
