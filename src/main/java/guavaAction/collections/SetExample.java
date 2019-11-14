package guavaAction.collections;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @Author : gavin
 * @Date : 2019-11-1414:28
 * @DESC : set案例
 */
public class SetExample {

    public static void main(String[] args) {

//        testCreate();
//        testCartesianProduct();
//        testCombinations();
//        testDiff();
//        testInterSection();
        testUnionSection();
    }

    public static void testCreate(){

        HashSet<Integer> set = Sets.newHashSet(1, 2, 3);
        assertThat(set.size(), equalTo(3));

        ArrayList<Integer> list = Lists.newArrayList(1, 1, 2, 3);
        assertThat(list.size(), equalTo(4));

        HashSet<Integer> set2 = Sets.newHashSet(list);
        assertThat(set2.size(), equalTo(3));

    }

    public static void testCartesianProduct(){

        Set<List<Integer>> set = Sets.cartesianProduct(Sets.newHashSet(1, 2), Sets.newHashSet(3, 4), Sets.newHashSet(5, 6));
        System.out.println(set);

    }

    public static void testCombinations(){

        HashSet<Integer> set = Sets.newHashSet(1, 2, 3);
        Set<Set<Integer>> combinations = Sets.combinations(set,2);

        combinations.forEach(System.out::println);

    }

    public static void testDiff(){

        HashSet<Integer> set01 = Sets.newHashSet(1, 2, 3);
        HashSet<Integer> set02 = Sets.newHashSet(1, 4, 6);

        Sets.SetView<Integer> difference1 = Sets.difference(set01, set02);
        System.out.println(difference1);

        Sets.SetView<Integer> difference2 = Sets.difference(set02, set01);
        System.out.println(difference2);

    }

    public static void testInterSection(){
        // 计算set集合交集

        HashSet<Integer> set01 = Sets.newHashSet(1, 2, 3);
        HashSet<Integer> set02 = Sets.newHashSet(1, 4, 6);

        Sets.intersection(set01,set02).forEach(System.out::println);

    }

    public static void testUnionSection(){
        // 计算set集合并集

        HashSet<Integer> set01 = Sets.newHashSet(1, 2, 3);
        HashSet<Integer> set02 = Sets.newHashSet(1, 4, 6);

        Sets.union(set01,set02).forEach(System.out::println);


    }
}
