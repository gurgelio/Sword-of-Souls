/*
Estado de jogo, menu inicial
 */

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.awt.Font;
import java.util.HashMap;

class Menu extends BasicGameState{
    private HashMap<String, Button> buttons = new HashMap<>();
    private Image bg;

    Menu(int state){
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        Font font = new Font("Tahome", Font.PLAIN, 35);
        buttons.put("name", new Button(Game.gamename, Game.width/2, (int) (Game.height * 0.1), new Font("Verdana", Font.PLAIN, 45), new Color(59, 66, 60, 255), new Color(59, 66, 60, 255)));
        buttons.put("continue", new Button("Continue", (int) (Game.width*(0.5)), (int) (Game.height*(0.5)) , font, new Color(59, 66, 60, 255), new Color(128, 128, 128, 255)));
        buttons.put("newgame", new Button("New Game", (int) (Game.width*(0.5)), (int) (Game.height*(0.6)), font, new Color(59, 66, 60, 255), new Color(128, 128, 128, 255)));
        buttons.put("load", new Button("Load", (int) (Game.width*(0.5)), (int) (Game.height*(0.7)), font, new Color(59, 66, 60, 255), new Color(128, 128, 128, 255)));
        buttons.put("save", new Button("Save", (int) (Game.width*(0.5)), (int) (Game.height*(0.8)), font, new Color(59, 66, 60, 255), new Color(128, 128, 128, 255)));
        buttons.put("quit", new Button("Quit", (int) (Game.width*(0.5)), (int) (Game.height*(0.9)), font, new Color(59, 66, 60, 255), new Color(128, 128, 128, 255)));
        bg = new Image("img/bgnew.jpg");
        In.init();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        bg.draw(0, 0, Game.width, Game.height);
        for(String st : buttons.keySet()) buttons.get(st).render();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        In.update();
        if(buttons.get("continue").isClicked()) {
            sbg.enterState(1);
        }
        if(buttons.get("newgame").isClicked()) {
            sbg.getState(1).init(gc, sbg);
            sbg.enterState(1);
        }
        if(buttons.get("quit").isClicked()) System.exit(0);

        //System.out.println(Controls.intendedActions());
    }

    @Override
    public int getID() {
        return 0;
    }
}
