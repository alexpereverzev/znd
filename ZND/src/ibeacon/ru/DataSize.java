package ibeacon.ru;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Created by Alexander on 25.09.2014.
 */
public class DataSize {

    private int Cm;
    private int Rm;
    private int Lm;

    private ArrayList<Integer> acm = new ArrayList<Integer>();
    private ArrayList<Integer> arm = new ArrayList<Integer>();
    private ArrayList<Integer> alm = new ArrayList<Integer>();

    private ArrayList<Double> acs = new ArrayList<Double>();
    private ArrayList<Double> aadr = new ArrayList<Double>();

    private ArrayList<Double> nub = new ArrayList<Double>();

    private ArrayList<Double> nubsame = new ArrayList<Double>();


    public ArrayList<Integer> getAcm() {
        return acm;
    }

    public ArrayList<Integer> getArm() {
        return arm;
    }

    public ArrayList<Integer> getAlm() {
        return alm;
    }

    public ArrayList<Double> getAadr() {
        return aadr;
    }

    public ArrayList<Double> getAcs() {
        return acs;
    }

    public void calculateCsMaximum(int cm, int rm, int lm) {

        double a = (1 + cm - rm - cm * Math.pow(rm, lm - 1)) / (1 - rm);
        acm.add(cm);
        arm.add(rm);
        alm.add(lm);

        acs.add(a);
    }

    public void calculateMaximumAddress(int cm, int rm, double cs) {

        double res = (cm + rm - 1) * cs;
        aadr.add(res);

    }

    public double calculateMaximumAddressReturn(int cm, int rm, double cs) {

        double res = (cm + rm - 1) * cs;
     //   aadr.add(res);
        return  res;
    }

    public double calculateCsMaximumReturn(int cm, int rm, int lm) {

        double a = (1 + cm - rm - cm * Math.pow(rm, lm - 1)) / (1 - rm);
        acm.add(cm);
        arm.add(rm);
        alm.add(lm);

        acs.add(a);
        return a;
    }

    public void initilizatedProcess(){
        int cm=4;
        int rm=3;
        int lm=4;
       for(int j=0; j<2; j++) {
           cm = cm + j+1;
           rm = rm;
           lm=lm;

           //for (int i = 0; i < 10; i++) {
               calculateCsMaximum(cm, rm, lm);
               calculateMaximumAddress(cm, rm, acs.get(j));
              // DeviceNumber(cm, rm, lm);
                DeviceNumberSame(lm,rm);
         //  }
           System.out.print(aadr.toArray().toString());
           try {
               String s = "Input Data cm=" + cm + " " + " rm=" + rm + " lm =" + lm;
               save(s, aadr, "Add", acs, "Cs", nubsame, "Num");

           } catch (FileNotFoundException e) {
               e.printStackTrace();
           }
       }
    }

    public void DeviceNumber(int cm, int rm, int lm){

        double sum=0;
        for (int i=0; i<lm; i++){
            sum=sum+cm+Math.pow(rm,i);
        }
        nub.add(sum);

    }


    public void DeviceNumberSame(int lm, int rm){

        double sum=0;
        for (int i=0; i<lm; i++){
            sum=sum+Math.pow(rm,i);
        }
        nubsame.add(sum);

    }

    public void save(String input,ArrayList aadr, String name,ArrayList cs, String cs_name,ArrayList num, String num_name) throws FileNotFoundException {

        try {
            File fileDir = new File("c:\\test.txt");

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

            out.append(num_name).append("\r\n");
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
