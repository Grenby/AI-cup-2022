package ai_cup_22.ai.btasks;

import ai_cup_22.Holder;
import ai_cup_22.ai.utils.Entity;
import ai_cup_22.model.Item;
import ai_cup_22.model.Loot;
import ai_cup_22.model.Unit;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.annotation.TaskAttribute;

public class SearchLoot extends LeafTask<Entity> {

    private enum LootType{
        SHIELD,
        WEAPON_WAND,
        WEAPON_STAFF,
        WEAPON_BOW,
        AMMO
    }

    @TaskAttribute()
    public LootType type = LootType.WEAPON_BOW;

    boolean isCorrect(Loot loot){
        switch (type){
            case SHIELD -> {
                return loot.getItem() instanceof Item.ShieldPotions;
            }
            case WEAPON_BOW -> {
                if (loot.getItem() instanceof Item.Weapon w) {
                    return w.getTypeIndex() == 2;
                }
                else
                    return false;
            }
            case WEAPON_STAFF -> {
                if (loot.getItem() instanceof Item.Weapon w) {
                    return w.getTypeIndex() == 1;
                }
                else
                    return false;
            }
            case WEAPON_WAND -> {
                if (loot.getItem() instanceof Item.Weapon w) {
                    return w.getTypeIndex() == 0;
                }
                else
                    return false;
            }
            case AMMO -> {
                if (loot.getItem() instanceof Item.Ammo a){
                    Unit u = getObject().unit;
                    return a.getWeaponTypeIndex() == u.getWeapon();
                }else
                    return false;
            }
        }
        return false;
    }

    @Override
    public Status execute() {
        Utils.info("Search Loot " + type);
        if (Holder.game == null || getObject() == null)
            return Status.FAILED;
        Loot l = null;
        if (getObject() == null)
            return Status.FAILED;
        Unit u = getObject().unit;
        for (Loot loot :Holder.game.getLoot()) {
            if (isCorrect(loot)){
                if (Utils.pointInRedZone(loot.getPosition()))
                    continue;
                if (l == null)
                    l = loot;
                else{
                    if (l.getPosition().dst2(u.getPosition()) > loot.getPosition().dst2(u.getPosition()))
                        l = loot;
                }
            }
        }
        if (l == null)
            return Status.FAILED;
        getObject().targetPos.set(l.getPosition());
        getObject().targetLoot = l;
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Entity> copyTo(Task<Entity> task) {
        return new SearchLoot();
    }
}
