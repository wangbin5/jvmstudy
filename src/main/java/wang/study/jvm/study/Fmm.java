package wang.study.jvm.study;

public class Fmm {
    private double[][] A = new double[][]{
            {1/3.0,1/3.0,1/3.0},
            {1/3.0,1/3.0,1/3.0},
            {1/3.0,1/3.0,1/3.0}
    };
    private double[][] B = new double[][]{
            {1/6.0,1/6.0,1/6.0,1/6.0,1/6.0,1/6.0,0,0},
            {1/4.0,1/4.0,1/4.0,1/4.0,0,0,0,0},
            {1/8.0,1/8.0,1/8.0,1/8.0,1/8.0,1/8.0,1/8.0,1/8.0}
    };


    private double[][] a;

    public double calculate(String result){
        System.out.println(A[0][0]);
        int length = result.length();
        int statusLength = A[0].length;
        a = new double[length][statusLength];

        //正向算法
        for(int i=0;i<statusLength;i++){
            int o= parse(result.charAt(0));
            a[0][i]= 1/3.0 *B[i][o] ;
        }
        for(int t=1; t< length;t++){
            int o= parse(result.charAt(t));
            for(int j=0;j<statusLength;j++){
                double sum = 0.0;
                for(int i=0;i<statusLength;i++){
                    sum+= a[t-1][i]*A[i][j];
                }
                sum = sum * B[j][o];
                a[t][j] = sum;
            }
        }
        double percent = 0.0;
        for(int i=0;i<statusLength;i++){
            percent+= a[length-1][i];
        }
        return percent;
    }

    private int parse(char c) {
        return Integer.parseInt(c+"")-1;
    }

    public static void main(String[] args){
        double percent = new Fmm().calculate("1635273524");
        System.out.println("calculate percent "+percent);

    }
}
