package yuuto.yuutogates.api.logic;

import yuuto.yuutogates.api.IGate;

/**
 * Created by Yuuto on 9/25/2015.
 */
public interface IGateLogicComponent {

    int getMaxParameters(IGate gate);
    boolean isComponentValid(IGate gate, Object target);
}
