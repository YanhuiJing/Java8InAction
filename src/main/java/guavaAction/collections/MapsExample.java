package guavaAction.collections;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;

/***************************************
 * @author:Alex Wang
 * @Date:2018/1/14
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class MapsExample
{
    public static void main(String[] args) {

        Map<String, String> map = Maps.asMap(Sets.newHashSet("1", "2", "3"), k -> k + "_");

        System.out.println(Maps.filterKeys(map,k -> Lists.newArrayList("1","2").contains(k)));

        Maps.transformValues(map,v -> v + "value");
        System.out.println(map);

    }
}
