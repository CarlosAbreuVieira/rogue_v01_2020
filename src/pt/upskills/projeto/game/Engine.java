package pt.upskills.projeto.game;

import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.StatusBarItem.StatusBar;
import java.util.ArrayList;
import java.util.List;

public class Engine {
    public static ImageMatrixGUI gui = ImageMatrixGUI.getInstance(); // Instancia e cria ImageMatrixGui gui
    public static List<ImageTile> tiles = new ArrayList<>(); // Instancia e cria uma lista de tiles
    public static boolean heroIsAlive = true;

    public void init(){
        new StatusBar().creatStatusBar();
        Level.nextLevel(Level.getInstance().getLevel());
        gui.go();

        while (true){
            gui.update();
        }
    }

    public static void main(String[] args){
        Engine engine = new Engine();
        engine.init();
    }
}
