package ai_cup_22.ai.btasks;

import ai_cup_22.Holder;
import ai_cup_22.ai.utils.Entity;
import ai_cup_22.model.Unit;
import com.badlogic.gdx.math.Vector2;

public class Utils {

    public static void info(String s){
        Holder.logger.info("ACTIONS:" , s);
    }

    public static boolean pointInRedZone(Vector2 vector2){
        return vector2.dst2(Holder.game.getZone().getCurrentCenter()) > Holder.game.getZone().getCurrentRadius() * Holder.game.getZone().getCurrentRadius();
    }

    public static boolean getAround(Entity unit, Vector2 targetPos, Vector2 out){

        return false;
    }

}
