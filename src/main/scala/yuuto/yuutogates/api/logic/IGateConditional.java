package yuuto.yuutogates.api.logic;

import yuuto.yuutogates.api.IGate;

/**
 * Created by Yuuto on 9/25/2015.
 */
public interface IGateConditional extends IGateLogicComponent{

    boolean isConditionTrue(IGate gate, Object target, boolean cache);
}
