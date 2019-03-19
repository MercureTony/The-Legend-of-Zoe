public class Bonus {
    int dx,dy;
    double cost,heuristic;

    public Bonus(int dx,int dy,double cost,double heuristic){
        this.dx= dx;
        this.dy=dy;
        this.cost=cost;
        this.heuristic=heuristic;
    }

    public int checkDistance(Bonus start,Bonus end){
        if (start.heuristic < end.heuristic){
            return 1;
        }
        else if(start.heuristic == end.heuristic){
            return 0;
        }
        else{
            return -1;
        }
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void dijkstra(Mur mur,Bonus start,Bonus end){

    }
}
