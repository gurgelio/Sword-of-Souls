import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

class Menu extends BasicGameState{
    private HashMap<String, Button> buttons = new HashMap<>();

    Menu(int state){
    }

    @Override               //(53, 109, 61)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        buttons.put("name", new Button(Game.gamename, Game.width/2, (int) (Game.height * 0.1), new Fonte("Tahoma", Font.PLAIN, 50, 59, 66, 60, 255)));
        buttons.put("continue", new Button("Continue", (int) (Game.width*(0.5)), (int) (Game.height*(0.5)) , new Fonte("Verdana", Font.PLAIN, 35, 59, 66, 60, 255)));
        buttons.put("newgame", new Button("New Game", (int) (Game.width*(0.5)), (int) (Game.height*(0.6)), new Fonte("Verdana", Font.PLAIN, 35, 59, 66, 60, 255)));
        buttons.put("load", new Button("Load", (int) (Game.width*(0.5)), (int) (Game.height*(0.7)), new Fonte("Verdana", Font.PLAIN, 35, 59, 66, 60, 255)));
        buttons.put("quit", new Button("Quit", (int) (Game.width*(0.5)), (int) (Game.height*(0.9)), new Fonte("Verdana", Font.PLAIN, 35, 59, 66, 60, 255)));
        buttons.put("save", new Button("Save", (int) (Game.width*(0.5)), (int) (Game.height*(0.8)), new Fonte("Verdana", Font.PLAIN, 35, 59, 66, 60, 255)));
        In.init();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(new Image("img/bgnew.jpg"),0,0);
        for(String st : buttons.keySet()) Fonte.ShadowFont(buttons.get(st));
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        In.update();
        if(buttons.get("continue").isClicked()) sbg.enterState(1);
        if(buttons.get("quit").isClicked()) System.exit(0);

    }

    //retorna o ID do estado do jogo
    @Override
    public int getID() {
        return 0;
    }
}
