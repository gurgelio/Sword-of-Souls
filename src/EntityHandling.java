/*
Gerencia múltiplos personagem a partir de uma ArrayList
 */

import java.util.ArrayList;

abstract class EntityHandling {

    static void render(ArrayList<Entity> entities){
        for(Entity e : entities) {
            for (Action act : e.charAnimation) act.render(e.pos.x, e.pos.y);
        }
    }

    static void update(ArrayList<Entity> entities, int delta, Mapa map, Larry larry){
        for(Entity e : entities){
            e.update(delta, map, larry);
        }
    }

    static void deleteDeadEntities(ArrayList<Entity> entities){
        ArrayList<Entity> delete = new ArrayList<>();
        for(Entity e : entities) if(e.hp <= 0 && e.charAnimation.get(0).isStopped()) delete.add(e);
        entities.removeAll(delete);
    }

    //remove todos os personagens exceto o jogador para transição de mapa
    static void clearArea(ArrayList<Entity> entities){
        Larry aux = (Larry) entities.get(0);
        entities.clear();
        entities.add(aux);
    }
}
