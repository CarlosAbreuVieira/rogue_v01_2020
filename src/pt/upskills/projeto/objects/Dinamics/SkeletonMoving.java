package pt.upskills.projeto.objects.Dinamics;

import pt.upskills.projeto.objects.DinamicObject;
import pt.upskills.projeto.rogue.utils.Position;

public class SkeletonMoving extends DinamicObject {
    private int life = 3;
    private int attack = 1;
    private int recordLife = 3;

    public SkeletonMoving(Position position) {
        super(position);
    }

    @Override
    public int getRecordLife() {
        return recordLife;
    }
    @Override
    public void setRecordLife(int recordLife) {
        this.recordLife = recordLife;
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
        return "SkeletonMoving";
    }

    @Override
    public void move() {
        Position newPosition = enemyRandomMove(0,4);

        if (getPosition().equals(inCaseOfWallOrDoors(getPosition(),newPosition))){
            return;
        }
        if (getPosition().equals(damageHero(getPosition(),newPosition))){
            return;
        }

        setPosition(newPosition);
    }
}
