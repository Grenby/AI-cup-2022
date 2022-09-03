package ai_cup_22.ai.tasks;

import ai_cup_22.Holder;
import ai_cup_22.model.Unit;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;

public class RandRun extends LeafTask<Unit> {

    Vector2 targetPos = new Vector2();

    private void setRandPos(){
        targetPos.setToRandomDirection();
        targetPos.scl((float)(Holder.game.getZone().getCurrentRadius() * Math.random() * 0.75f));
        targetPos.add(Holder.game.getZone().getCurrentCenter());
    }

    @Override
    public void start() {
        setRandPos();
    }

    @Override
    public Status execute() {

        return null;
    }

    @Override
    protected Task<Unit> copyTo(Task<Unit> task) {
        return null;
    }
}
