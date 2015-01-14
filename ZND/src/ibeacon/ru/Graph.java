package ibeacon.ru;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Александр on 16.09.2014.
 */
public class Graph {

    private int vertex;

    private ArrayList<Integer []> coordinate;

    public ArrayList<Integer[]> getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(ArrayList<Integer[]> coordinate) {
        this.coordinate = coordinate;
    }

    private int [][][] edge;

    private LinkedList<Integer []> sort_index;

    public LinkedList<Integer[]> getSort_index() {
        return sort_index;
    }

    public void setSort_index(LinkedList<Integer[]> sort_index) {
        this.sort_index = sort_index;
    }





    public ArrayList<Integer[]> getGraph() {
        return graph;
    }

    public void setGraph(ArrayList<Integer[]> graph) {
        this.graph = graph;
    }

    public int [][][] getEdge() {
        return edge;
    }

    public void setEdge(int [][][] edge) {
        this.edge = edge;
    }

    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    private ArrayList<Integer []> graph;




}
