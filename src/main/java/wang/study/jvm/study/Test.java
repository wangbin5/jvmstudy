package wang.study.jvm.study;

public class Test {

    public double pb(double pa){
        return 0.5- pa;
    }
    public double pc(double pa){
        return 0.3-pa;
    }
    public double pd(double pa){
        return 0.1+0.5*pa;
    }
    public double pe(double pa){
        return 0.1+0.5*pa;
    }
    public double hp(double pa){
        double pb = pb(pa);
        double pc = pc(pa);
        double pd = pd(pa);
        double pe = pe(pa);
        return -1.0 * plogp(pa)
                - plogp(pb)
                - plogp(pc)
                - plogp(pd)
                - plogp(pe);


    }

    private double plogp(double pa) {
        if(pa == 0){
            return 0;
        }
        return Math.log(pa) * pa;
    }

    public static void main(String[] args){
        Test test = new Test();
        int maxstep = 1000;
        double maxhp = Double.MIN_VALUE;
        double pa = 0;
        double maxpa = pa;
        int step = 0;
        while(step<=maxstep){
            double hp = test.hp(pa);

            if(hp>maxhp){
                System.out.println("pa = "+pa+",hp = "+hp);
                maxhp = hp;
                maxpa = pa;
            }
            pa = pa+0.3/maxstep;
            step++;
        }
        System.out.println("max pa "+maxpa+", hp "+maxhp);
    }
}
