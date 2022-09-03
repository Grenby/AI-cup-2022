package ai_cup_22.ai;

import ai_cup_22.Holder;
import ai_cup_22.ai.utils.Entity;
import ai_cup_22.model.Obstacle;
import ai_cup_22.model.Unit;
import ai_cup_22.util.Ray;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser;
import com.badlogic.gdx.math.Vector2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class FirstStateMachine {

    Unit u;

    ArrayList<Obstacle> nearObstacles;
    Ray ray = new Ray();
    Vector2 tmp = new Vector2();
    BehaviorTree<Entity> tree;
    Entity e = new Entity();

    public FirstStateMachine(){
        BehaviorTreeParser<Entity> parser = new BehaviorTreeParser<>();
        try {
            FileReader reader = new FileReader("src/main/java/ai_cup_22/ai/btree/btree");
            tree = parser.parse( reader, e);
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    public void setObstacles(ArrayList<Obstacle> obstacles){
        nearObstacles = obstacles;
        e.nearObstacles = obstacles;
    }

    public void update(Unit unit){
        e.unit = unit;
        e.unitOrder = Holder.getUnitOrder(unit);
        tree.step();

    }


    Vector2 randPos(){
        tmp.set(1,0);
        tmp.setToRandomDirection();
        tmp.scl((float)(Holder.game.getZone().getCurrentRadius() * Math.random() * 0.75f));
        tmp.add(Holder.game.getZone().getCurrentCenter());
        return tmp;
    }

}
