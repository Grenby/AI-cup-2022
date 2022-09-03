package ai_cup_22.ai.btasks;

import ai_cup_22.Holder;
import ai_cup_22.ai.utils.Entity;
import ai_cup_22.model.Unit;
import ai_cup_22.model.UnitOrder;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;

public class WalkToCenter extends LeafTask<Entity> {

    @Override
    public Status execute() {
        Utils.info("Walk to center");
        if (getObject() == null)
            return Status.FAILED;
        Unit u = getObject().unit;
        if (u == null)
            return Status.FAILED;
        UnitOrder o = getObject().unitOrder;
        if (o == null)
            return Status.FAILED;
        o.getTargetVelocity().set(u.getPosition()).scl(-1).nor().scl((float) Holder.constants.getMaxUnitForwardSpeed());
        o.getTargetDirection().set(o.getTargetVelocity());
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return new WalkToCenter();
    }
}
