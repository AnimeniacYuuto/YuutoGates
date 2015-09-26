package yuuto.yuutogates.api.gates;

import yuuto.yuutogates.api.IGate;
import yuuto.yuutogates.api.logic.GateLogicNodeListXOR;

/**
 * Created by Yuuto on 9/25/2015.
 */
public interface IGateXOR extends IGate{

    default void createNodeList(){
        setNodeList(new GateLogicNodeListXOR(this, getGateMaterial()));
    }
}
