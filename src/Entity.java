import org.newdawn.slick.geom.Vector2f;

abstract class Entity {
    private Vector2f pos;
    private CharAnimation chanim;

    Entity(Anim[] animationSet){
        chanim = new CharAnimation(animationSet);
    }

    Entity(){

    }
}