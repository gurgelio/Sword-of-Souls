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
        Fontes.ShadowFont(Game.gamename, Game.gamewidth/2 - Fontes.Fonte1.getWidth(Game.gamename)/2, 10, Fontes.Fonte1, Fontes.Vermelho);
        Fontes.ShadowFont("Play", Game.gamewidth/2 - Fontes.Fonte2.getWidth("Play")/2, (int)(Game.gameheight*0.8), Fontes.Fonte2, Fontes.Gold);
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
