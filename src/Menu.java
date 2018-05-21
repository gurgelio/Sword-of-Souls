import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.awt.Font;

class Menu extends BasicGameState{

    In in = new In();
    private Fonte fonte1 = new Fonte("Tahoma", Font.PLAIN, 50, 204, 0, 0, 255);
    private Fonte fonte2 = new Fonte("Verdana", Font.PLAIN, 35, 204, 153, 0, 255);
    private Button name = new Button(Game.gamename, Game.width/2, (int) (Game.height *0.1), fonte1);
    private Button play = new Button("play", Game.width /2, Game.height /2, fonte2);

    Menu(int state){
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(new Image("img/bg.jpg"),0,0);
        Fonte.ShadowFont(play);
        Fonte.ShadowFont(name);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        if(In.mouseIsOver(play.area())){
            play.setColor(200, 150, 0, 100);
            if (In.mouseIsPressed(1)){
                sbg.enterState(1);
            }
        } else play.setColor(204, 0, 0, 255);
    }

    //retorna o ID do estado do jogo
    @Override
    public int getID() {
        return 0;
    }
}
