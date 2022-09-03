package ai_cup_22.ai.btasks;

import ai_cup_22.Holder;
import ai_cup_22.ai.utils.Entity;
import ai_cup_22.model.*;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.annotation.TaskAttribute;

public class PickUpLoot extends LeafTask<Entity> {

    private boolean looting = false;


    @Override
    public Status execute() {
        System.out.println("start pickup execute");
        if (getObject()==null)
            return Status.FAILED;
        Unit u = getObject().unit;
        if (u==null)
            return Status.FAILED;
        UnitOrder o = getObject().unitOrder;
        if (o == null)
            return Status.FAILED;
        if (getObject().targetLoot == null)
            return Status.FAILED;
        if (looting){
            if (u.getAction() == null || u.getAction().getActionType() != ActionType.LOOTING){
                o.setAction(null);
                getObject().targetLoot = null;
                looting = false;
                return Status.SUCCEEDED;
            }
            return Status.RUNNING;
        }
        if (u.getAction() == null || u.getAction().getActionType() != ActionType.LOOTING) {
            o.setAction(new ActionOrder.Pickup(getObject().targetLoot.getId()));
            looting = true;
            return Status.RUNNING;
        }
        return Status.FAILED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return new PickUpLoot();
    }
}
