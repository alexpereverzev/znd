package ibeacon.ru;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Alexander on 17.09.2014.
 */
public class GraphR {

    private boolean[] connectedVertex = new boolean[9];

    private LinkedList<Integer> weight = new LinkedList<Integer>();

    private HashSet<Edges> edgeses = new HashSet<Edges>();

    public LinkedList<Integer> getWeight() {
        return weight;
    }

    public void setWeight(LinkedList<Integer> weight) {
        this.weight = weight;
    }

    public void addWeight(Integer we) {
        weight.add(we);
    }

    private LinkedList<Integer> vertex = new LinkedList<Integer>();
    private LinkedList<Integer[]> edge = new LinkedList<Integer[]>();
    private LinkedList<Integer[]> coordinate = new LinkedList<Integer[]>();
    private LinkedList<Integer> Wd = new LinkedList<Integer>();

    private MobileProfile profile;

    public MobileProfile getProfile() {
        return profile;
    }

    public void setProfile(MobileProfile profile) {
        this.profile = profile;
    }

    public LinkedList<Integer[]> getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(LinkedList<Integer[]> coordinate) {
        this.coordinate = coordinate;
    }

    public LinkedList<Integer> getVertex() {
        return vertex;
    }

    public void setVertex(LinkedList<Integer> vertex) {
        this.vertex = vertex;
    }

    public LinkedList<Integer[]> getEdge() {
        return edge;
    }

    public void setEdge(LinkedList<Integer[]> edge) {
        this.edge = edge;
    }


    public void addVertex(Integer v) {
        vertex.add(v);
    }

    public void addEdges(Integer[] e) {
        edge.add(e);
    }

    public void addConnect(int index) {
        connectedVertex[index] = true;
    }

    public void addCordinate(Integer[] a) {
        coordinate.add(a);
    }

    public void addEdges(Edges e) {
        edgeses.add(e);
    }

    public void setSumWd(int a) {
        Wd.add(a);
    }

    public HashSet<Edges> getEdgeses() {
        return edgeses;
    }


}
