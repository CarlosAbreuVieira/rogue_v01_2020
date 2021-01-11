package pt.upskills.projeto.objects.Statics.CatchOrDropItem;

import pt.upskills.projeto.objects.GameObject;
import pt.upskills.projeto.objects.Item;
import pt.upskills.projeto.rogue.utils.Position;

public class Hammer extends Item {

    public Hammer(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Hammer";
    }

}
