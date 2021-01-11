package pt.upskills.projeto.objects.Dinamics;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.game.FireBallThread;
import pt.upskills.projeto.game.Level;
import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.GameObject;
import pt.upskills.projeto.objects.DinamicObject;
import pt.upskills.projeto.objects.Item;
import pt.upskills.projeto.objects.StaticObject;
import pt.upskills.projeto.objects.Statics.BuildingScene.FireFlames;
import pt.upskills.projeto.objects.Statics.CatchOrDropItem.Fireballs;
import pt.upskills.projeto.objects.StatusBarItem.BlackTitle;
import pt.upskills.projeto.objects.StatusBarItem.StatusBar;
import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;

import java.awt.event.KeyEvent;
import java.util.*;

public class Hero extends GameObject implements Observer {
    private int life = 8;
    private int attack = 1;
    private int fireBallAttack = 4;
    public static int score = 0;
    public int counter = 0;
    public static boolean isHeroAlive = true;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int[] fireBalls = new int[]{1, 1, 1};
    public int[] itemSpace = new int[]{0, 0, 0};

    public int getFireBallAttack() {
        return fireBallAttack;
    }

    public static void setIsHeroAlive(boolean isHeroAlive) {
        Hero.isHeroAlive = isHeroAlive;
    }

    public static Direction lastDirection;

    public Hero(Position position) {
        super(position);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public String getName() {
        return "Hero";
    }

    /**
     * This method is called whenever the observed object is changed. This function is called when an
     * interaction with the graphic component occurs {{@link ImageMatrixGUI}}
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Integer keyCode = (Integer) arg;
        Position newPosition = null;

        if (isHeroAlive) {
            for (ImageTile tile : Engine.tiles) {
                if (tile instanceof DinamicObject) {
                    ((DinamicObject) tile).move();
                    if (!isHeroAlive){
                        fireBalls = new int[]{1, 1, 1}; 
                        setIsHeroAlive(true);
                        break;
                    }
                }
            }
        }


        if (keyCode == KeyEvent.VK_DOWN) { // Caso se carregue na tecla seta para baixo
            newPosition = getPosition().plus(Direction.DOWN.asVector());  // Nova posicao passa a ser um quadrado abaixo
            this.lastDirection = Direction.DOWN;
        }
        if (keyCode == KeyEvent.VK_UP) { // Caso se carregue na tecla seta para cima
            newPosition = getPosition().plus(Direction.UP.asVector()); // Nova posicao passa a ser um quadrado acima
            this.lastDirection = Direction.UP;
        }
        if (keyCode == KeyEvent.VK_LEFT) { // Caso se carregue na tecla seta para esquerda
            newPosition = getPosition().plus(Direction.LEFT.asVector()); // Nova posicao passa a ser um quadrado a esquerda
            this.lastDirection = Direction.LEFT;
        }
        if (keyCode == KeyEvent.VK_RIGHT) { // Caso se carregue na tecla seta para direita
            newPosition = getPosition().plus(Direction.RIGHT.asVector()); // Nova posicao passa a ser um quadrado a direita
            this.lastDirection = Direction.RIGHT;
        }
        if (keyCode == KeyEvent.VK_1){
            dropItemPosition(7);
        }
        if (keyCode == KeyEvent.VK_2) {
            dropItemPosition(8);
            return;
        }

        if (keyCode == KeyEvent.VK_3){
            dropItemPosition(9);
            return;
        }
        if (keyCode == KeyEvent.VK_SPACE){
           fireFireBall(lastDirection);
           return;
        }

        if (StaticObject.inCaseOfBuildingObject(newPosition)){
            return;
        }

        if (StaticObject.inCaseFireBall(newPosition)){
            return;
        }

        if (inCaseOfCatchOrDropItem(newPosition)){
            return;
        }
       if (DinamicObject.inCaseEnemy(newPosition)){
            return;
        }

       if(newPosition == null){
           return;
        }
       setPosition(newPosition); // Copia a newPosition para variavel position
       score--;
       scoreGame(score);
    }

    public void scoreGame(int score){
        if (score <= 0){
            setScore(0);
        } else {
            setScore(score);
        }
        System.out.println("Pontuação: " +  getScore());
    }

    public void fireFireBall(Direction lastDirection){
        if(lastDirection != null) {
            for (int i = 2; i >= 0; i--) {
                if (fireBalls[i] != 0) {
                    Fireballs fireballs = new Fireballs(getPosition());
                    FireBallThread fireBallThread = new FireBallThread(lastDirection, fireballs);
                    fireBallThread.start();
                    Engine.tiles.add(fireballs);
                    ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
                    gui.addImage(fireballs);
                    this.fireBalls[i] = 0;

                    for(ImageTile tile: StatusBar.status){
                        if (tile instanceof FireFlames){
                            StatusBar.status.remove(tile);
                            Engine.gui.removeStatusImage(tile);
                            Engine.gui.update();
                            break;
                        }
                    }
                    Engine.gui.newStatusImages(StatusBar.status);
                    break;
                }
            }
        }
    }

    public void dropItemPosition(int posNumber) {
        for (ImageTile tile : Item.inventory) {
            if (tile.getPosition().equals(new Position(posNumber, 0))) {
                ((Item) tile).setPosition(new Position(Level.hero.getPosition().getX(), Level.hero.getPosition().getY()));
                for (int i = 0; i < 3; i++){ // Este loop mete a posição do item a zero, assim pode receber outro item
                    if (posNumber == (7 + i)){
                        itemSpace[i] = 0;
                    }
                }
                Engine.gui.removeStatusImage(tile); // remove o tile do statusBar
                Engine.tiles.add(tile);
                Engine.gui.addImage(tile);
                Engine.gui.removeImage(this); // Ao remover o hero e voltar a mete-lo
                Engine.gui.addImage(this); // Ele fica por cima dos drops
                this.counter -= 1;
                break;
            }
        }
    }

    public boolean inCaseOfCatchOrDropItem(Position newPosition) {
        for (ImageTile tile : Engine.tiles) {
            if (tile instanceof Item) {
                if (counter < 3) {
                    if (tile.getPosition().equals(newPosition)) {
                        Engine.gui.removeImage(tile);
                        Engine.tiles.remove(tile);
                        if (tile.getName().equals("GoodMeat")) {
                            DinamicObject.gainLifeHero();
                            Hero.score += 10;
                            break;
                        }
                        for (int i = 0; i < 3; i++) {
                            if (itemSpace[i] == 0) {
                                ((GameObject) tile).setPosition(new Position(7 + i, 0));
                                itemSpace[i] = 1;
                                Hero.score += 10;
                                Item.inventory.add(tile);
                                this.counter += 1;
                                break;
                            }
                        }
                        Engine.gui.addStatusImage(tile);
                        break;
                    }
                }
            }
        }
        return false;
    }
}