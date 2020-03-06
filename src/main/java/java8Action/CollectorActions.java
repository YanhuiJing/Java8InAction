package java8Action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

/**
 * @author gavin
 * @createDate 2020/3/6
 */
public class CollectorActions {

    private static List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH));

    public static void main(String[] args) {

        groupByAction();

    }

    // javaStream版的groupBy操作
    public static void groupByFunction(){

        List<Apple> list = Arrays.asList(new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170)
                , new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170));

        Map<String, List<Apple>> collect = list.stream()
                .collect(Collectors.groupingBy(Apple::getColor));

        System.out.println(collect);

    }

    public static void groupByAction(){

        //计数
        Long count = menu.stream().collect(Collectors.counting());
        String joinString = menu.stream().map(Dish::getName).collect(Collectors.joining(",", "[", "]"));

        System.out.println(joinString);

        //求最大值
        Optional<Dish> max = menu.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));
        Optional<Dish> maxOther = menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));

        //计数
        Integer size = menu.stream().collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
        //分组求平均数
        Map<Type, Double> collect = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        //选取指定数据,将数据放入map中
        ConcurrentMap<String, Integer> map = menu.stream().collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories));
        //四个参数分别为,key值,value初始值,value计算逻辑,数据存放的容器
        ConcurrentSkipListMap<Type, Long> skipListMap = menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (a, b) -> a + b, ConcurrentSkipListMap::new));

    }
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class Apple{
    private String color;
    private long weight;
}
