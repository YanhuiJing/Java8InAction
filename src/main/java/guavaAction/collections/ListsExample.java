package guavaAction.collections;

import com.google.common.collect.Lists;
import com.sun.imageio.spi.OutputStreamImageOutputStreamSpi;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : gavin
 * @Date : 2019-11-1416:18
 * @DESC : Lists案例
 */
public class ListsExample {

    public static void main(String[] args) {

        ArrayList<String> list = Lists.newArrayList("1", "2", "3", "4");
        List<List<String>> result = Lists.partition(list,2);

        System.out.println(result);
    }


}
