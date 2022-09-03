package ai_cup_22.ai.utils;

import ai_cup_22.model.Loot;
import ai_cup_22.model.Obstacle;
import ai_cup_22.model.Unit;
import ai_cup_22.model.UnitOrder;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Entity {

    public Unit unit = null;
    public UnitOrder unitOrder = null;
    public Loot targetLoot = null;
    public final Vector2 targetPos = new Vector2(0,0);
    public ArrayList<Obstacle> nearObstacles = null;

}
