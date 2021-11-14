public class CoordSingleton {
    private static CoordSingleton coordSingleton = null;
    private int xCoord;
    private int yCoord;

    private CoordSingleton(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public static CoordSingleton getInstance()
    {
        if (coordSingleton == null) {
            coordSingleton = new CoordSingleton(0, 0);
        }
        return coordSingleton;
    }
    public String getCoords(){
        return "Cursor is at: (" + this.xCoord + ", " + this.yCoord + ")";
    }
    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
}
