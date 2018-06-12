import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.awt.Font;

class Menu extends BasicGameState{

    private Button name;
    private Button continueGame;
    private Button newGame;
    private Button loadGame;
    private Button quit;
    private Button save;

    Menu(int state){
    }

    @Override               //(53, 109, 61)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        name  = new Button(Game.gamename, Game.width/2, (int) (Game.height * 0.1), new Fonte("Tahoma", Font.PLAIN, 50, 59, 66, 60, 255));
        continueGame = new Button("Continue", (int) (Game.width*(0.5)), (int) (Game.height*(0.5)) , new Fonte("Verdana", Font.PLAIN, 35, 59, 66, 60, 255));
        newGame = new Button("New Game", (int) (Game.width*(0.5)), (int) (Game.height*(0.6)), new Fonte("Verdana", Font.PLAIN, 35, 59, 66, 60, 255));
        loadGame = new Button("Load", (int) (Game.width*(0.5)), (int) (Game.height*(0.7)), new Fonte("Verdana", Font.PLAIN, 35, 59, 66, 60, 255));
        quit = new Button("Quit", (int) (Game.width*(0.5)), (int) (Game.height*(0.9)), new Fonte("Verdana", Font.PLAIN, 35, 59, 66, 60, 255));
        save = new Button("Save", (int) (Game.width*(0.5)), (int) (Game.height*(0.8)), new Fonte("Verdana", Font.PLAIN, 35, 59, 66, 60, 255));
        In.init();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(new Image("img/bgnew.jpg"),0,0);
        Fonte.ShadowFont(name);
        Fonte.ShadowFont(continueGame);
        Fonte.ShadowFont(newGame);
        Fonte.ShadowFont(loadGame);
        Fonte.ShadowFont(quit);
        Fonte.ShadowFont(save);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        In.update();

        if(In.mouseIsOver(continueGame.area())){
            continueGame.setColor(200, 200, 200, 255);
            if (In.buttonPressed("lmb")){
                continueGame.held = true;
            }
            if (In.buttonReleased("lmb") && continueGame.held){
                continueGame.held = false;
                sbg.enterState(1);
            }
        } else {
            continueGame.setColor(59, 66, 60, 255);
            continueGame.held = false;
        }
        if(In.mouseIsOver(quit.area())){
            quit.setColor(200, 200, 200, 255);
            if(In.buttonPressed("lmb")) quit.held = true;

            if (In.buttonReleased("lmb") && quit.held) {
                gc.exit();
            }
        } else {
            quit.setColor(59, 66, 60, 255);
            quit.held = false;
        }
    }

    //retorna o ID do estado do jogo
    @Override
    public int getID() {
        return 0;
    }
}
