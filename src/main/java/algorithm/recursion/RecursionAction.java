package algorithm.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gavin
 * @createDate 2020/3/7
 */
public class RecursionAction {

    private static Map<Integer,Integer> map= new HashMap<>();

    //n阶楼梯,一次可以走1步或2步,一共多少种走法
    public static int findStep(int num){

        if(num ==1) {return 1;}
        if(num ==2) {return 2;}


        //通过map记录已经计算完成的值,避免重复计算
        if(map.containsKey(num)){
            return map.get(num);
        }

        int res = findStep(num-1) + findStep(num-2);

        map.put(num,res);

        return res;

    }
}


