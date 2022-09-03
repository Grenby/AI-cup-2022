package ai_cup_22;

import ai_cup_22.debugging.logger.Logger;
import ai_cup_22.debugging.logger.NullLogger;
import ai_cup_22.model.*;

import java.util.HashMap;

public class Holder {

    public static Constants constants;
    public static Game game;
    public static DebugInterface debugInterface;
    public static Logger logger = new NullLogger();
    private static final Order order = new Order(new HashMap<>());

    public static float getDelta(){
        return constants == null ? 0f : (float) (1/constants.getTicksPerSecond());
    }

    public static UnitOrder getUnitOrder(Unit unit){
        return order.getUnitOrders().get(unit.getId());
    }

    public static void addUnit(Unit unit){
        if (!order.getUnitOrders().containsKey(unit.getId()))
            order.getUnitOrders().put(unit.getId(),new UnitOrder());
    }

    public static void resetOrders(){
        order.getUnitOrders().clear();
    }

    public static Order getOrder(){
        return order;
    }

}
