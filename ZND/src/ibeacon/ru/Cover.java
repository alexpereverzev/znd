package ibeacon.ru;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Alexander on 18.09.2014.
 */
public class Cover {

   private int weighl;



   private ArrayList<Integer> index_i=new ArrayList<Integer>();

    public ArrayList<Integer> getIndex_i() {
        return index_i;
    }





    public int getWeighl() {
        return weighl;
    }

    public void setWeighl(int weighl) {
        this.weighl = weighl;
    }

    public LinkedList<Integer[]> getItem() {
        return item;
    }

    public void setItem(LinkedList<Integer[]> item) {
        this.item = item;
    }

    private LinkedList<Integer[]> item=new LinkedList<Integer[]>();

    public void addItem(Integer [] a){
        item.add(a);
        weighl=weighl+a[2];
    }

    public void addIndex(int a){
       index_i.add(a);
    }


}
