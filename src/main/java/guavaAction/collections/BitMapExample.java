package guavaAction.collections;

import com.google.common.collect.HashBiMap;

/**
 * @Author : gavin
 * @Date : 2019-11-1416:27
 * @DESC : bitMap案例
 */
public class BitMapExample {

    public static void main(String[] args) {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "2");
        biMap.put("1", "3");

        System.out.println(biMap);
    }


}
