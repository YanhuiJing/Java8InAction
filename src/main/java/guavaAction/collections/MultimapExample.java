package guavaAction.collections;

import com.google.common.collect.LinkedListMultimap;

/**
 * @Author : gavin
 * @Date : 2019-11-1416:04
 * @DESC : multiMap案例
 */
public class MultimapExample {

    public static void main(String[] args) {

        testBasic();

    }

    public static void testBasic(){
        LinkedListMultimap<String, String> multiMap = LinkedListMultimap.create();

        multiMap.put("key","1");
        multiMap.put("key","2");

        System.out.println(multiMap.get("key"));
    }
}
