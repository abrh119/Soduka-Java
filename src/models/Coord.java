//Name of the package
package models;
/**
*the function is defined by this class
*for x position and y position for the game
*This class inherit from mapeleent class
 */

public class Coord {
//global variables
    private Integer x;
    private Integer y;

//assigning the global variables
    public Coord(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
//getter
    public Integer getXCoord() {
        return x;
    }
//setter
    public void setXCoord(Integer xCoord) {
        this.x = xCoord;
    }
//getter
    public Integer getYCoord() {
        return y;
    }
//setter
    public void setYCoord(Integer yCoord) {
        this.y = yCoord;
    }

}
