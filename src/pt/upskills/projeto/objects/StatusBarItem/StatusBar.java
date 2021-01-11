package pt.upskills.projeto.objects.StatusBarItem;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Statics.BuildingScene.FireFlames;
import pt.upskills.projeto.rogue.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class StatusBar {
    public static List<ImageTile> status = new ArrayList<>();
    private int counter = 0;
    private int barLifeGreen = 6;
    private int barLifeRed = 6;
    private int counterStatusBarRed = 6;
    private int counterStatusBarRedGreen = 6;
    private int counterStatusBarLife = 6;

    public StatusBar(){
        getStatus();
    }

    public void getStatus(){
        Engine.gui.newStatusImages(status);
    }

    public int getCounterStatusBarRed() {
        return counterStatusBarRed;
    }
    public void setCounterStatusBarRed(int counterStatusBarRed) {
        this.counterStatusBarRed = counterStatusBarRed;
    }
    public int getCounterStatusBarRedGreen() {
        return counterStatusBarRedGreen;
    }
    public void setCounterStatusBarRedGreen(int counterStatusBarRedGreen) {
        this.counterStatusBarRedGreen = counterStatusBarRedGreen;
    }


    public void creatStatusBar(){
        creatBlackBar();
        creatLifeBarGreen(barLifeGreen);
        creatFireBall();
    }

    public void creatBlackBar(){
        for (int i = 0; i < 10; i++){
            status.add(new BlackTitle(new Position(i,0)));
        }
    }

    public void creatLifeBarGreen(int barLifeGreen){
        for (int i = 3; i <= barLifeGreen; i++){
            status.add(new Green(new Position(i,0)));
        }
    }

    public void creatFireBall(){
        for (int i = 0; i < 3; i++){
            status.add(new FireFlames(new Position(i,0)));
        }
    }
}
