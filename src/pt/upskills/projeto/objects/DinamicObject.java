package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.game.Level;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Dinamics.*;
import pt.upskills.projeto.objects.Statics.BuildingScene.DoorOpen;
import pt.upskills.projeto.objects.Statics.BuildingScene.Wall;
import pt.upskills.projeto.objects.StatusBarItem.Red;
import pt.upskills.projeto.objects.StatusBarItem.RedGreen;
import pt.upskills.projeto.objects.StatusBarItem.StatusBar;
import pt.upskills.projeto.rogue.utils.Position;
import pt.upskills.projeto.rogue.utils.Vector2D;
import static pt.upskills.projeto.game.Level.*;

public abstract class DinamicObject extends GameObject {

    public static StatusBar statusBar = new StatusBar();

    public DinamicObject(Position position){
        super(position);
    }

    public abstract void setRecordLife(int recordLife);
    public abstract int getRecordLife();
    public abstract void setLife(int life);
    public abstract int getLife();
    public abstract void setAttack(int attack);
    public abstract int getAttack();
    public abstract String getName();
    public abstract void move();

    public int getRandomNumber(int minNumber, int maxNumber){
        return (int)(Math.random() * (maxNumber - minNumber) + minNumber) ;
    }

    public static boolean inCaseEnemy(Position newPosition){
        for (ImageTile tile: Engine.tiles){
            if (tile instanceof DinamicObject){
                if (tile.getPosition().equals(newPosition)){
                    if (((DinamicObject) tile).getLife() > 1){
                        ((DinamicObject) tile).setLife(((DinamicObject) tile).getLife() - hero.getAttack());
                        System.out.println(tile.getName() + " | Vida: " + ((DinamicObject) tile).getLife() + " | Força do Ataque Heroi: " + hero.getAttack());
                        Hero.score += 10;
                    }
                    if (((DinamicObject) tile).getLife() <= 1){
                        System.out.println(tile.getName() + " | Naaaaaoooo!!! Morriiiiii...");
                        Engine.gui.removeImage(tile);
                        Engine.tiles.remove(tile);
                        ((DinamicObject) tile).setLife(((DinamicObject) tile).getRecordLife());
                        Hero.score += 50;
                    }

                    return true;
                }
            }
        }
        return false;
    }

    public Position damageHero(Position position, Position newPosition){

        for (ImageTile tile: Engine.tiles) {
            if (tile instanceof Hero) {
                if (tile.getPosition().equals(newPosition)) {
                    if(hero.getLife() > 1) {
                        hero.setLife(hero.getLife() - getAttack());
                        if ((hero.getLife() % 2) != 0) {
                            StatusBar.status.add(new RedGreen(new Position((statusBar.getCounterStatusBarRedGreen()), 0)));
                            statusBar.setCounterStatusBarRedGreen(statusBar.getCounterStatusBarRedGreen() - 1);
                            Engine.gui.newStatusImages(StatusBar.status);
                        }
                        if ((hero.getLife() % 2) == 0) {
                            StatusBar.status.add(new Red(new Position((statusBar.getCounterStatusBarRed()), 0)));
                            statusBar.setCounterStatusBarRed(statusBar.getCounterStatusBarRed() - 1);
                            Engine.gui.newStatusImages(StatusBar.status);
                        }
                        Hero.score -= 10;
                    }
                    if (hero.getLife() <= 1) {
                        Hero.setIsHeroAlive(false);
                        System.out.println("Hero | Morriiiiii...");
                        StatusBar.status.add(new Red(new Position(3, 0)));
                        Item.inventory.clear();
                        gainLifeHero();
                        Level.restartLevel();
                        return position;
                    }
                    System.out.println("Heroi | Vida: " + hero.getLife());
                    return position;
                }
            }
        }
        return newPosition;
    }

    public static void gainLifeHero(){
        hero.setLife(8);
        statusBar.creatLifeBarGreen(6);
        statusBar.setCounterStatusBarRed(6);
        statusBar.setCounterStatusBarRedGreen(6);
        Engine.gui.newStatusImages(StatusBar.status);
    }

    public Position inCaseOfWallOrDoors(Position position, Position newPosition){
        for (ImageTile tile: Engine.tiles){
            if (tile instanceof Wall || tile instanceof DoorOpen){
                if (tile.getPosition().equals(newPosition)){
                    move();
                    return position;
                }
            }
        }
        return newPosition;
    }

    public Position enemyRandomMove(int minRandomNumber, int maxRandomNumber){
        Position newPosition = null;
        int randomNumber = getRandomNumber(minRandomNumber, maxRandomNumber);

        if (randomNumber == 0) {
            newPosition = getPosition().plus(new Vector2D(-1, 0));
        }
        if (randomNumber == 1) {
            newPosition = getPosition().plus(new Vector2D(1, 0));
        }
        if (randomNumber == 2) {
            newPosition = getPosition().plus(new Vector2D(0, 1));
        }
        if (randomNumber == 3) {
            newPosition = getPosition().plus(new Vector2D(0, -1));
        }
        if (randomNumber == 4) {
            newPosition = getPosition().plus(new Vector2D(-1, -1));
        }
        if (randomNumber == 5) {
            newPosition = getPosition().plus(new Vector2D(1, 1));
        }
        if (randomNumber == 6) {
            newPosition = getPosition().plus(new Vector2D(-1, 1));
        }
        if (randomNumber == 7) {
            newPosition = getPosition().plus(new Vector2D(1, -1));
        }
        return newPosition;
    }
}
