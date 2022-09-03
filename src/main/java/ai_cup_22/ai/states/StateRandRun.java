package ai_cup_22.ai.states;

import ai_cup_22.Holder;
import ai_cup_22.ai.utils.Entity;
import ai_cup_22.model.Unit;
import ai_cup_22.model.UnitOrder;
import ai_cup_22.model.Vec2;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.steer.behaviors.RaycastObstacleAvoidance;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class StateRandRun implements State<Unit> {

    //RaycastObstacleAvoidance<Vector2> raycastObstacleAvoidance = new RaycastObstacleAvoidance<Vector2>();
    Vector2 targetPos = new Vector2();
    Unit unit;
    Vec2 tmp = new Vec2();

    private void setRandPos(){
        targetPos.setToRandomDirection();
        targetPos.scl((float)(Holder.game.getZone().getCurrentRadius() * Math.random() * 0.75f));
        targetPos.add(Holder.game.getZone().getCurrentCenter());
    }

    @Override
    public void enter(Unit unit) {
        this.unit = unit ;
        setRandPos();
        System.out.println("Unit" + unit.getId()  + " in state RunRand");
    }

    @Override
    public void update(Unit unit) {
        if (unit.getId() == this.unit.getId()){
            UnitOrder o = Holder.getUnitOrder(unit);

            o.getTargetDirection().set(targetPos).sub(unit.getPosition());
            o.getTargetVelocity().set(targetPos).sub(unit.getPosition()).nor().scl((float)Holder.constants.getMaxUnitForwardSpeed());
        }

    }

    @Override
    public void exit(Unit unit) {
        if (unit.getId() == this.unit.getId()){
            targetPos = null;
        }
    }

    @Override
    public boolean onMessage(Unit unit, Telegram telegram) {
        return false;
    }

}
