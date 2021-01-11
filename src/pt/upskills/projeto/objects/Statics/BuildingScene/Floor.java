package pt.upskills.projeto.objects.Statics.BuildingScene;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.StaticObject;
import pt.upskills.projeto.rogue.utils.Position;

public class Floor extends StaticObject implements ImageTile {
    private Position position;

    public Floor(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "Floor";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
