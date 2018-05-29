import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;

abstract class Entity {
    private Vector2f pos;
    private int hp = 100, atack = 15, armor = 40;
    private Inventory equipment;

    Actions body = new Actions(new Image(Inventory.walkdir+Inventory.maleBody),64,64, 120);
    Actions shirt = new Actions(new Image(Inventory.walkdir+Inventory.brownShirt),64,64,120);
    Actions hair = new Actions(new Image(Inventory.walkdir+Inventory.blondeHair),64,64,120);
    Actions legs = new Actions(new Image(Inventory.walkdir+Inventory.greenPants),64,64,120);
    Actions feet = new Actions(new Image(Inventory.walkdir+Inventory.brownShoes),64,64,120);

    Actions death = new Actions(new Image(Inventory.deathdir+Inventory.maleBody), 64, 64, 120);

    Anim walk = new Anim(new Actions[] {body,shirt,hair,legs,feet});
    CharAnimation chAnim = new CharAnimation(walk.getAnimList());

    void setAnimation(ArrayList<Actions> animationSet){
        chAnim = new CharAnimation(animationSet);
    }

    void setDeath() {
        ArrayList<Actions> aux = new ArrayList<>();
        aux.add(death);
        chAnim = new CharAnimation(aux);
    }

    Entity() throws SlickException {
    }
}