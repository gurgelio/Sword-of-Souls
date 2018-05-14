import java.awt.Font;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

class Menu extends BasicGameState{
    Menu(int state){
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //Font mf = new Font("Arial",Font.ITALIC, 30);
        TrueTypeFont fonte = new TrueTypeFont(new Font("Arial",Font.ITALIC, 30), false);
        g.drawImage(new Image("img/i.png"), 0,0);
        fonte.drawString(Game.gameheight/2 - fonte.getWidth("Larry Coppermann: The game!")/2, 30, "Larry Coppermann: The game!", Color.cyan);


    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{

    }

    @Override
    public int getID() {
        return 0;
    }
}
