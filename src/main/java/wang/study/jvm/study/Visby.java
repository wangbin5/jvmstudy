package wang.study.jvm.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Visby {
    private int m = 2;
    private int n = 3;
    private int K = 9;
    private List<Double> w = Arrays.asList(0.0,1.0,0.5,1.0,1.0,0.2,1.0,0.5,0.8,0.5);

    private double[][] th = new double[n+1][m+1];

    private int[][] path = new int[n+1][m+1];

    public double wdotF(int lastY,int currentY,List<Double> x,int i){
        List<Double> fvalue = this.F(lastY,currentY,x,i);
        double sum = 0.0;
        for(int index=1;index<w.size();index++){
            sum+= w.get(index)*fvalue.get(index);
        }
        return sum;
    }

    public List<Double> F(int lastY,int currentY,List<Double> x,int i){
        List<Double> values = new ArrayList<>();
        values.add(0.0);
        for(int k=1;k<=K;k++){
            values.add(f(lastY,currentY,x,i,k));
        }
        return values;
    }

    public double f(int lastY,int currentY,List<Double> x,int i,int k){
        if(k == 1){
            if(lastY == 1 && currentY == 2 && (i==2||i==3)){
                return 1;
            }
            return 0;
        }
        else if(k == 2){
            if(lastY == 1 && currentY == 1 && i==2){
                return 1;
            }
            return 0;
        }
        else if(k == 3){
            if(lastY == 2 && currentY == 1 && i==3){
                return 1;
            }
            return 0;
        }
        else if(k == 4){
            if(lastY == 2 && currentY == 1 && i==2){
                return 1;
            }
            return 0;
        }
        else if(k == 5){
            if(lastY == 2 && currentY == 2 && i==3){
                return 1;
            }
            return 0;
        }
        else if(k == 6){
            if(currentY == 1 && i==1){
                return 1;
            }
            return 0;
        }
        else if(k == 7){
            if(currentY == 2 && (i==1||i==2)){
                return 1;
            }
            return 0;
        }
        else if(k == 8){
            if(currentY == 1 && (i==2||i==3)){
                return 1;
            }
            return 0;
        }
        else if(k == 9){
            if(currentY == 2 && i==3){
                return 1;
            }
        }
        return 0.0;
    }

    // j ~ 1...m
    // i ~ 1...n
    //l  ~ 1...m



    public void calculate(List<Double> x){

        for(int j=1;j<=m;j++){
            th[1][j] = wdotF(-1,j,x,1);
        }
        for(int i=2;i<=n;i++){
            for(int l=1;l<=m;l++){
                double max = Double.MIN_VALUE;
                for(int j=1;j<=m;j++){
                    double value = th[i-1][j]+ wdotF(j,l,x,i);
                    if(value> max){
                        max = value;
                        path[i][l] = j;
                    }
                }
                th[i][l] = max;
            }
        }
        double maxValue = Double.MIN_VALUE;
        int pathValue = 0;
        List<Integer> goodPath = new ArrayList<>();
        for(int j=1;j<=m;j++){
            double value = th[n][j];
            if(value> maxValue){
                maxValue = value;
                pathValue = j;
            }
        }
        goodPath.add(pathValue);
        for(int i=n-1;i>0;i--){
            int value = path[i+1][pathValue];
            pathValue = value;
            goodPath.add(0,pathValue);
        }
        System.out.print("path is :");
        for(Integer path : goodPath){
            System.out.print(path+" ");
        }
    }

    public static void main(String[] args){
        new Visby().calculate(new ArrayList<>());

        int k =1;
        System.out.println(Math.pow(1-Math.pow(Math.E,-k*1.0/40),k));

        System.out.println(1-Math.pow(1-1.0/200000,8000000));
    }
}
