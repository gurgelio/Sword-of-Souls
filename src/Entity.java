import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

abstract class Entity {
    private Vector2f pos;
    private int hp = 100, atack = 15, armor = 40;

    Anim body = new Anim(new Image(Inventory.maleBody),64,64, 120);
    Anim shirt = new Anim(new Image(Inventory.brownShirt),64,64,120);
    Anim hair = new Anim(new Image(Inventory.blondeHair),64,64,120);
    Anim legs = new Anim(new Image(Inventory.greenPants),64,64,120);
    Anim feet = new Anim(new Image(Inventory.brownShoes),64,64,120);

    Anim death = new Anim(new Image(Inventory.maleBodyDying), 64, 64, 120);

    CharAnimation chAnim = new CharAnimation(new Anim[] {body,shirt,hair,legs,feet});

    void setAnimation(Anim[] animationSet){
        chAnim = new CharAnimation(animationSet);
    }

    void setDeath() {
        chAnim = new CharAnimation(new Anim[] {death});
    }

    Entity() throws SlickException {
    }
}