package pt.upskills.projeto.objects.Dinamics;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.DinamicObject;
import pt.upskills.projeto.rogue.utils.Position;

public class Thief extends DinamicObject {
    private int life = 8;
    private int attack = 1;
    private int recordLife = 8;

    public Thief(Position position) {
        super(position);
    }

    @Override
    public void setRecordLife(int recordLife) {
        this.recordLife = recordLife;
    }
    @Override
    public int getRecordLife() {
        return recordLife;
    }
    @Override
    public int getLife() {
        return life;
    }
    @Override
    public void setLife(int life) {
        this.life = life;
    }
    @Override
    public int getAttack() {
        return attack;
    }
    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }
    @Override
    public String getName() {
        return "Thief";
    }

    @Override
    public void move() {
        Position newPosition = enemyRandomMove(4,8);

        if (getPosition() == inCaseOfWallOrDoors(getPosition(),newPosition)){
            return;
        }
        if (getPosition() == damageHero(getPosition(),newPosition)){
            return;
        }

        setPosition(newPosition);
    }
}
