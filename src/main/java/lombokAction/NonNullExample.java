package lombokAction;

import lombok.NonNull;

/**
 * @Author : gavin
 * @Date : 2019-11-1915:28
 * @DESC : lombok注解@NonNull使用案例
 *
 * @NonNull注解主要用在属性和构造器上,lombok会生成一个非空的声明,用于校验参数,能帮助避免空指针
 *        属性非空可以变相通过Optional实现
 */
public class NonNullExample {

    private String name;
    private int age;
    private double score;

    public NonNullExample() {
    }

    public NonNullExample(@NonNull String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public static void main(String[] args) {

        String name = null;

        try {
            NonNullExample data = new NonNullExample(name, 30, 90.0);
        }catch (NullPointerException e){
            System.out.println(new RuntimeException(e));
        }

    }
}
