package java8Action;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author gavin
 * @createDate 2020/3/6
 * Created by wangwenjun on 2016/10/18.
 * interface Stream
 *      Stream<T> filter(Predicate<? super T> predicate);
 *      <R> Stream<R> map(Function<? super T, ? extends R> mapper);
 *      <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
 *      Stream<T> distinct();
 *      Stream<T> sorted();
 *      Stream<T> sorted(Comparator<? super T> comparator);
 *      Stream<T> peek(Consumer<? super T> action);
 *      Stream<T> limit(long maxSize);
 *      Stream<T> skip(long n);
 *      void forEach(Consumer<? super T> action);
 *      Object[] toArray();
 *      T reduce(T identity, BinaryOperator<T> accumulator);
 *      <R> R collect(Supplier<R> supplier,BiConsumer<R, ? super T> accumulator,BiConsumer<R, R> combiner);
 *      <R, A> R collect(Collector<? super T, A, R> collector);
 *      Optional<T> min(Comparator<? super T> comparator);
 *      Optional<T> max(Comparator<? super T> comparator);
 *
 *      interface Predicate<T>
 *           boolean test(T t);
 *       interface Function<T, R>
 *       R apply(T t);
 *       interface Consumer<T>
 *       void accept(T t);
 *       interface BiFunction<T, U, R>
 *       R apply(T t, U u);
 *       interface BiConsumer<T, U>
 *       void accept(T t, U u);
 *       interface Supplier<T>
 *       T get();
 *
 */
public class ListStreamAction {

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

//        getDishByName(menu).stream().forEach(System.out::println);
//        streamMatchAction();
//        streamReduceAction();

    }

    public static List<Dish> getDishByName(List<Dish> dishs){

        List<Dish> dishList = dishs.stream()
                .filter(dish -> {
                    return dish.getCalories() > 400;
                })
                .sorted(Comparator.comparing(Dish::getCalories))
                .collect(Collectors.toList());

        return dishList;
    }

    public static void streamMatchAction(){

        //0-8
//        IntStream range = IntStream.range(0, 9);
        // 0-9
        IntStream intStream = IntStream.rangeClosed(0, 9);

        boolean allMatch = intStream.allMatch(data -> data > 0);
        System.out.println(allMatch);

        boolean anyMatch = intStream.anyMatch(data -> data > 5);
        System.out.println(anyMatch);

        boolean nonMatch = intStream.noneMatch(data -> data > 10);
        System.out.println(nonMatch);


    }

    public static void streamReduceAction(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        Optional<Integer> max = list.stream().reduce(Integer::max);
        max.ifPresent(System.out::println);
        Optional<Integer> maxOther = list.stream().reduce((i, j) -> i > j ? i : j);
        maxOther.ifPresent(System.out::println);


    }
}

enum Type {MEAT, FISH, OTHER}

@Data
@AllArgsConstructor
class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;
}


