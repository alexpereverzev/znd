package ibeacon.ru;

import java.util.*;

/**
 * Created by Alexander on 29.09.2014.
 */
public class ZTC {

    private int lm;
    private int rm;
    private int cm;

    private HashSet<Edges> edgesesTree;

    public HashSet<Edges> getEdgesesTree() {
        return edgesesTree;
    }

    public void setEdgesesTree(HashSet<Edges> edgesesTree) {
        this.edgesesTree = edgesesTree;
    }

    private HashSet<Integer> Tvertex=new HashSet<Integer>();

    private HashSet<Edges> Tedges=new HashSet<Edges>();

    public void addTVertex(int v){
        Tvertex.add(v);
    }

    public HashSet<Integer> getTvertex(){
        return Tvertex;
    }

    public void addEdges(Edges e){
        Tedges.add(e);
    }


    public HashSet<Edges> getTedges() {
        return Tedges;
    }

    private List<String[]> path=new ArrayList<String[]>();

    private List<String[]> paths=new ArrayList<String[]>();

    private ArrayList<Integer> Lvertex=new ArrayList<Integer>();

    private ArrayList<Integer> Rvertex=new ArrayList<Integer>();

    public void setRvertex(int r, int number){
        Rvertex.set(number,r);
    }

    public ArrayList<Integer> getRvertex(){
        return Rvertex;
    }

    public void setLvertex(int l,int number){

        Lvertex.set(number,l);
    }

    public ArrayList<Integer> getLvertex(){
        return Lvertex;
    }

    private static int [][] neigboor;

    public void setNeigboor(int [][] a){
        neigboor=a;
    }

    public int getNeigboor(int v){
        for(int i=0; i<neigboor.length; i++){
            if(neigboor[v][i]==1){


                neigboor[v][i]=0;


               neigboor[i][v]=0;
                return i;
            }
        }
        return 100;
    }

    int begin=110;

    public List<List<Integer>> Algo(int begin, int current,List<Integer> path, List<List<Integer>> paths){
        int related=-1;
        this.begin=begin;
        if(current!=100){
        path.add(current);
        Iterator<Integer> iterator=path.iterator();
        while (related!=100){
            related=getNeigboor(current);
           // if((related==begin)&&(path.size()==2)) related=getNeigboor(current);

            if(related!=100){
                if((related==begin)&&((path.size()==2)||(path.size()>2))){
                    paths.add(path);
                }
                if(checkInPath(related)){
                    paths=Algo(begin, related, path, paths);
                  }
        }

        }
        }
        return paths;
    }

    public boolean checkNeighboor(int curent, int related){
        return false;
    }

    public boolean checkInPath(int related){
        return true;
    }

    private static GraphR Gr;

    public static void setGr(GraphR gr) {
        Gr = gr;
    }

    public static GraphR getGr() {
        return Gr;
    }

    public void setLm(int lm) {
        this.lm = lm;
    }

    public void setRm(int rm) {
        this.rm = rm;
    }

    public void setCm(int cm) {
        this.cm = cm;
    }

    public boolean valid(int Lm,int Rm, Edges e){
        return true;
    }



}
