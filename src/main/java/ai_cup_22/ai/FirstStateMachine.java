package ai_cup_22.ai;

import ai_cup_22.Holder;
import ai_cup_22.ai.tasks.GoToPoint;
import ai_cup_22.ai.tasks.Task;
import ai_cup_22.ai.utils.Entity;
import ai_cup_22.model.Obstacle;
import ai_cup_22.model.Unit;
import ai_cup_22.util.Intersector;
import ai_cup_22.util.Ray;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser;
import com.badlogic.gdx.math.Vector2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class FirstStateMachine {

    Task currentTask;
    Task globalTask;

    Unit u;
    Task.Status status;

    ArrayList<Obstacle> nearObstacles;
    Ray ray = new Ray();
    Vector2 tmp = new Vector2();
    BehaviorTree<Entity> tree;
    Entity e = new Entity();
    public FirstStateMachine(){
        BehaviorTreeParser<Entity> parser = new BehaviorTreeParser<>();
        try {
            FileReader reader = new FileReader("src/main/java/trees/btree");
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

    void setTask(Task task){
        currentTask = task;
        task.run(u);
    }

    public void update(Unit unit){
        e.unit = unit;
        e.unitOrder = Holder.getUnitOrder(unit);
        tree.step();

    }



    void obstacle() {
        Obstacle obs = null;
        Vector2 d = new Vector2();
        Vector2 dir = new Vector2(d);

        for (Obstacle obstacle:nearObstacles) {
            double dst = obstacle.getPosition().dst2(u.getPosition());
            if (dst < 10*10){
                if (Intersector.intersectRaySphere(ray.set(u.getPosition(),u.getVelocity()),obstacle.getPosition(),(float) (obstacle.getRadius()+Holder.constants.getUnitRadius()),dir)){
                    if (obs == null){
                        obs = obstacle;
                    }else{
                        if (dst < obs.getPosition().dst2(u.getPosition()))
                            obs = obstacle;
                    }
                }
            }
        }
        if (obs==null)
            return;
        d.set(obs.getPosition()).sub(u.getPosition());
        double r = obs.getRadius() + Holder.constants.getUnitRadius();
        double sin = r / d.len();
        double cos = Math.sqrt(1 - sin * sin);

        Vector2 velocity = u.getVelocity();
        dir.set(d);
        double newX = d.x * cos - d.y * sin;
        double newY = d.x * sin + d.y * cos;
        dir.set((float)newX,(float) newY);

        if (d.crs(dir) * d.crs(velocity) <= 0 ){
            sin = -sin;
            newX = d.x * cos - d.y * sin;
            newY = d.x * sin + d.y * cos;
            dir.set((float)newX,(float) newY);
        }
        dir.nor().scl((float) Math.sqrt(d.len2() - r*r));
        globalTask = currentTask;
        //Holder.debugInterface.addPie(new Vec2(dir.x,dir.y),1,0,360, new Color(1,0,0,1));
        setTask(new GoToPoint(dir));
        //o.getTargetDirection().set(o.getTargetVelocity());
    }

    Vector2 randPos(){
        tmp.set(1,0);
        tmp.setToRandomDirection();
        tmp.scl((float)(Holder.game.getZone().getCurrentRadius() * Math.random() * 0.75f));
        tmp.add(Holder.game.getZone().getCurrentCenter());
        return tmp;
    }

    void updateState(){
        if (currentTask == null){
            setTask(new GoToPoint(randPos()));
        }
        switch (currentTask.getName()){
            case GO_TO_POINT -> {
                updateGoToPoint();
            }
            default -> {
                setTask(new GoToPoint(randPos()));
            }
        }
    }

    void updateGoToPoint(){
        Task.Status s = currentTask.execute();
        if (s == Task.Status.SUCCEEDED){
            currentTask.exit();
            if (globalTask == null) {
                setTask(new GoToPoint(randPos()));
            }else{
                currentTask = globalTask;
            }
            updateState();
        }
    }

}
