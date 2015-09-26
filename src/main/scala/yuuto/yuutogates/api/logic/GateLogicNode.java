package yuuto.yuutogates.api.logic;

import net.minecraftforge.common.util.ForgeDirection;
import yuuto.yuutogates.api.IGate;

/**
 * Created by Yuuto on 9/25/2015.
 */
public class GateLogicNode {

    IGate parent;
    GateLogicNodeList list;

    ForgeDirection conditionTarget;
    IGateConditional conditional;
    boolean conditionCache=false;
    IGateParamater[] conditionalParameters;

    ForgeDirection actionTarget;
    IGateAction action;
    IGateParamater[] actionParameters;

    public GateLogicNode(GateLogicNodeList list){
        this.parent=list.getParent();
        this.list = list;
        this.conditionalParameters=new IGateParamater[list.getMaxParams()];
        this.actionParameters=new IGateParamater[list.getMaxParams()];
    }

    public int getMaxParameters(){
        return list.getMaxParams();
    }
    public boolean isConditionTrue(){
        return conditional != null && conditional.isConditionTrue(parent, parent.getTarget(conditionTarget), conditionCache);
    }
    public ForgeDirection getConditionTarget(){return conditionTarget;}
    public IGateConditional getConditional(){return conditional;}
    public int getMaxConditionParameters(){
        if(conditional == null)
            return 0;
        return Math.min(getMaxParameters(), conditional.getMaxParameters(parent));
    }
    public IGateParamater[] getConditionParamaters(){return actionParameters;};

    public void runAction(){
        if(action == null)
            return;
        action.runLogic(parent, parent.getTarget(actionTarget));
    }
    public ForgeDirection getActionTarget(){return actionTarget;}
    public IGateAction getAction(){return action;}
    public int getMaxActionParameters(){
        if(action == null)
            return 0;
        return Math.min(getMaxParameters(), action.getMaxParameters(parent));
    }
    public IGateParamater[] getActionParamaters(){return actionParameters;};

    public boolean matches(GateLogicNode target){
        if(target == null)
            return false;
        if(action != target.getAction())
            return false;
        if(actionTarget != target.actionTarget)
            return false;
        return action.paramatersMatch(getActionParamaters(), target.getActionParamaters());

    }
}
