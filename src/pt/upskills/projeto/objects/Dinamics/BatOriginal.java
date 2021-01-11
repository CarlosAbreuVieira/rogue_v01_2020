package pt.upskills.projeto.objects.Dinamics;

import pt.upskills.projeto.objects.DinamicObject;
import pt.upskills.projeto.rogue.utils.Position;

public class BatOriginal extends DinamicObject {
    private int life = 5;
    private int attack = 1;
    private int recordLife = 5;

    public BatOriginal(Position position) {
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
        return "BatOriginal";
    }

    @Override
    public void move() {
        Position newPosition = enemyRandomMove(0,2);

        if (getPosition() == inCaseOfWallOrDoors(getPosition(),newPosition)){
            return;
        }
        if (getPosition() == damageHero(getPosition(),newPosition)){
            return;
        }

        setPosition(newPosition);
    }
}
