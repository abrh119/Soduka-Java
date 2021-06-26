//Name of the package
package models;

public class SokobanGame {

    // stores the on going map in game
    private Map myGame;

    public SokobanGame() {
        myGame = new Map();
    }
//getter for map
    public Map getMap() {
        return myGame;
    }

    
    // from  methods it loads the map
    public void loadMap(String mapName) {
        myGame.loadMap(mapName);
    }

}
