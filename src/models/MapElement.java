//package name
package models;
/**
*This class is to define the function
*for game movements
 */
public class MapElement {
	
	//global variables
    private Boolean canBePushed;
    private Boolean isDestination;
 
    private String imgFilename;
    
    private Boolean obs;
    
    private String symbol;
    
 
    private Integer x;
    
 
    private Integer y;
    
 
    private MapElement underneath;

    public MapElement(Boolean canBePushed, Boolean isDestination, String imgFilename, String symbol, Boolean obs, Integer x, Integer y) {
        this.canBePushed = canBePushed;
        this.obs = obs;
        this.isDestination = isDestination;
        this.imgFilename = imgFilename;
        this.symbol = symbol;
        this.x = x;
        this.y = y;
    }

    public MapElement() {
    }
    
    
// To move object the method retunrs the variables 
    public Boolean getCanBePushed() {
        return canBePushed;
    }

    public void setCanBePushed(Boolean canBePushed) {
        this.canBePushed = canBePushed;
    }
// to reach the destination the methods return variables
    public Boolean getIsDestination() {
        return isDestination;
    }
//setter for destination
    public void setIsDestination(Boolean isDestination) {
        this.isDestination = isDestination;
    }

    public String getImgFilename() {
        return imgFilename;
    }

    public void setImgFilename(String imgFilename) {
        this.imgFilename = imgFilename;
    }
//getter
    public Boolean getObs() {
        return obs;
    }
//setter
    public void setObs(Boolean obs) {
        this.obs = obs;
    }
//getter
    public String getSymbol() {
        return symbol;
    }
//setter
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
//getter
    public Integer getX() {
        return x;
    }
//setter
    public void setX(Integer x) {
        this.x = x;
    }
//getter
    public Integer getY() {
        return y;
    }
//setter
    public void setY(Integer y) {
        this.y = y;
    }
//getter
    public MapElement getUnderneath() {
        return underneath;
    }
//setter
    public void setUnderneath(MapElement underneath) {
        this.underneath = underneath;
    }

}
