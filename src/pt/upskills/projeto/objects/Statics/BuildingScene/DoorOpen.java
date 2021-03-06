package pt.upskills.projeto.objects.Statics.BuildingScene;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class DoorOpen implements ImageTile {

    private Position position;

    public DoorOpen(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "DoorOpen";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
