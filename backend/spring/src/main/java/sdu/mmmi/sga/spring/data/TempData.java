package sdu.mmmi.sga.spring.data;

public class TempData {
    private double[] temp;
    private int[] time;

    public TempData(double[] temp, int[] time){
        this.temp = temp;
        this.time = time;
    }

    public double[] getTemp(){
        return temp;
    }
    public int[] getTime(){
        return time;
    }
    public void setTemp(double[] temp){
        this.temp = temp;
    }
    public void setTime(int[] time){
        this.time = time;
    }
    public double[] getTempData(){
        return temp;
    }
    public int[] getTimeData(){
        return time;
    }
}
