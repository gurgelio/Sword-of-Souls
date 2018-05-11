import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

class Game extends StateBasedGame{

    private static final String gamename = "Batatão";
    private static final int menu = 0;
    private static final int play = 1;

    private Game(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
    }

    //Inicia os estados do jogo (menu e ingame) e define menu como inicial
    public void initStatesList(GameContainer gc) throws SlickException{
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.enterState(menu);
    }

    public static void main(String[] args){
        //inicialização da janela
        AppGameContainer appgc;
        try{
            appgc = new AppGameContainer(new Game(gamename));
            appgc.setDisplayMode(800, 600, false);
            appgc.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
