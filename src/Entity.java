import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {
    private Vector2f pos;
    private CharAnimation chanim;

    public Entity(Anim[] animationSet){
        chanim = new CharAnimation(animationSet);
    }
    
}