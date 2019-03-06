abstract class Bloc {
    private int xCoordinate;
    private  int yCoordinate;


    public Bloc(int xCoordinate , int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getX(){
        return xCoordinate;
    }

    public int getY(){
        return yCoordinate;
    }

    public void setX(int x){
        this.xCoordinate = x;
    }

    public void setY(int y){
        this.yCoordinate = y;
    }

    public String affichage(){
        return "";
    }
}