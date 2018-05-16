import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;


class Play extends BasicGameState{
    TiledMap map;
    Play(int state){
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        map = new TiledMap("map/mapa.tmx");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g){
        map.render(0,0);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{

    }

    //retorna o ID do estado do jogo
    @Override
    public int getID() {
        return 1;
    }
}
