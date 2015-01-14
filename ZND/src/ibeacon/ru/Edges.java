package ibeacon.ru;

/**
 * Created by Alexander on 24.09.2014.
 */
public class Edges {

    private int x;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int y;

    Edges(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object a) {
        if (this.x == ((Edges)a).x && this.y ==((Edges) a).y) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return 10*x+y;
    }


    public boolean checkEdge(int a){
        if (x==a)return true;
        else return false;
    }

}
