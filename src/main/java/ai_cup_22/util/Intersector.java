package ai_cup_22.util;

import com.badlogic.gdx.math.Vector2;

public class Intersector {

    public static boolean intersectRaySphere (Ray ray, Vector2 center, float radius, Vector2 intersection) {
        final float len = ray.direction.dot(center.x - ray.origin.x, center.y - ray.origin.y);
        if (len < 0.f) // behind the ray
            return false;
        final float dst2 = center.dst2(ray.origin.x + ray.direction.x * len, ray.origin.y + ray.direction.y * len);
        final float r2 = radius * radius;
        if (dst2 > r2) return false;
        if (intersection != null) intersection.set(ray.direction).scl(len - (float)Math.sqrt(r2 - dst2)).add(ray.origin);
        return true;
    }


}
