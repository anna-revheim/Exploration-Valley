package no.uib.inf101.sem2.ExploartionValley.model;

import no.uib.inf101.sem2.ExploartionValley.entity.entity;
import no.uib.inf101.sem2.ExploartionValley.view.gameView;

public class collisionCheck {
    
    gameView view;
    public collisionCheck(gameView view){
        this.view = view;    
    }

    public void tileCheck(entity entity){
        int entityLeftWorldX = entity.x + entity.collisionArea.x;
        int entityRightWorldX = entity.x + entity.collisionArea.x + entity.collisionArea.width;
        int entityTopWorldY = entity.y + entity.collisionArea.y;
        int entityBottomWorldY = entity.y + entity.collisionArea.y + entity.collisionArea.height;
    
        int entityLeftCol = entityLeftWorldX/view.tilesize;
        int entityRightcol = entityRightWorldX/view.tilesize;
        int entityTopRow = entityTopWorldY/view.tilesize;
        int entityBottomRow = entityBottomWorldY/view.tilesize;

        int tileNum1, tileNum2; //

        switch(entity.direction){ //checks all direcections.
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/view.tilesize;
                tileNum1 = view.tileM.mapTileNum[entityLeftCol][entityTopRow]; //sjekker venstreside
                tileNum2 = view.tileM.mapTileNum[entityRightcol][entityTopRow];
                //EVT BRUKE ITEMMAP. Lage en liste ut av det. då vil alle items som skal kolliderast få collision.
                break;
            case "down":
                break;
            case "left":
                break;
            case "right":
                break;
        }
    }
}
