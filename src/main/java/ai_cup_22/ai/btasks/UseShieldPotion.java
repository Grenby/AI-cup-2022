package ai_cup_22.ai.btasks;

import ai_cup_22.ai.utils.Entity;
import ai_cup_22.model.ActionOrder;
import ai_cup_22.model.ActionType;
import ai_cup_22.model.Unit;
import ai_cup_22.model.UnitOrder;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;

public class UseShieldPotion extends LeafTask<Entity> {

    boolean start = false;

    @Override
    public Status execute() {
        Utils.info("add armor");
        if (getObject() == null)
            return Status.FAILED;
        Unit u = getObject().unit;
        if (u==null)
            return Status.FAILED;
        if (start && (u.getAction() == null || u.getAction().getActionType() !=ActionType.USE_SHIELD_POTION)) {
            start = false;
            return Status.SUCCEEDED;
        }
        if (u.getAction() != null && u.getAction().getActionType() == ActionType.USE_SHIELD_POTION)
            return Status.RUNNING;
        if (u.getShieldPotions() > 0){
            UnitOrder o = getObject().unitOrder;
            if (o==null)
                return Status.FAILED;
            start = true;
            o.setAction(new ActionOrder.UseShieldPotion());
            return Status.RUNNING;
        }
        return Status.FAILED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return new UseShieldPotion();
    }
}
