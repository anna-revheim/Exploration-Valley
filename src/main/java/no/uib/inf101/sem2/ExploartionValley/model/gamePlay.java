package no.uib.inf101.sem2.ExploartionValley.model;

//This class is made to keep the gamestory  going. We will achieve this with a bunch of boolean variables.

public class gamePlay {
    String mapname;
    public boolean choppedTree; //When choppped tree, opens possibility of going over bridge.

    public gamePlay() {
        this.mapname = "maps3.txt";
    }


    public String getMap() {
        return this.mapname;
    }

    public int getCols() {
        if (this.mapname == "maps3.txt") {
            return 60;
        } else {
            return 60;
        }
    }

    public int getRows() {
        if (this.mapname == "maps3.txt") {
            return 40;
        } else {
            return 40;
        }
    }

    public int getXBorder(){
        if(this.mapname == "maps3.txt"){
            return 160;
        }
        else{
            return 160; //example.
        }
    }

    public int getYBorder(){
        if(this.mapname == "maps3.txt"){
            return 160;
        }
        else{
            return 0;
        }
    }
}
