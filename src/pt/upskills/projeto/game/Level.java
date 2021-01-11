package pt.upskills.projeto.game;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Dinamics.*;
import pt.upskills.projeto.objects.StaticObject;
import pt.upskills.projeto.objects.Statics.BuildingScene.DoorOpen;
import pt.upskills.projeto.objects.Statics.BuildingScene.DoorWay;
import pt.upskills.projeto.objects.Statics.BuildingScene.FireFlames;
import pt.upskills.projeto.objects.Statics.BuildingScene.Wall;
import pt.upskills.projeto.objects.Statics.CatchOrDropItem.*;
import pt.upskills.projeto.objects.StatusBarItem.StatusBar;
import pt.upskills.projeto.rogue.utils.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Level implements ImageTile{
    private static final Level INSTANCE = new Level();
    public static StatusBar statusBar = new StatusBar();
    public static Hero hero;

    private int level = 0;

    public static Level getInstance() {
        return INSTANCE;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level){ // Atribui um novo valor a varavel level
        this.level = level;
    }

    public static void nextLevel(int level){ // Metodo para fazer a mudança de level
        StaticObject.cleanTiles();
        StaticObject.generateFloor();
        readMapFile(Engine.tiles, level);
        Engine.gui.newImages(Engine.tiles);
        Engine.heroIsAlive = true;
    }

    public static void readMapFile(List<ImageTile> tiles, int level){ // Metodo para ler os ficheiros .txt
        try{
            Scanner scannerFile = new Scanner(new File("rooms/room" + level + ".txt")); // Scanner faz a leitura do ficheiro
            int j = 0; // É usado para incrementar uma nova linha

            while (scannerFile.hasNextLine()){ // Enquanto houver linhas escritas no ficheiro o loop não acaba
                String nextLine = scannerFile.nextLine(); // Copia a lonha lida para a String nextLine
                String[] split = nextLine.split(""); // Copia a String nextLine para dentro do vetor split, separando-a por caraterees

                inCaseOfCarater(split,j);

                j++; // Incrementa o j para ir para proxima linha
            }
        }catch (FileNotFoundException e){ // Caso não seja possivel ler o ficheiro, é exibida a mensagem de erro
            e.printStackTrace();
            System.out.println("Ficheiro não encontrado! Verifique o nome do ficheiro.");
        }
    }

    public static void inCaseOfCarater(String[] split, int j){
        for (int i = 0; i < split.length; i++){
            Position pos = new Position(i,j);

            if (split[0].equals("#")){ // Se encontrar # decrementa o j para não acrescentar uma nova linha no mapa
                j--;
                break;
            }
            if (split[i].equals("W")){ // Caso encontre o W, é criada uma parede nessa posição
                Engine.tiles.add(new Wall(pos));
            }
            if (split[i].equals("0")){ // Caso encontre o 0, é criada uma porta aberta nessa posição
                Engine.tiles.add(new DoorOpen(pos));
            }
            if (split[i].equals("1")){ // Caso encontre o 0, é criada uma porta aberta nessa posição
                Engine.tiles.add(new DoorWay(pos));
            }
            if (split[i].equals("g")){ // Caso encontre o g, é criado um goodmeat nessa posição
                Engine.tiles.add(new GoodMeat(pos));
            }
            if (split[i].equals("f")){ // Caso encontre o 0, é criada uma porta aberta nessa posição
                Engine.tiles.add(new FireFlames(pos));
            }
            if (split[i].equals("k")){ // Caso encontre o k, é criada uma chave nessa posição
                Engine.tiles.add(new Key(pos));
            }
            if (split[i].equals("B")){ // Caso encontre o B, é criado um morcego nessa posição
                Engine.tiles.add(new Bat(pos));
            }
            if (split[i].equals("T")){ // Caso encontre o B, é criado um morcego nessa posição
                Engine.tiles.add(new Thief(pos));
            }
            if (split[i].equals("G")){ // Caso encontre o G, é criado um BadGuy nessa posição
                Engine.tiles.add(new BadGuy(pos));
            }
            if (split[i].equals("H")){ // Caso encontre o H, é criado um Martelo nessa posição
                Engine.tiles.add(new Hammer(pos));
            }
            if (split[i].equals("s")){ // Caso encontre o W, é criada uma parede nessa posição
                Engine.tiles.add(new Sword(pos));
            }
            if (split[i].equals("S")){ // Caso encontre o S, é criado um skeleton nessa posição
                Engine.tiles.add(new Skeleton(pos));
            }
            if (split[i].equals("h")){ // Caso encontre o h, é criado um heroi nessa posição
                if (hero == null){
                    hero = new Hero(pos);
                    Engine.tiles.add(hero);
                    Engine.gui.addObserver(hero);
                } else {
                    hero.setPosition(pos);
                    Engine.tiles.add(hero);
                }
            }
        }
    }

    public static void restartLevel(){ // Metodo para fazer a mudança de level
        Engine.tiles.clear();
        Engine.gui.clearImages();
        Engine.gui.clearStatus();
        new StatusBar().creatStatusBar();
        getInstance().setLevel(0);
        StaticObject.generateFloor();
        readMapFile(Engine.tiles, 0);
        Engine.gui.newImages(Engine.tiles);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Position getPosition() {
        return null;
    }
}
