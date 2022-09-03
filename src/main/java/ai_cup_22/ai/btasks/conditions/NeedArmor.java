package ai_cup_22.ai.btasks.conditions;

import ai_cup_22.Holder;
import ai_cup_22.ai.btasks.Utils;
import ai_cup_22.ai.utils.Entity;
import ai_cup_22.model.Unit;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;

public class NeedArmor extends LeafTask<Entity> {

    @Override
    public Status execute() {
        Utils.info("need armor condition");
        if (getObject()==null)
            return Status.FAILED;
        Unit u = getObject().unit;
        if (u == null)
            return Status.FAILED;
        if(u.getShield() < Holder.constants.getMaxShield())
            return Status.SUCCEEDED;
        return Status.FAILED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return new NeedArmor();
    }
}
