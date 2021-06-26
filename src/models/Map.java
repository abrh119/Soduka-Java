//Name of the package
package models;

//all the data thats linked tot the map that we are going to draw  will be held in tihs
  
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import utill.FixedMapElements;
import static utill.FixedMapElements.CRATE;
import static utill.FixedMapElements.DIAMOND;
import static utill.FixedMapElements.FLOOR;
import static utill.FixedMapElements.OUTSIDE_OF_WALL;
import static utill.FixedMapElements.WALL;
import static utill.FixedMapElements.WAREHOUSE_KEEPER;

/**
*the class is defined by this function
*for game board
 */

public class Map {

//global variables
    private MapElement[][] myMap;


    private Integer lgth;

 
    private Integer height;


    private Coord warehouseKeeperLocation;

    private Boolean complete;


    private Integer numMoves;


//constructor
    public Map() {
        this.complete = false;
        this.height = 0;
        this.lgth = 0;
        this.numMoves = 0;
    }

//analysis for winning of the game
    public Boolean checkForWin() {
        for (int mLine = 0; mLine < this.height; mLine++) {
            for (int mColumn = 0; mColumn < this.lgth; mColumn++) {
                if (this.myMap[mLine][mColumn] instanceof Crate) {
                    if (this.myMap[mLine][mColumn].getUnderneath() instanceof Diamond) {
                        complete = true;
                    } else {
                        complete = false;
                        return complete;
                    }

                }
            }

        }

        return complete;
    }

 
    public void displayMap() {

    }

    public Coord findWarehouseKeeper() {
        return warehouseKeeperLocation;
    }

//  getter for Height
    public Integer getHeight() {
        return height;
    }

    //the map elements are returned at the given coordinates
    public MapElement getElement(Integer x, Integer y) {
        return this.myMap[x][y];
    }

    //Returns map length
    public Integer getLgth() {
        return lgth;
    }

    //Returns map 
    public MapElement[][] getMap() {
        return myMap;
    }

    //Returns number of moves 
    public Integer getNumMoves() {
        return numMoves;
    }

    //Returns if or if there is not a block           
    public Boolean isObstacleAhead(Integer x, Integer y) {
        return this.myMap[x][y].getObs();
    }

    //the object is to move in the correct path
    public Boolean isPushableObject(Integer x, Integer y) {
        return this.myMap[x][y].getCanBePushed();
    }

