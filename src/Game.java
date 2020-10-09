/*
Classe principal
 */

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

class Game extends StateBasedGame{

    static final String gamename = "Sword of Souls";
    private static final int menu = 0;
    private static final int play = 1;
    private static final int win = 2;
    private static final int death = 3;
    static int height = 600;
    static int width = 800;

    //define o nome do jogo e adiciona os estados ao Game
    private Game(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new winState(win));
        this.addState(new death(death));
    }

    //Inicia os estados do jogo (menu e ingame) e define menu como inicial
    public void initStatesList(GameContainer gc){
        this.enterState(menu);
    }

    public static void main(String[] args){
        //inicialização da janela
        AppGameContainer appgc;
        try{
            appgc = new AppGameContainer(new Game(gamename));
            appgc.setDisplayMode(width, height, false);
            appgc.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
