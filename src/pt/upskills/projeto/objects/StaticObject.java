package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.game.Level;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Dinamics.Hero;
import pt.upskills.projeto.objects.Statics.BuildingScene.*;
import pt.upskills.projeto.objects.StatusBarItem.StatusBar;
import pt.upskills.projeto.rogue.utils.Position;

import static pt.upskills.projeto.objects.StatusBarItem.StatusBar.status;

public abstract class StaticObject implements ImageTile {

    public abstract String getName();

    public static void generateFloor(){ // Metodo para criar o chão
        for(int i=0; i<10; i++){  // Faz o loop para preencher na os espaços na horizontal
            for(int j=0; j<10; j++){ // Faz o loop para acrescentar uma nova linha na vertical
                Engine.tiles.add(new Floor(new Position(i, j))); // Adiciona um Floor na nova posição
            }
        }
    }

    public static void cleanTiles(){ // Metodo para limpar os tiles criados
        Engine.tiles.clear();
        Engine.gui.clearImages();
    }

    public static boolean inCaseOfBuildingObject(Position newPosition){
        for (ImageTile tile: Engine.tiles){
            if (tile instanceof Wall){
                if (tile.getPosition().equals(newPosition)){
                    return true;
                }
            }
            if (tile instanceof DoorOpen){
                if (tile.getPosition().equals(newPosition)){
                    Engine.tiles.remove(tile);
                    int nextLevel = Level.getInstance().getLevel() + 1;
                    Level.getInstance().setLevel(nextLevel);
                    Level.nextLevel(Level.getInstance().getLevel());
                    return true;
                }
            }
            if (tile instanceof DoorWay){
                if (tile.getPosition().equals(newPosition)){
                    Engine.tiles.remove(tile);
                    int nextLevel = Level.getInstance().getLevel() - 1;
                    if(nextLevel != 0){
                        Level.getInstance().setLevel(nextLevel);
                    }
                    if(nextLevel <= 0){
                        Level.getInstance().setLevel(0);
                    }
                    Level.nextLevel(Level.getInstance().getLevel());
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean inCaseFireBall(Position newPosition){
        for (ImageTile tile: Engine.tiles){
            if (tile instanceof FireFlames) {
                if (tile.getPosition().equals(newPosition)) {
                    Engine.gui.removeImage(tile);
                    Engine.tiles.remove(tile);
                    for (int i = 0; i < 3; i++) {
                        if (Level.hero.fireBalls[i] == 0) {
                            StatusBar.status.add(new FireFlames(new Position(i, 0)));
                            Level.hero.fireBalls[i] = 1;
                            Hero.score += 10;
                            break;
                        }
                    }
                }
                break;
            }
        }
        return false;
    }
}