    //acqquire images from each classes to build the game
    public void loadMap(String mapName) {

        this.complete = false;
        this.height = 0;
        this.lgth = 0;
        this.numMoves = 0;
        File file = new File(mapName);

        BufferedReader mBufferedReader;
        String mCurrentLine;
        StringBuilder mAllLines = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            mBufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((mCurrentLine = mBufferedReader.readLine()) != null) {
                this.height++;
                if (this.lgth < mCurrentLine.length()) {
                    this.lgth = mCurrentLine.length();
                }
            }
            fileInputStream.getChannel().position(0);

            while ((mCurrentLine = mBufferedReader.readLine()) != null) {
                while ((mCurrentLine.length() % this.lgth) != 0) {
                    mCurrentLine += OUTSIDE_OF_WALL;
                    System.out.println(mCurrentLine);
                }
                mAllLines.append(mCurrentLine);
            }

            mBufferedReader.close();

            this.myMap = new MapElement[this.height][this.lgth];
            for (int mLine = 0; mLine < this.height; mLine++) {
                for (int mColumn = 0; mColumn < this.lgth; mColumn++) {
                    Coord mCoord = new Coord(mColumn, mLine);
                    char mCurrentChar = mAllLines.charAt(mLine * this.lgth + mColumn);
                    Floor mUnderneathElement = mUnderneathElement = new Floor(false, false, "src/imgs/floor.png", FixedMapElements.FLOOR_STRING, false, mCoord.getXCoord(), mCoord.getYCoord());
                    switch (mCurrentChar) {

                        case CRATE:
                            Crate mTopCrate = new Crate(true, false, "src/imgs/crate.png", FixedMapElements.CRATE_STRING, true, mCoord.getXCoord(), mCoord.getYCoord());
                            mTopCrate.setUnderneath(mUnderneathElement);
                            this.myMap[mLine][mColumn] = mTopCrate;

                            break;

                        case WAREHOUSE_KEEPER:

                            setLocation(mColumn, mLine);

                            WarehouseKeeper mTopElement = new WarehouseKeeper(true, false, "src/imgs/warehousekeeper.png", FixedMapElements.WAREHOUSE_KEEPER_STRING, true, mCoord.getXCoord(), mCoord.getYCoord());
                            mTopElement.setUnderneath(mUnderneathElement);
                            this.myMap[mLine][mColumn] = mTopElement;
                            break;

                        case WALL:
                            this.myMap[mLine][mColumn]
                                    = new Wall(false, false, "src/imgs/wall.png", FixedMapElements.WALL_STRING, true, mCoord.getXCoord(), mCoord.getYCoord());
                            break;

                        case DIAMOND:
                            Diamond mTop = new Diamond(false, false, "src/imgs/diamond.png", FixedMapElements.DIAMOND_STRING, false, mCoord.getXCoord(), mCoord.getYCoord());
                            mTop.setUnderneath(mUnderneathElement);
                            this.myMap[mLine][mColumn] = mTop;
                            break;

                        case FLOOR:
                            this.myMap[mLine][mColumn]
                                    = new Floor(false, false, "src/imgs/floor.png", FixedMapElements.FLOOR_STRING, false, mCoord.getXCoord(), mCoord.getYCoord());
                            break;

                        case OUTSIDE_OF_WALL:
                            this.myMap[mLine][mColumn]
                                    = new Wall(false, false, "src/imgs/wall.png", FixedMapElements.WALL_STRING, true, mCoord.getXCoord(), mCoord.getYCoord());
                            break;
                        default:

                            throw new Exception("Cant Reade The  Map Charator ! Charactor " + mCurrentChar);

                    }

                }

            }

            if (this.findWarehouseKeeper() == null) {
                throw new Exception("Cannot Find WarehouseKeeper!");
            }

        } catch (Exception ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    
    // Move Objects on the map
    public void move(Integer x, Integer y, Integer nx, Integer ny) {
        MapElement mOldUnderneath = myMap[y][x].getUnderneath();
        WarehouseKeeper mOldWarehouseKeeper;
        MapElement mNewUnderneath = myMap[ny][nx];

        if (myMap[y][x] instanceof WarehouseKeeper) {
            mOldWarehouseKeeper = (WarehouseKeeper) myMap[y][x];
            mOldWarehouseKeeper.setUnderneath(mNewUnderneath);
            myMap[ny][nx] = mOldWarehouseKeeper;
            setLocation(nx, ny);
            numMoves += 1;
        } else {
            MapElement mOldTop = myMap[y][x];
            mOldTop.setUnderneath(mNewUnderneath);
            myMap[ny][nx] = mOldTop;
        }
        myMap[y][x] = mOldUnderneath;
        checkForWin();

    }

    
    // Moves the playable objcts in the map ( warehouse and objects)
    public Boolean moveWarehouseKeeper(Integer dir) {
        Integer xCurrCodWarehouseKeeper = this.findWarehouseKeeper().getXCoord();
        Integer yCurrCodWarehouseKeeper = this.findWarehouseKeeper().getYCoord();
        Integer yNewCodWarehouseKeeper;
        Integer yNewCoord;
        Integer xNewCodWarehouseKeeper;
        Integer xNewCoord;

        switch (dir) {
            case KeyEvent.VK_UP:
                yNewCodWarehouseKeeper = yCurrCodWarehouseKeeper + (-1);
                yNewCoord = yNewCodWarehouseKeeper + (-1);
                if (this.myMap[yNewCodWarehouseKeeper][xCurrCodWarehouseKeeper].getObs()) {
                    if (this.myMap[yNewCodWarehouseKeeper][xCurrCodWarehouseKeeper].getCanBePushed()) {
                        if (this.myMap[yNewCoord][xCurrCodWarehouseKeeper].getObs()) {
                            return false;

                        } else {
                            move(xCurrCodWarehouseKeeper, yNewCodWarehouseKeeper, xCurrCodWarehouseKeeper, yNewCoord);
                            move(xCurrCodWarehouseKeeper, yCurrCodWarehouseKeeper, xCurrCodWarehouseKeeper, yNewCodWarehouseKeeper);
                            return true;
                        }
                    } else {
                        return false;
                    }

                } else {
                    if (this.myMap[yNewCodWarehouseKeeper][xCurrCodWarehouseKeeper].getCanBePushed()) {

                        if (this.myMap[yNewCoord][xCurrCodWarehouseKeeper].getObs()) {
                            return false;

                        } else {
                            move(xCurrCodWarehouseKeeper, yNewCodWarehouseKeeper, xCurrCodWarehouseKeeper, yNewCoord);
                            move(xCurrCodWarehouseKeeper, yCurrCodWarehouseKeeper, xCurrCodWarehouseKeeper, yNewCodWarehouseKeeper);
                            return true;
                        }
                    } else {
                        move(xCurrCodWarehouseKeeper, yCurrCodWarehouseKeeper, xCurrCodWarehouseKeeper, yNewCodWarehouseKeeper);
                        return true;

                    }

                }

            case KeyEvent.VK_DOWN:
                yNewCodWarehouseKeeper = yCurrCodWarehouseKeeper + (1);
                yNewCoord = yNewCodWarehouseKeeper + (1);
                if (this.myMap[yNewCodWarehouseKeeper][xCurrCodWarehouseKeeper].getObs()) {
                    if (this.myMap[yNewCodWarehouseKeeper][xCurrCodWarehouseKeeper].getCanBePushed()) {
                        if (this.myMap[yNewCoord][xCurrCodWarehouseKeeper].getObs()) {
                            return false;

                        } else {
                            move(xCurrCodWarehouseKeeper, yNewCodWarehouseKeeper, xCurrCodWarehouseKeeper, yNewCoord);
                            move(xCurrCodWarehouseKeeper, yCurrCodWarehouseKeeper, xCurrCodWarehouseKeeper, yNewCodWarehouseKeeper);
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    if (this.myMap[yNewCodWarehouseKeeper][xCurrCodWarehouseKeeper].getCanBePushed()) {

                        if (this.myMap[yNewCoord][xCurrCodWarehouseKeeper].getObs()) {
                            return false;

                        } else {
                            move(xCurrCodWarehouseKeeper, yNewCodWarehouseKeeper, xCurrCodWarehouseKeeper, yNewCoord);
                            move(xCurrCodWarehouseKeeper, yCurrCodWarehouseKeeper, xCurrCodWarehouseKeeper, yNewCodWarehouseKeeper);
                            return true;
                        }
                    } else {
                        move(xCurrCodWarehouseKeeper, yCurrCodWarehouseKeeper, xCurrCodWarehouseKeeper, yNewCodWarehouseKeeper);
                        return true;

                    }

                }
            case KeyEvent.VK_RIGHT:
                xNewCodWarehouseKeeper = xCurrCodWarehouseKeeper + (1);
                xNewCoord = xNewCodWarehouseKeeper + (1);
                if (this.myMap[yCurrCodWarehouseKeeper][xNewCodWarehouseKeeper].getObs()) {
                    if (this.myMap[yCurrCodWarehouseKeeper][xNewCodWarehouseKeeper].getCanBePushed()) {

                        if (this.myMap[yCurrCodWarehouseKeeper][xNewCoord].getObs()) {
                            return false;

                        } else {
                            move(xNewCodWarehouseKeeper, yCurrCodWarehouseKeeper, xNewCoord, yCurrCodWarehouseKeeper);
                            move(xCurrCodWarehouseKeeper, yCurrCodWarehouseKeeper, xNewCodWarehouseKeeper, yCurrCodWarehouseKeeper);
                            return true;
                        }
                    } else {

                        return false;
                    }
                } else {

                    if (this.myMap[yCurrCodWarehouseKeeper][xNewCodWarehouseKeeper].getCanBePushed()) {

                        if (this.myMap[yCurrCodWarehouseKeeper][xNewCoord].getObs()) {
                            return false;

                        } else {
                            move(xNewCodWarehouseKeeper, yCurrCodWarehouseKeeper, xNewCoord, yCurrCodWarehouseKeeper);
                            move(xCurrCodWarehouseKeeper, yCurrCodWarehouseKeeper, xNewCodWarehouseKeeper, yCurrCodWarehouseKeeper);
                            return true;
                        }
                    } else {
                        move(xCurrCodWarehouseKeeper, yCurrCodWarehouseKeeper, xNewCodWarehouseKeeper, yCurrCodWarehouseKeeper);
                        return true;

                    }

                }

            case KeyEvent.VK_LEFT:
                xNewCodWarehouseKeeper = xCurrCodWarehouseKeeper + (-1);
                xNewCoord = xNewCodWarehouseKeeper + (-1);
                if (this.myMap[yCurrCodWarehouseKeeper][xNewCodWarehouseKeeper].getObs()) {
                    if (this.myMap[yCurrCodWarehouseKeeper][xNewCodWarehouseKeeper].getCanBePushed()) {

                        if (this.myMap[yCurrCodWarehouseKeeper][xNewCoord].getObs()) {
                            return false;

                        } else {
                            move(xNewCodWarehouseKeeper, yCurrCodWarehouseKeeper, xNewCoord, yCurrCodWarehouseKeeper);
                            move(xCurrCodWarehouseKeeper, yCurrCodWarehouseKeeper, xNewCodWarehouseKeeper, yCurrCodWarehouseKeeper);
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    if (this.myMap[yCurrCodWarehouseKeeper][xNewCodWarehouseKeeper].getCanBePushed()) {

                        if (this.myMap[yCurrCodWarehouseKeeper][xNewCoord].getObs()) {
                            return false;

                        } else {
                            move(xNewCodWarehouseKeeper, yCurrCodWarehouseKeeper, xNewCoord, yCurrCodWarehouseKeeper);
                            move(xCurrCodWarehouseKeeper, yCurrCodWarehouseKeeper, xNewCodWarehouseKeeper, yCurrCodWarehouseKeeper);
                            return true;
                        }
                    } else {
                        move(xCurrCodWarehouseKeeper, yCurrCodWarehouseKeeper, xNewCodWarehouseKeeper, yCurrCodWarehouseKeeper);
                        return true;

                    }

                }

        }

        return false;
    }

    //Reset the number of moves 
    public void resetNumMoves() {
        this.numMoves = 0;

    }

    //sets warehousekeepers location to be spawned
    public void setLocation(Integer x, Integer y) {
        this.warehouseKeeperLocation = new Coord(x, y);
    }

}
