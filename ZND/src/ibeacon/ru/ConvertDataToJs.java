package ibeacon.ru;

import java.io.*;
import java.util.Iterator;

/**
 * Created by Alexander on 10.10.2014.
 */
public class ConvertDataToJs {

    private GraphR graphR;

    private ZTC ztc;

    public ConvertDataToJs(){

    }

    public GraphR getGraphR() {
        return graphR;
    }

    public void setGraphR(GraphR graphR) {
        this.graphR = graphR;
    }

    public ZTC getZtc() {
        return ztc;
    }

    public void setZtc(ZTC ztc) {
        this.ztc = ztc;
    }

    public void save(ZTC zt) throws FileNotFoundException {

        try {
            File fileDir = new File("c:\\lm_js.js");

            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileDir,true), "UTF8"));
            int number_vertex=zt.getTvertex().size();
            out.append("var n=" + number_vertex).append("\r\n");
            out.append("var mas=[]").append("\r\n");


            for(int i=0; i<zt.getTvertex().size();i++) {
                Iterator<Edges> edgesIterator=zt.getTedges().iterator();
                Integer [] coordinate=new Integer[2];

                out.append("mas["+i+"]={}").append("\r\n");
                out.append("mas["+i+"].vertex="+i).append("\r\n");
                coordinate= zt.getGr().getCoordinate().get(i);
                out.append("mas["+i+"].x="+coordinate[0]).append("\r\n");
                out.append("mas["+i+"].y="+coordinate[1]).append("\r\n");
                String connect="";
                while (edgesIterator.hasNext()){
                    Edges edges=edgesIterator.next();
                    if (edges.getX()==i){
                        connect=connect+edges.getY()+",";
                    }
                }
                String result;
                connect=removeMethod(connect);
                out.append("mas["+i+"].con=["+connect+"];").append("\r\n");
            }



            out.flush();
            out.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public int[][] getNeighboor() {
        Integer vertex;
        int[][] neig = new int[ztc.getTvertex().size()][ztc.getTvertex().size()];
        for (int i = 0; i < ztc.getTvertex().size(); i++) {
            vertex = ztc.getGr().getVertex().get(i);
            Iterator<Edges> iterator = ztc.getEdgesesTree().iterator();
            while (iterator.hasNext()) {
                Edges e = iterator.next();
                if (e.checkEdge(vertex)) {

                    neig[vertex][e.getY()] = 1;
                }
            }
        }
        return neig;

    }

    public String removeMethod(String str) {
        if (str.length() > 0 && str.charAt(str.length()-1)==',') {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }


}
