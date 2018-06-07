import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

class Skeleton extends Entity{
    Skeleton(float x, float y, int[] stats, String[] equipment) throws SlickException {
        super(equipment, stats[0], stats[1], stats[2], stats[3]);
        pos = new Vector2f(x, y);
    }
}
