package pt.upskills.projeto.objects.Statics.CatchOrDropItem;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.FireTile;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.DinamicObject;
import pt.upskills.projeto.objects.Dinamics.*;
import pt.upskills.projeto.objects.StaticObject;
import pt.upskills.projeto.objects.Statics.BuildingScene.Wall;
import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;

public class Fireballs extends StaticObject implements FireTile {
    private Position position;

    public Fireballs(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "FireFlames";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
    @Override
    public boolean validateImpact() {
        return stopFireBallOnWall(Hero.lastDirection);
    }

    public boolean stopFireBallOnWall(Direction lastDirection) {
        for(ImageTile tile: Engine.tiles){
            if (tile instanceof Wall || tile instanceof Skeleton || tile instanceof Bat ||
            tile instanceof BadGuy || tile instanceof Thief) {
                if (Hero.lastDirection == Direction.RIGHT){
                    if (tile.getPosition().equals(getPosition())) {
                        return removeFireball(tile);
                    }
                }
                if (Hero.lastDirection == Direction.LEFT){
                    if (tile.getPosition().equals(getPosition())){
                        return removeFireball(tile);
                    }
                }
                if (Hero.lastDirection == Direction.UP){
                    if (tile.getPosition().equals(getPosition())){
                        return removeFireball(tile);
                    }
                }
                if (Hero.lastDirection == Direction.DOWN){
                    if (tile.getPosition().equals(getPosition())){
                        return removeFireball(tile);
                    }
                }
            }
        }
        return true;
    }

    public boolean removeFireball(ImageTile tile){
        Engine.tiles.remove(Fireballs.this);
        fireBallAttackEnemy(tile);
        return false;
    }

    public void fireBallAttackEnemy(ImageTile tile){
        DinamicObject.inCaseEnemy(position);
    }

}
