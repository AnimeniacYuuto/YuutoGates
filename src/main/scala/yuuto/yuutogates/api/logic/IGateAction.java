package yuuto.yuutogates.api.logic;

import yuuto.yuutogates.api.IGate;

/**
 * Created by Yuuto on 9/25/2015.
 */
public interface IGateAction extends IGateLogicComponent{

    void runLogic(IGate gate, Object target);
    boolean paramatersMatch(IGateParamater[] paramaters1, IGateParamater[] paramaters2);
}
