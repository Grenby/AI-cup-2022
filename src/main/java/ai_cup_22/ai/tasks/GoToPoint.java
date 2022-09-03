package ai_cup_22.ai.tasks;

import ai_cup_22.Holder;
import ai_cup_22.model.Unit;
import ai_cup_22.model.UnitOrder;
import com.badlogic.gdx.math.Vector2;

public class GoToPoint extends Task {

    final Vector2 targetPos = new Vector2();
    Unit u;
    public GoToPoint(Vector2 targetPos) {
        setName(Name.GO_TO_POINT);
        this.targetPos.set(targetPos);
    }

    @Override
    public void run(Unit unit) {
        u = unit;
    }

        @Override
        public Status execute() {
            if (u !=null){
                double r = Holder.constants.getUnitRadius();
                if (u.getPosition().dst2(targetPos) < r*r){
                    return Status.SUCCEEDED;
                }else{
                    UnitOrder o = Holder.getUnitOrder(u);
                    o.getTargetDirection().set(targetPos).sub(u.getPosition());
                    o.getTargetVelocity().set(targetPos).sub(u.getPosition()).nor().scl((float)Holder.constants.getMaxUnitForwardSpeed());
                    return Status.RUNNING;
                }
            }
            return Status.FAILED;
        }

        @Override
        public void exit() {
            targetPos.setZero();
        }
}
