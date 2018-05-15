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
        Fontes.ShadowFont("Larry Coppermann", Game.gamewidth/2 - Fontes.Fonte1.getWidth(Game.gamename)/2, 10, Fontes.Fonte1, Fontes.Vermelho1);
        //Fontes.Fonte2.drawString(Game.gamewidth/2 - Fontes.Fonte2.getWidth("Play")/2, 420, "Play", Fontes.Gold1);
        //Fontes.Fonte1.drawString(Game.gamewidth/2 - Fontes.Fonte1.getWidth(Game.gamename)/2 + 3, 12, Game.gamename, Fontes.Vermelho3);
        //Fontes.Fonte1.drawString(Game.gamewidth/2 - Fontes.Fonte1.getWidth(Game.gamename)/2, 12, Game.gamename, Fontes.Vermelho3);
        //Fontes.Fonte1.drawString(Game.gamewidth/2 - Fontes.Fonte1.getWidth(Game.gamename)/2 + 3, 10, Game.gamename, Fontes.Vermelho2);
        //Fontes.Fonte1.drawString(Game.gamewidth/2 - Fontes.Fonte1.getWidth(Game.gamename)/2, 10, Game.gamename, Fontes.Vermelho2);
        //Fontes.Fonte1.drawString(Game.gamewidth/2 - Fontes.Fonte1.getWidth(Game.gamename)/2 + 3, 7, Game.gamename, Fontes.Vermelho1);
        //Fontes.Fonte1.drawString(Game.gamewidth/2 - Fontes.Fonte1.getWidth(Game.gamename)/2, 7, Game.gamename, Fontes.Vermelho1);
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
