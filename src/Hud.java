import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

public class Hud {

    Image hudImage;
    private Image lifeBar = new Image("img/lifeBar.png");
    private Image manaBar = new Image("img/manaBar.png");
    private Image inventory = new Image("img/inventoryHud.png");
    private float x = 0, y = 0;

    public Hud(String ref) throws SlickException {
        hudImage = new Image(ref);
    }

    void render(float x, float y, int life, int mana){
        hudImage.draw(x, y);
        lifeBar.draw(x + Game.width*(0.1f),y + Game.height*(0.94f), 10*life, 10);
        manaBar.draw(x + Game.width*(0.1f),y + Game.height*(0.96f), mana*10, 10);
    }

    void renderInventory(boolean invState){
        if (invState) {
            //new TrueTypeFont(new Font("Tahome", Font.PLAIN, 30), true).drawString(100, 0, (In.mouseIsOver(new int[]{(int) x + Game.width - inventory.getWidth(), (int) y + Game.height - inventory.getHeight(), 188, 24}) + " " + x + " " + y));
            inventory.draw(-x + Game.width - inventory.getWidth(), -y + Game.height - inventory.getHeight());
            if (In.mouseIsOver(new int[]{(int) -x + Game.width - inventory.getWidth(), (int) -y + Game.height - inventory.getHeight(), 188, 24})) {
                if (In.buttonHeld("mmb")) {
                    x = Game.width - In.getMouse()[0] - inventory.getWidth() / 2;
                    y = Game.height - In.getMouse()[1] - inventory.getHeight() + 12;
                }
            }
        }
    }

}
