//Name of the package
package models;

/**
*this function is defined by this class
*for Wall
*This class inherit from mapeleent class
 */
public class Wall extends MapElement {

    public Wall(Boolean canBePushed, Boolean isDestination, String imgFilename, String symbol,Boolean obs, Integer x, Integer y) {
        super(canBePushed, isDestination, imgFilename, symbol,obs ,x, y);
    }

}
