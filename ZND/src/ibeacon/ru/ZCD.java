package ibeacon.ru;

import java.util.LinkedList;

/**
 * Created by Alexander on 26.09.2014.
 */
public class ZCD {

    private GraphR graphR;

    private Integer[] root_vertex=new Integer[2];


    public GraphR getGraphR() {
        return graphR;
    }

    public void setGraphR(GraphR graphR) {
        this.graphR = graphR;
    }

    private LinkedList<Integer> Wr=new LinkedList<Integer>();

    private LinkedList<Integer[]> WrEdges=new LinkedList<Integer[]>();

    public void addWrEdges(Integer [] a){
        WrEdges.add(a);
    }

    public LinkedList<Integer[]> getWrEdges(){
        return WrEdges;
    }

    public LinkedList<Integer> getWr(){
        return Wr;
    }

    public void CombineState(MobileProfile profile, Graph g){

    }

    public void setRoot_vertex(Integer[] a){
        root_vertex=a;
    }

    public Integer[] getRoot_vertex() {
        return root_vertex;
    }
}
