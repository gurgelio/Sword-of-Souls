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
        g.drawImage(new Image("img/bg.jpg"),0,0);
        Fontes.Fonte1.drawString(Game.gamewidth/2 - Fontes.Fonte1.getWidth("Larry Coppermann")/2, 50, "Larry Coppermann", new Color(230,92,0,180));
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{

    }

    //retorna o ID do estado do jogo
    @Override
    public int getID() {
        return 0;
    }
}
