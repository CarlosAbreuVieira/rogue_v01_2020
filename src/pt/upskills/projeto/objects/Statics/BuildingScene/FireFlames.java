package pt.upskills.projeto.objects.Statics.BuildingScene;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.StaticObject;
import pt.upskills.projeto.rogue.utils.Position;

public class FireFlames extends StaticObject implements ImageTile {
    private Position position;

    public FireFlames(Position position) {
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
}
