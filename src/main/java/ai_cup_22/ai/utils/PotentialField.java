package ai_cup_22.ai.utils;

import ai_cup_22.model.Vec2;
import com.badlogic.gdx.ai.steer.behaviors.FollowFlowField;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.function.Function;

public class PotentialField{

    ArrayList<Function<Vector2,Float>> functions = new ArrayList<>();
    Vector2 tmp = new Vector2();

    public void addFunc(Function<Vector2,Float> func){
        functions.add(func);
    }

    public float getVal(float x,float y){
        return getVal(tmp.set(x,y));
    }

    public float getVal(Vector2 pos){
        float res = 0;
        for (Function<Vector2,Float> f:functions) {
            res += f.apply(pos);
        }
        return res;
    }

    public Vector2 getGrad(Vector2 pos){
        return getGrad(pos,new Vector2());
    }

    public Vector2 getGrad(Vector2 pos,Vector2 out){
        final float eps = 0.01f;
        final float val = getVal(pos);
        final float x = getVal(tmp.set(pos).add(eps,0)) - val;
        final float y = getVal(tmp.set(pos).add(0,eps)) - val;
        return out.set(x,y).nor();
    }

}
