package guavaAction.collections;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Author : gavin
 * @Date : 2019-11-1416:09
 * @DESC : maps案例
 */
public class MapsExample {

    public static void main(String[] args) {

        ArrayList<String> valueLIst = Lists.newArrayList("1", "2", "3");
        ImmutableMap<String, String> map = Maps.uniqueIndex(valueLIst, k -> k + "_key");
        System.out.println(map);

        Map<String, String> map2 = Maps.asMap(Sets.newHashSet("1", "2", "3"), k -> k + "_value");
        System.out.println(map2);

        //基于map数据类型进行遍历transformer和filter操作

        Map<String, String> map3 = Maps.transformValues(map2, v -> v + "_transforme");
        System.out.println(map3);

        Map<String, String> map4 = Maps.filterKeys(map2, k -> Lists.newArrayList("1", "2").contains(k));
        System.out.println(map4);

    }


}
