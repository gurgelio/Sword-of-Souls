import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Hud {

    Image hudImage;
    private Image lifeBar = new Image("img/lifeBar.png");
    private Image manaBar = new Image("img/manaBar.png");

    public Hud(String ref) throws SlickException {
        hudImage = new Image(ref);
    }

    void render(float x, float y, int life, int mana){
        hudImage.draw(x, y);
        lifeBar.draw(x + Game.width*(0.1f),y + Game.height*(0.94f), 10*life, 10);
        manaBar.draw(x + Game.width*(0.1f),y + Game.height*(0.96f), mana*10, 10);
    }

}
