//Name of the package
package models;

/**
*this function is defined by this class
*for warehousekeeper
*This class inherit from mapeleent class
 */
public class WarehouseKeeper extends MapElement {

    public WarehouseKeeper(Boolean canBePushed, Boolean isDestination, String imgFilename, String symbol,Boolean obs, Integer x, Integer y) {
        super(canBePushed, isDestination, imgFilename, symbol,obs ,x, y);
    }

}
