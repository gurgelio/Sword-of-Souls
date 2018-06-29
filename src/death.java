import org.newdawn.slick.*;
import java.awt.Font;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class death extends BasicGameState {

    private Image bg;
    private int stateid;
    private Button bt;

    death(int id){
        stateid = id;
    }

    @Override
    public int getID() {
        return stateid;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        In.init();
        Font font = new java.awt.Font("Tahome", java.awt.Font.PLAIN, 35);
        bg = new Image("img/winScreen.png");;
        bt = new Button("Errou!", Game.width/2, (int) (Game.height * 0.5), new Font("Verdana", Font.PLAIN, 45), new Color(59, 66, 60, 255));
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        bg.draw(0,0,1366,768);
        bt.render();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame sbg, int i) throws SlickException {
        In.update();
        if (In.keyPressed("escape")) sbg.enterState(0);
    }
}