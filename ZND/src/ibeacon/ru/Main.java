package ibeacon.ru;


import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private static MobileProfile profile;
    private static Graph g;
    private static int Lmax = 4;
    private static int Rmax = 4;
    private static int Rant = 2;
    private static LinkedList<Integer[]> R;
    private static ArrayList<LinkedList<Integer[]>> list_r = new ArrayList<LinkedList<Integer[]>>();
    private static LinkedList<Integer[]> Q = new LinkedList<Integer[]>();
    private static LinkedList<Integer[]> Rtmp = new LinkedList<Integer[]>();
    private static LinkedList<Integer[]> Rg;
    private static GraphR Gr;
    private static int count_tree = 0;
    private static int size_tree = -1;

    private static LinkedList<Integer[]> weight_edges = new LinkedList<Integer[]>();

    private static AlgorithmConnectivity algorithmConnectivity = new AlgorithmConnectivity();
    private static ZCD zcd;
    private static ArrayList<Integer> wr = new ArrayList<Integer>();
    private static LinkedList<Integer[]> verLmtest = new LinkedList<Integer[]>();
    private static LinkedList<Edges> edgesTest = new LinkedList<Edges>();

    private static HashSet<Edges> edgesesTestHash = new HashSet<Edges>();
    private static LinkedList<Edges> disable = new LinkedList<Edges>();
    private static boolean[] vertex_be;

    private static ZTC ztc = new ZTC();

    private static int[][] neig;

    //  private static HashSet<Edges> verLmTestHash=new HashSet<Edges>();


    public static void main(String[] args) {
        // write your code here
        initialize();


        Sort(profile.getProfile());
        Integer[][][] mas = new Integer[11][11][1];

        R.add(Q.pollFirst());

        for (Integer[] e : Q) {
            if (DiskCover(e, R, Rant)) {
                R.add(e);
                // Rtmp.add(Q.pollFirst());
            }
        }

        FindMaxAngle(R, 0);

        Rg = R;
        //Rtmp-R add Q
        list_r.add(R);
        int j = 0;
        boolean flag = false;
        while (!Q.isEmpty()) {


            list_r.add(FindMaxPolygonCover(Rg));

        }

        MakePolygon(list_r);

        boolean work = true;

        do {
            R.clear();
            Gr.getEdge().clear();
            //  algorithmConnectivity.setMarketVertexFirst();
            for (int i = 0; i < Gr.getVertex().size(); i++) {

                AlgorithmConnectivity algorithmConnectivity = new AlgorithmConnectivity();
                algorithmConnectivity.initializated(Gr.getVertex().size());
                algorithmConnectivity.setMarketVertexFirst();
                algorithmConnectivity.setMarketVertexSecond(i);
                while (algorithmConnectivity.findSecond() != 100) {
                    //int a= iterator.next();
                    int index = algorithmConnectivity.findSecond();
                    algorithmConnectivity.setMarketVertexThird(index);
                    algorithmConnectivity = MakeConnection(index, algorithmConnectivity);
                }
                R.add(algorithmConnectivity.getMarket());
            }
            if (!checkConnectionGraf(R)) {
                //MakeConnection(Gr);
                ArrayList<Integer[]> result = new ArrayList<Integer[]>();
                ArrayList<Integer> ver = new ArrayList<Integer>();
                ArrayList<ArrayList<Integer>> v = new ArrayList<ArrayList<Integer>>();
                for (Integer[] p : R) {
                    ArrayList<Integer> res = new ArrayList<Integer>();
                    ver.clear();
                    for (int i = 0; i < p.length; i++) {
                        if (p[i] != 1) {
                            ver.add(Gr.getVertex().get(i));
                        }
                    }
                    if (ver.size() != 1) {
                        // result.add(AddNewRoute(ver));
                        //  v.add(ver);
                        result.addAll(AddNewRoute(ver));
                    }
                }
                int minumum = 1000;
                Integer[] place = new Integer[3];
                for (int i = 0; i < result.size(); i++) {
                    Integer[] ma = result.get(i);
                    if (ma[2] == null) {
                        System.out.print("");
                    }
                    if ((minumum > ma[2]) && (ma[2]) != 0) {
                        minumum = ma[2];
                        place = ma;
                    }
                }
                if (minumum == 1000) {
                    for (Integer[] p : R) {
                        ver.clear();
                        for (int i = 0; i < p.length - 1; i++) {
                            if (p[i] == 1) {
                                ver.add(Gr.getVertex().get(i));
                            }
                        }
                        result.addAll(AddNewRoute(ver));
                        //  result.add(AddNewRoute(ver));
                        for (int i = 0; i < result.size(); i++) {
                            Integer[] ma = result.get(i);
                            if (ma[2] == null) {
                                System.out.print("");
                            }
                            if ((minumum > ma[2]) && (ma[2]) != 0) {
                                minumum = ma[2];
                                place = ma;
                            }
                        }
                        AddNewVertex(place);
                    }
                } else {
                    AddNewVertex(place);
                }
            } else {
                work = false;
            }

        } while (work);

        MobileProfile prof = new MobileProfile();
        prof.initialization(Gr.getVertex().size());
        int sum = 0;
        int[][][] massive = profile.getProfile();
        for (int i = 0; i < Gr.getVertex().size(); i++) {
            // sum=sum+massive;
        }


        zcd = new ZCD();

        zcd.setGraphR(Gr);

        Iterator<Edges> it = Gr.getEdgeses().iterator();
        boolean fla = false;
        while (it.hasNext()) {
            Edges d = it.next();
            LinkedList<Integer[]> graph_edges = g.getSort_index();

            for (int i = 0; i < graph_edges.size(); i++) {
                Integer[] mass = graph_edges.get(i);
                if (checkLine(g.getCoordinate().get(mass[0]), g.getCoordinate().get(mass[1]), Gr.getCoordinate().get(d.getX()), Gr.getCoordinate().get(d.getY()))) {
                    if (!fla) {
                        Integer[] wr = new Integer[3];
                        wr[0] = d.getX();
                        wr[1] = d.getY();
                        wr[2] = mass[2];
                        fla = true;
                        zcd.addWrEdges(wr);
                    }
                }
            }
            if (!fla) {

                Integer[] wr = new Integer[3];
                wr[0] = d.getX();
                wr[1] = d.getY();
                wr[2] = 2;

                zcd.addWrEdges(wr);

            }
            fla = false;
        }

        Edges e = null;
        for (int i = 0; i < Gr.getVertex().size(); i++) {
            int sumwr = 0;
            HashSet<Edges> item = Gr.getEdgeses();
            Iterator<Edges> iterator = item.iterator();
            for (int k = 0; k < zcd.getWrEdges().size(); k++) {

                e = iterator.next();
                if (e.checkEdge(i)) {
                    Integer[] m = zcd.getWrEdges().get(k);
                    sumwr = sumwr + m[2];

                }

            }
            wr.add(sumwr);

        }

        int max = 0;
        int count = 0;
        for (int i = 0; i < wr.size(); i++) {
            if (max < wr.get(i)) {
                max = wr.get(i);
                count = i;
            }
        }
        Integer[] a = new Integer[2];
        a[0] = count;
        a[1] = max;
        zcd.setRoot_vertex(a);


        int number_vertex = 5;
        //ZTC ztc = new ZTC();
        neig = new int[Gr.getVertex().size()][Gr.getVertex().size()];
        for (int i = 0; i < Gr.getVertex().size(); i++) {
            HashSet<Edges> item = Gr.getEdgeses();
            Iterator<Edges> iterator = item.iterator();
            while (iterator.hasNext()) {
                e = iterator.next();
                if (e.checkEdge(i)) {

                    neig[i][e.getY()] = 1;
                }
            }
        }
        ztc.setNeigboor(neig);
        ztc.addTVertex(a[0]);
        Integer[] zero = new Integer[3];
        zero[0] = a[0];
        zero[1] = 0;
        zero[2] = 0;
        verLmtest.add(zero);
        vertex_be = new boolean[Gr.getVertex().size()];
        int root_number = 5;
        while (ztc.getTvertex().size() != Gr.getVertex().size()) {

            LinkedList<Edges> q = new LinkedList<Edges>();


            count_tree++;


            LinkedList<Integer> vertext_t = new LinkedList<Integer>(ztc.getTvertex());
            while (vertext_t.size() != 0) {
                // for(int i=0; i<count_tree;i++){

                number_vertex = vertext_t.pollFirst();
                weight_edges.clear();
                if (!vertex_be[number_vertex]) {
                    vertex_be[number_vertex] = true;
                } else {
                    continue;
                }

                // if(i<vertext_t.size())


                HashSet<Edges> item = Gr.getEdgeses();
                Iterator<Edges> iterator = item.iterator();
                //  while (iterator.hasNext()) {
                for (int k = 0; k < item.size(); k++) {
                    e = iterator.next();

                    if (e.checkEdge(number_vertex)) {

                        weight_edges.add(zcd.getWrEdges().get(k));
                        q.add(e);
                    }

                    if (q.size() > 1)
                        q = sort(weight_edges, q);


                    while (!q.isEmpty()) {
                        e = q.pollFirst();
                        Integer[] lm = new Integer[3];


                        lm[0] = e.getY();
                        lm[1] = 1;
                        lm[2] = 0;
                        boolean add_flag = true;
                        for (int i = 0; i < verLmtest.size(); i++) {
                            Integer[] mess = verLmtest.get(i);
                            if (e.getY() == mess[0]) {
                                add_flag = false;
                            }
                        }
                        if (add_flag) {
                            for (int i = 0; i < verLmtest.size(); i++) {
                                Integer[] mess = verLmtest.get(i);
                                if (e.getX() == mess[0]) {
                                    mess[2] = mess[2] + 1;
                               /*   if (e.getX() == root_number) {
                                      mess[1] = 1;
                                  } else {
                                      mess[1] = mess[1] + 1;
                                      lm[1]=mess[1];

                                  }*/
                                    verLmtest.set(i, mess);
                                    break;
                                }

                            }
                            for (int i = 0; i < verLmtest.size(); i++) {
                                Integer[] mess = verLmtest.get(i);
                                if (e.getX() == mess[0]) {
                                    lm[1] = mess[1] + 1;
                                }

                            }

                            verLmtest.add(lm);
                        } else {
                            continue;
                        }
                        // }
                        if (ztc.getTvertex().size() == 1) {
                            edgesesTestHash.add(e);
                            int x = e.getX();
                            int y = e.getY();
                            Edges e1 = new Edges(y, x);
                            edgesesTestHash.add(e1);

                            ztc.addEdges(e);
                            ztc.addEdges(e1);

                            ztc.addTVertex(lm[0]);
                            //  q.removeFirst();


                        } else {
                            //  edgesTest.add(e);
                            int x = e.getX();
                            int y = e.getY();
                            Edges e1 = new Edges(y, x);
                            //    disable.add(e);
                            //    disable.add(e1);

                            edgesesTestHash.add(e1);
                            edgesesTestHash.add(e);

                            int[][] ad = getNeighboor();


                            if (checkLegal(e, ad)) {
                                ztc.addEdges(e);
                                ztc.addEdges(e1);

                                ztc.addTVertex(lm[0]);

                                //   if(q.size()!=0)
                                //   q.removeFirst();
                            } else {
                                //  q.removeFirst();

                                Iterator<Edges> edgesIterator = edgesesTestHash.iterator();
                                while (edgesIterator.hasNext()) {
                                    Edges eo = edgesIterator.next();
                                    if ((eo.getY() == e.getY()) && (eo.getX() == e.getX())) {
                                        edgesIterator.remove();
                                    }
                                    if ((eo.getY() == e1.getY()) && (eo.getX() == e1.getX())) {
                                        edgesIterator.remove();
                                    }
                                }

                                verLmtest.removeLast();
                            }


                        }


                    }


                }
            }
        }

        ztc.setEdgesesTree(edgesesTestHash);
        ztc.setGr(Gr);
        ConvertDataToJs convertDataToJs=new ConvertDataToJs();
        try {
            convertDataToJs.save(ztc);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        System.out.print("");

    }

    public static boolean checkLegal(Edges e, int[][] a) {
        Integer[] ma = null;
        int vvv = 0;
        ztc.setNeigboor(a);
        // for(int i=0; i<verLmtest.size(); i++){

        for (int i = 0; i < 2; i++) {
            if (i == 0) vvv = e.getX();
            else vvv = e.getY();

            // ma=verLmtest.get(i);
            List<Integer> path = new ArrayList<Integer>();
            List<List<Integer>> paths = new ArrayList<List<Integer>>();
            int cure = ztc.getNeigboor(vvv);
            List<List<Integer>> re = ztc.Algo(vvv, cure, path, paths);
            if (re.size() != 0) {
                return false;
            }
        }

        return true;
    }

    public static int[][] getNeighboor() {
        Integer[] vertex;
        int[][] neig = new int[Gr.getVertex().size()][Gr.getVertex().size()];
        for (int i = 0; i < verLmtest.size(); i++) {
            vertex = verLmtest.get(i);
            Iterator<Edges> iterator = edgesesTestHash.iterator();
            while (iterator.hasNext()) {
                Edges e = iterator.next();
                if (e.checkEdge(vertex[0])) {

                    neig[vertex[0]][e.getY()] = 1;
                }
            }
        }
        return neig;

    }

    public static boolean checkConnectionGraf(LinkedList<Integer[]> item) {
        LinkedList<Integer[]> current;

        Integer[] aray;
        Integer[] reslt = new Integer[Gr.getVertex().size()];

        aray = item.get(0);

        for (int j = 0; j < aray.length; j++) {

            if (aray[j] == 1) {
                return false;
            }

        }

        return true;
    }

    public static LinkedList<Edges> sort(LinkedList<Integer[]> massive, LinkedList<Edges> q_input) {
        LinkedList<Edges> q_result = new LinkedList<Edges>();
        while (!q_input.isEmpty()) {
            int maximum = 0;
            int index = 0;

            for (int i = 0; i < massive.size(); i++) {
                Integer[] km = massive.get(i);
                if (maximum < km[2]) {
                    maximum = km[2];
                    index = i;
                }
            }

            q_result.add(q_input.get(index));
            q_input.remove(index);
        }
        return q_result;
    }

    public static ArrayList<Integer[]> AddNewRoute(ArrayList<Integer> p) {
        Integer[] massive = new Integer[3];
        ArrayList<Integer[]> mass = new ArrayList<Integer[]>();
        for (int i = 0; i < p.size(); i++)
            for (int j = 0; j < Gr.getVertex().size(); j++)

            {
                Integer[] massive1 = new Integer[3];

                boolean fl = false;
                for (int z = 0; z < p.size(); z++) {
                    if (p.get(z) == Gr.getVertex().get(j)) {
                        fl = true;
                    }
                }
                if (!fl) {
                    massive[0] = p.get(i);
                    massive[1] = Gr.getVertex().get(j);
                    massive1[0] = p.get(i);
                    massive1[1] = Gr.getVertex().get(j);


                    double d1 = calculateLength(Gr.getCoordinate().get(p.get(i)), Gr.getCoordinate().get(j));
                    massive[2] = (int) d1;
                    massive1[2] = (int) d1;

                    mass.add(massive1);
                }
            }
        return mass;
    }

    public static void AddNewVertex(Integer[] place) {
        Integer[] a = Gr.getCoordinate().get(place[0]);
        Integer[] b = Gr.getCoordinate().get(place[1]);
//      int x=(a[0]+b[0])/2;
        //    int y=(a[1]+b[1])/2;

        Integer[] coordinate = new Integer[2];

        for (int i = 2; i < 5; i++) {
            int x = (a[0] + b[0]) / i;
            int y = (a[1] + b[1]) / i;
            coordinate[0] = x;
            coordinate[1] = y;
            double lengt = calculateLength(Gr.getCoordinate().get(place[0]), coordinate);
            if (Rant > lengt) {
                Gr.addVertex(Gr.getVertex().size());
                Integer[] edge = new Integer[2];
                edge[0] = place[0];
                edge[1] = Gr.getVertex().getLast();
                Edges e = new Edges(edge[0], edge[1]);
                Gr.addEdges(e);
                Gr.addEdges(edge);
                Gr.addCordinate(coordinate);


                Gr.addWeight(2);
                lengt = calculateLength(Gr.getCoordinate().get(place[1]), coordinate);
                if (Rant > lengt) {
                    Integer[] another = new Integer[2];
                    another[0] = place[1];
                    another[1] = Gr.getVertex().getLast();
                    Gr.addEdges(another);
                    Edges ed = new Edges(another[0], another[1]);
                    Gr.addEdges(ed);
                    Gr.addWeight(2);
                }
                break;
            }
        }

    }

    public static AlgorithmConnectivity MakeConnection(int vertex, AlgorithmConnectivity algorithmConnectivity) {
        LinkedList<Integer> v = Gr.getVertex();
        boolean[] vertex_flag = new boolean[Gr.getVertex().size()];


        for (int i = 0; i < v.size(); i++) {
            if (vertex != i) {
                double lengt = calculateLength(Gr.getCoordinate().get(vertex), Gr.getCoordinate().get(i));
                if (Rant > lengt) {
                    algorithmConnectivity.setMarketVertexSecond(i);
                    // algorithmConnectivity.addNumberEdge();
                    vertex_flag[vertex] = true;
                    vertex_flag[i] = true;
                    // Gr.addConnect(vertex);
                    //   Gr.addConnect(i);
                    Integer[] a = new Integer[2];
                    a[0] = vertex;
                    a[1] = i;
                    Gr.addEdges(a);

                    Edges e = new Edges(a[0], a[1]);
                    Gr.addEdges(e);
                }
            }
        }
        return algorithmConnectivity;
    }

    public static boolean DiskCover(Integer[] e, LinkedList<Integer[]> R, int Rant) {
        Integer[] r = R.getFirst();
        Integer[] first = g.getCoordinate().get(e[0]);
        Integer[] second = g.getCoordinate().get(e[1]);
        Integer[] r_first = g.getCoordinate().get(r[0]);
        Integer[] r_second = g.getCoordinate().get(r[1]);
        double x_c = (r_first[0] + r_second[0]) / 2;
        double y_c = (r_first[1] + r_second[1]) / 2;
        if (((first[0] < x_c + Rant) && (x_c - Rant < first[0])) && ((first[1] < y_c + Rant) && (y_c - Rant < first[1]))) {

            if (((second[0] < x_c + Rant) && (x_c - Rant < second[0])) && ((second[1] < y_c + Rant) && (y_c - Rant < second[1]))) {
                return true;
            } else return false;
        } else return false;

    }

    public static boolean DiskCoverCheck(Integer[] e, Integer[] r, int Rant) {

        Integer[] first = g.getCoordinate().get(e[0]);
        Integer[] second = g.getCoordinate().get(e[1]);
        Integer[] r_first = g.getCoordinate().get(r[0]);
        Integer[] r_second = g.getCoordinate().get(r[1]);
        double x_c = (r_first[0] + r_second[0]) / 2;
        double y_c = (r_first[1] + r_second[1]) / 2;
        if (((first[0] < x_c + Rant) && (x_c - Rant < first[0])) && ((first[1] < y_c + Rant) && (y_c - Rant < first[1]))) {

            if (((second[0] < x_c + Rant) && (x_c - Rant < second[0])) && ((second[1] < y_c + Rant) && (y_c - Rant < second[1]))) {
                return true;
            } else return false;
        } else return false;

    }

    public static LinkedList<Integer[]> FindMaxPolygonCover(LinkedList<Integer[]> Rg) {
        int[] index = new int[101];
        int z = 0;
        ArrayList<Cover> result = new ArrayList<Cover>();
        int[] sum_weight = new int[Q.size()];
        for (int k = 0; k < Q.size(); k++) {
            Cover cover = new Cover();
            cover.addItem(Q.get(k));
            cover.addIndex(k);
            z = 0;
            for (int i = 0; i < Q.size(); i++) {
                if (k != i) {

                    if (DiskCoverCheck(Q.get(i), Q.get(k), Rant)) {
                        cover.addItem(Q.get(i));
                        // R.add(Q.get(i));
                        index[z] = i;
                        z++;
                        cover.addIndex(i);
                    }
                }
            }
            result.add(cover);
        }
        Cover max = findMaxWeight(result);
        int j = 0;
        Iterator<Integer> it = max.getIndex_i().iterator();
        while (it.hasNext()) {
            Q.remove(it.next() - j);
            j++;
        }


        return max.getItem();
    }

    public static Cover findMaxWeight(ArrayList<Cover> list) {
        int max = 0;
        Cover c;
        Cover ma = null;
        Iterator<Cover> iterator = list.iterator();
        while (iterator.hasNext()) {
            c = iterator.next();
            if (max < c.getWeighl()) {
                max = c.getWeighl();
                ma = c;
            } else {
                ma = c;
            }
        }
        return ma;
    }

    public static LinkedList<Integer[]> FindMaxPolygonCover(Integer[] Rg) {
        int[] index = new int[Q.size()];
        LinkedList<Integer[]> R = new LinkedList<Integer[]>();
        R.add(Rg);
        int z = 0;
        for (int i = 1; i < Q.size(); i++) {
            if (DiskCoverCheck(Q.get(i), R.get(0), Rant)) {

                R.add(Q.get(i));
                index[z] = i;
                z++;
            }
        }

        int j = 0;
        for (int i = 0; i < index.length; i++) {
            if (index[i] != 0) {
                Q.remove(i - j);
                j++;
            }
        }

        Q.remove(0);


        return R;
    }

    public static int MakePolygon(ArrayList<LinkedList<Integer[]>> res) {
        int i = 0;
        LinkedList<Integer> weight = new LinkedList<Integer>();
        LinkedList<Integer[]> coordinate = new LinkedList<Integer[]>();
        LinkedList<Integer[]> current = new LinkedList<Integer[]>();
        Iterator<LinkedList<Integer[]>> iterator = res.iterator();
        while (iterator.hasNext()) {
            current = iterator.next();
            Gr.addVertex(i);
            Integer[] iw = current.get(0);
            Integer[] first = g.getCoordinate().get(iw[0]);
            Integer[] second = g.getCoordinate().get(iw[1]);
            Integer[] result = new Integer[2];
            result[0] = (first[0] + second[0]) / 2;
            result[1] = (first[1] + second[1]) / 2;

            coordinate.add(result);
            i++;
            if (current.size() == 1) {
                weight.add(iw[2]);
            } else {
                int w = 0;
                for (Integer[] a : current) {
                    w = w + a[2];
                }
                weight.add(w);
            }
        }
        Gr.setCoordinate(coordinate);
        Gr.setWeight(weight);
        return 0;
    }

    public static void FindMaxAngle(LinkedList<Integer[]> R1, int ant) {
        int sum = 0;
        for (Integer[] item : R1) {
            sum = sum + item[2];
        }
        //Rant=sum;


    }

    public static void initialize() {
        Gr = new GraphR();
        g = new Graph();

        profile = new MobileProfile();
        R = new LinkedList<Integer[]>();
        g.setVertex(10);

        int[][][] s = new int[11][11][1];
        int[][][] m = new int[11][11][1];
        int j = 1;
        for (int i = 0; i < g.getVertex(); i++) {
            s[i][i + 1][0] = 1;

            m[i][i + 1][0] = j + 2;
            j++;


        }

        ArrayList<Integer[]> cor = new ArrayList<Integer[]>();
        Integer[] a = new Integer[2];
        a[0] = 1;
        a[1] = 1;
        cor.add(a);
        Integer[] a1 = new Integer[2];
        a1[0] = 2;
        a1[1] = 1;
        cor.add(a1);
        Integer[] a2 = new Integer[2];
        a2[0] = 2;
        a2[1] = 2;
        cor.add(a2);
        Integer[] a3 = new Integer[2];
        a3[0] = 4;
        a3[1] = 1;
        cor.add(a3);
        Integer[] a4 = new Integer[2];
        a4[0] = 1;
        a4[1] = 4;
        cor.add(a4);
        Integer[] a5 = new Integer[2];
        a5[0] = 4;
        a5[1] = 4;
        cor.add(a5);
        Integer[] a6 = new Integer[2];
        a6[0] = 6;
        a6[1] = 2;
        cor.add(a6);
        Integer[] a7 = new Integer[2];
        a7[0] = 6;
        a7[1] = 1;
        cor.add(a7);
        Integer[] a8 = new Integer[2];
        a8[0] = 6;
        a8[1] = 5;
        cor.add(a8);
        Integer[] a9 = new Integer[2];
        a9[0] = 2;
        a9[1] = 6;
        cor.add(a9);
        Integer[] a10 = new Integer[2];
        a10[0] = 4;
        a10[1] = 6;
        cor.add(a10);
        g.setCoordinate(cor);
        g.setEdge(s);

        profile.setProfile(m);
    }

    public static void Sort(int[][][] s) {
        int max = 0;
        int index_i = 0;
        int index_j = 0;
        int z = 0;
        int[] result = new int[11];
        Integer[][][] res = new Integer[11][11][1];

        LinkedList<Integer[]> result_index = new LinkedList<Integer[]>();

        while (z < 11) {
            for (int i = 0; i < s.length; i++)
                for (int j = 0; j < s[i].length; j++) {
                    if (s[i][j][0] != 0) {
                        if (max < s[i][j][0]) {
                            max = s[i][j][0];
                            index_i = i;
                            index_j = j;
                        }

                    }
                }
            result[z] = max;

            s[index_i][index_j][0] = 0;
            Integer[] ma = new Integer[3];
            ma[0] = index_i;
            ma[1] = index_j;
            ma[2] = max;
            result_index.add(ma);
            res[index_i][index_j][0] = result[z];
            Q.add(ma);
            z++;
            max = 0;

        }
        //  for(int a=0; a<result.length; a++) Q.add(result[a]);
        g.setSort_index(result_index);

    }


    public static double calculateLength(Integer[] a, Integer[] b) {

        double length = Math.sqrt(Math.pow((b[0] - a[0]), 2) + Math.pow((b[1] - a[1]), 2));

        return length;
    }

    public static boolean checkLine(Integer[] a, Integer[] b, Integer[] a1, Integer[] b1) {
        double d1 = Math.sqrt(Math.pow((a[0] - b[0]), 2) + Math.pow((a[1] - b[1]), 2));
        double d2 = Math.sqrt(Math.pow((a[0] - a1[0]), 2) + Math.pow((a[1] - a1[1]), 2));
        double d3 = Math.sqrt(Math.pow((b[0] - a1[0]), 2) + Math.pow((b[1] - a1[1]), 2));

        if (((d1 + d2) == d3) || ((d1 + d3) == d2) || ((d2 + d3) == d1)) {
            d1 = Math.sqrt(Math.pow((a[0] - b[0]), 2) + Math.pow((a[1] - b[1]), 2));
            d2 = Math.sqrt(Math.pow((a[0] - b1[0]), 2) + Math.pow((a[1] - b1[1]), 2));
            d3 = Math.sqrt(Math.pow((b[0] - b1[0]), 2) + Math.pow((b[1] - b1[1]), 2));
            if (((d1 + d2) == d3) || ((d1 + d3) == d2) || ((d2 + d3) == d1)) {
                return true;
            } else return false;
        } else return false;
        //IF d1 + d2 = d3 OR d1 + d3 = d2 OR d2 + d3 = d1 THEN PRINT "lejat na pryamoy" ELSE PRINT "ne lejat na pryamoy"
    }

}
