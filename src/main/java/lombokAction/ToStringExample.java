package lombokAction;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @Author : gavin
 * @Date : 2019-11-1916:11
 * @DESC : lombok注解toString使用案例
 *
 * 使用@ToString注解,lombok会生成一个toString方法,默认情况下会出输出类名,所有属性
 *          使用exclude可以指定需要排除的属性
 */
@ToString(exclude = {"age","score"})
@AllArgsConstructor
public class ToStringExample {

    private String name;
    private int age;
    private double score;

    public static void main(String[] args) {

        ToStringExample data = new ToStringExample("gavin",31,90.0);

        System.out.println(data);

    }


}
