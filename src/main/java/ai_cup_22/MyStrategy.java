package ai_cup_22;

import ai_cup_22.ai.FirstStateMachine;
import ai_cup_22.ai.MyAI;
import ai_cup_22.ai.utils.Entity;
import ai_cup_22.debugging.Color;
import ai_cup_22.debugging.logger.DefaultLogger;
import ai_cup_22.model.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import java.util.ArrayList;
import java.util.HashMap;

public class MyStrategy {

    final boolean DEBUG = true;
    final DebugInterface defaultDebug = new NoneDebug(null,null);

    final ArrayList<Entity> enemy = new ArrayList<>();
    final float nearRadius = 30;
    final ArrayList<Obstacle> obstacles = new ArrayList<>(30);
    Color color = new Color(1,0,0,0.5d);

    MyAI ai = new MyAI();
    FirstStateMachine stateMachine = new FirstStateMachine();

    public MyStrategy(Constants constants) {
        Holder.constants = constants;
        Holder.logger = new DefaultLogger();
    }

    void getNearObstacles(Vector2 pos){
        float l = nearRadius * nearRadius;
        obstacles.clear();
        for (Obstacle o:Holder.constants.getObstacles()) {
            float ll = o.getPosition().dst(pos);
            if (ll < l){
                obstacles.add(o);
            }
        }

    }

    public Order getOrder(Game game, DebugInterface debugInterface) {

        Holder.game = game;
        Holder.debugInterface = debugInterface == null ? defaultDebug : DEBUG ? debugInterface : defaultDebug;

        Unit unit = null;

        Holder.resetOrders();
        for (Unit u : game.getUnits()) {
            if (u.getPlayerId() != game.getMyId()) {
                //enemy.add(new Entity(u));
                //Holder.field.addDynamic(v-> 100/ Math.log(1+v.dist2(unit.getPosition())));
            }   else {
                Holder.addUnit(u);
                unit = u;
                //myUnit = new Entity(unit,myUnitOrder);
                //order.getUnitOrders().put(unit.getId(),myUnitOrder);
            }
        }
        if (unit!=null) {
            
            if (debugInterface!=null){
                debugInterface.getState().getCamera().setCenter(unit.getPosition());
            }

            getNearObstacles(unit.getPosition());
            //ai.updateBehavior(unit);
            stateMachine.setObstacles(obstacles);
            stateMachine.update(unit);
//        double dir = unit.getDirection().angle();
//        double delta = Holder.constants.getFieldOfView()/2;// - (Holder.constants.getFieldOfView() - Holder.constants.getWeapons()unit.getWeapon()) * aim
//        Holder.debugInterface.addPie(unit.getPosition(),Holder.constants.getFieldOfView(),dir - delta,dir+ delta, color);
        }
        return Holder.getOrder();
    }

    public void debugUpdate(int displayedTick, DebugInterface debugInterface) {}

    public void finish() {}

}