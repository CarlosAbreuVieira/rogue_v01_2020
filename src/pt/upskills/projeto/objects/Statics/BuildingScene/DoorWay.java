package pt.upskills.projeto.objects.Statics.BuildingScene;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class DoorWay implements ImageTile {

    private Position position;

    public DoorWay(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "DoorWay";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
