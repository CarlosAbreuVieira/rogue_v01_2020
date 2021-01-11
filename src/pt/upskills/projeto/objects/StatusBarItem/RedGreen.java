package pt.upskills.projeto.objects.StatusBarItem;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.StaticObject;
import pt.upskills.projeto.rogue.utils.Position;

public class RedGreen extends StaticObject implements ImageTile {
    private Position position;

    public RedGreen(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "RedGreen";
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
