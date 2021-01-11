package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Item extends GameObject {
    public static List<ImageTile> inventory = new ArrayList<>();
    public Item(Position position) {
        super(position);
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
    }
}
