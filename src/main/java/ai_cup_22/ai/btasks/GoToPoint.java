package ai_cup_22.ai.btasks;

import ai_cup_22.Holder;
import ai_cup_22.ai.utils.Entity;
import ai_cup_22.model.Unit;
import ai_cup_22.model.UnitOrder;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;

public class GoToPoint extends LeafTask<Entity> {

    public GoToPoint(){

    }

    @Override
    public Status execute() {
        if(getObject() == null) {
            return Status.FAILED;
        }
        Utils.info("go to " + getObject().targetPos);
        Unit u = getObject().unit;
        if (u !=null){
            double r = Holder.constants.getUnitRadius();
            if (u.getPosition().dst2(getObject().targetPos) < r*r){
                return Status.SUCCEEDED;
            }else{
                UnitOrder o = getObject().unitOrder;
                o.getTargetDirection().set(getObject().targetPos).sub(u.getPosition());
                o.getTargetVelocity().set(getObject().targetPos).sub(u.getPosition()).nor().scl((float)Holder.constants.getMaxUnitForwardSpeed());
                return Status.RUNNING;
            }
        }
        return Status.FAILED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return new GoToPoint();
    }
}
