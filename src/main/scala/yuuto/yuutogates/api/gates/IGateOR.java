package yuuto.yuutogates.api.gates;

import yuuto.yuutogates.api.IGate;
import yuuto.yuutogates.api.logic.GateLogicNodeListOR;

/**
 * Created by Yuuto on 9/25/2015.
 */
public interface IGateOR extends IGate{

    default void createNodeList(){
        setNodeList(new GateLogicNodeListOR(this, getGateMaterial()));
    }
}
