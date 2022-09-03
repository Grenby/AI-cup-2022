package ai_cup_22.ai.btasks;

import ai_cup_22.Holder;
import ai_cup_22.ai.utils.Entity;
import ai_cup_22.model.Unit;
import ai_cup_22.model.UnitOrder;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;

public class RunFromBorder extends LeafTask<Entity> {

    boolean running = false;

    @Override
    public Status execute() {
        Utils.info("run from border");
        if (getObject()==null)
            return Status.FAILED;
        Unit u = getObject().unit;
        if(u==null)
            return Status.FAILED;
        double d = Holder.game.getZone().getCurrentRadius() - 10;
        if (d < 0)
            return Status.SUCCEEDED;
        if (d*d < u.getPosition().dst2(Holder.game.getZone().getCurrentCenter())){
            running = true;
            UnitOrder o =getObject().unitOrder;
            o.getTargetVelocity().set(u.getPosition()).scl(-1).nor().scl((float) Holder.constants.getMaxUnitForwardSpeed());
            o.getTargetDirection().set(o.getTargetVelocity());
            return Status.RUNNING;
        }else if (running && (d - 10) * (d-10) < u.getPosition().dst2(Holder.game.getZone().getCurrentCenter()) && (d - 10) > 0){
            UnitOrder o =getObject().unitOrder;
            o.getTargetVelocity().set(u.getPosition()).scl(-1).nor().scl((float) Holder.constants.getMaxUnitForwardSpeed());
            o.getTargetDirection().set(o.getTargetVelocity());
            return Status.RUNNING;
        }
        running = false;
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return null;
    }
}
