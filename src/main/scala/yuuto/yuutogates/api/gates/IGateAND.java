package yuuto.yuutogates.api.gates;

import yuuto.yuutogates.api.IGate;
import yuuto.yuutogates.api.logic.GateLogicNodeListAND;

/**
 * Created by Yuuto on 9/25/2015.
 */
public interface IGateAND extends IGate{

    default void createNodeList(){
        setNodeList(new GateLogicNodeListAND(this, getGateMaterial()));
    }
}
