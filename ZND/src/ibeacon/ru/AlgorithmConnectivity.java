package ibeacon.ru;

/**
 * Created by Alexander on 19.09.2014.
 */
public class AlgorithmConnectivity {
    private Integer[] market;

    public void setMarketVertexFirst() {
        for(int i=0; i<market.length; i++){
            market[i]=1;
        }

    }

    public void initializated(int size){
        market = new Integer[size];
    }

    public void setMarketVertexSecond(int index) {
        if(market[index]!=3) market[index] = 2;
    }

    public void setMarketVertexThird(int index) {
        market[index] = 3;
    }

    public int findSecond(){
        for(int i=0; i<market.length; i++){
            if(market[i]==2) return i;
        }

      return 100;
    }

    public Integer [] getMarket(){
        return market;
    }

    public void addNumberEdge(){
        market[market.length-1]=market[market.length-1]+1;
    }

}
