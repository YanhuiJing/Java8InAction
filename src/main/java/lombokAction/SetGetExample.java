package lombokAction;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author : gavin
 * @Date : 2019-11-1915:18
 * @DESC : lombok注解@Getter@Setter使用案例
 *
 * @Getter和@Setter注解可以直接在类上进行定义,也可以根据不同属性特性设置
 *  如果没有特殊需要,属性的设置直接使用@Data注解就可以
 */
@Getter
@Setter
public class SetGetExample {

    private String name;
    private int age;
    private double score;

    public static void main(String[] args) {
        SetGetExample data = new SetGetExample();

    }
}
