package ibeacon.ru;

/**
 * Created by Александр on 16.09.2014.
 */
public class MobileProfile {

    private int weigh;
    private int [][][] profile;

    public int[][][] getProfile() {
        return profile;
    }

    private boolean [] router;


    public void setProfile(int[][][] profile) {
        this.profile = profile;
    }


    public void initialization(int size){
        router=new boolean[size];
    }

    public void setRouter(int number){
        router[number]=true;
    }


}
