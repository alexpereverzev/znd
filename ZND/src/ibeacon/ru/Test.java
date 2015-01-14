package ibeacon.ru;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Alexander on 25.09.2014.
 */
public class Test {

  static   ArrayList<Double> cs=new ArrayList<Double>();

  static   ArrayList<Integer> lm=new ArrayList<Integer>();

   static ArrayList<Integer> rm=new ArrayList<Integer>();

    public static void main(String[] args) {

        DataSize dataSize=new DataSize();
        Test t=new Test();

        for(int i=1; i<8; i++){
            int cm=6;
            rm.add(cm);
            cs.add(dataSize.calculateCsMaximumReturn(cm,cm,i));
            lm.add(i);
        }
        try {
            t.save("Rm",rm,"Cs",cs,"Lm",lm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        double cs=   dataSize.calculateCsMaximumReturn(4,3,4);

        double addr=   dataSize.calculateMaximumAddressReturn(4,3,4);
        double nub=  t.DeviceNumber(4,3,4);

        //  dataSize.initilizatedProcess();

      double re=  t.DeviceNumber(6,5,4);
        System.out.print(re);




    }


    public double DeviceNumber(int cm, int rm, int lm){

        double sum=0;
        for (int i=0; i<lm; i++){
            sum=sum+cm+Math.pow(rm,i);
        }
       return sum;

    }



    public void save(String input,ArrayList aadr, String name,ArrayList cs, String cs_name,ArrayList num) throws FileNotFoundException {

        try {
            File fileDir = new File("c:\\lm_cs.txt");

            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileDir,true), "UTF8"));

            out.append(" --> ").append("\r\n");
            out.append(" --> ").append("\r\n");
            out.append(" --> ").append("\r\n");
            out.append(name).append("\r\n");
            for(int i=0; i<aadr.size();i++){
                out.append(aadr.get(i).toString()).append("\r\n");

            }

            out.append(cs_name).append("\r\n");
            for(int i=0; i<cs.size();i++){
                out.append(cs.get(i).toString()).append("\r\n");

            }

            out.append("Last").append("\r\n");
            for(int i=0; i<num.size();i++){
                out.append(num.get(i).toString()).append("\r\n");

            }
            out.append(input).append("\r\n");

            out.flush();
            out.close();

        }
        catch (UnsupportedEncodingException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
