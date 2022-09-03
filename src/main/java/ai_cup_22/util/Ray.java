package ai_cup_22.util;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Ray {

    public final Vector2 origin = new Vector2();
    public final Vector2 direction = new Vector2();

    public Ray () { }

    public Ray (Vector2 origin, Vector2 direction) {
        this.origin.set(origin);
        this.direction.set(direction).nor();
    }

    public Ray cpy () {
        return new Ray(this.origin, this.direction);
    }

    public Vector2 getEndPoint (final Vector2 out, final float distance) {
        return out.set(direction).scl(distance).add(origin);
    }

    static Vector2 tmp = new Vector2();

    public String toString () {
        return "ray [" + origin + ":" + direction + "]";
    }

    public Ray set (Vector2 origin, Vector2 direction) {
        this.origin.set(origin);
        this.direction.set(direction).nor();
        return this;
    }

    public Ray set (float x, float y, float dx, float dy) {
        this.origin.set(x, y);
        this.direction.set(dx, dy);
        return this;
    }

    public Ray set (Ray ray) {
        this.origin.set(ray.origin);
        this.direction.set(ray.direction);
        return this;
    }

    @Override
    public boolean equals (Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        com.badlogic.gdx.math.collision.Ray r = (com.badlogic.gdx.math.collision.Ray)o;
        return this.direction.equals(r.direction) && this.origin.equals(r.origin);
    }

    @Override
    public int hashCode () {
        final int prime = 73;
        int result = 1;
        result = prime * result + this.direction.hashCode();
        result = prime * result + this.origin.hashCode();
        return result;
    }

}
