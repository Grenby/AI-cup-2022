package ai_cup_22.ai.tasks;

import ai_cup_22.Holder;
import ai_cup_22.model.Item;
import ai_cup_22.model.Loot;
import ai_cup_22.model.Unit;

public class VisibleArmor extends Task{

    Unit u;

    @Override
    public void run(Unit unit) {
        this.u = unit;
    }

    @Override
    public Status execute() {
        if (Holder.game == null || u == null)
            return Status.FAILED;
        for (Loot loot :Holder.game.getLoot()) {
            if (loot.getItem() instanceof Item.ShieldPotions){
                return Status.SUCCEEDED;
            }
        }
        return Status.FAILED;
    }
}
