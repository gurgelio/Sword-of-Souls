import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;

public class Hud {

    private TrueTypeFont fonte = new TrueTypeFont(new Font("Tahome", Font.PLAIN, 30), true);
    Image hudImage;
    private Image lifeBar = new Image("img/lifeBar.png");
    private Image manaBar = new Image("img/manaBar.png");

    public Hud(String ref) throws SlickException {
        hudImage = new Image(ref);
    }

    void render(float x, float y, int life, int gold){
        //hudImage.draw(x, y);
        lifeBar.draw(x + Game.width*(0.1f),y + Game.height*(0.94f), 10*life, 10);
        //manaBar.draw(x + Game.width*(0.1f),y + Game.height*(0.96f), mana*10, 10);
        fonte.drawString(x + 100,y + 730,"Gold : "+String.valueOf(gold), Color.cyan);
    }

}
