package lombokAction;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * @Author : gavin
 * @Date : 2019-11-1916:05
 * @DESC : lombok注解equalAndHashCode案例
 *
 * @EqualsAndHashCode默认情况下会使用所有属性生成equals和hascode,也能通过exclude注解来排除一些属性
 */
@EqualsAndHashCode(exclude = {"age","score"})
@AllArgsConstructor
public class EqualAndHashCodeExample {

    private String name;
    private int age;
    private double score;

    public static void main(String[] args) {

        EqualAndHashCodeExample data01 = new EqualAndHashCodeExample("gavin",31,90.0);
        EqualAndHashCodeExample data02 = new EqualAndHashCodeExample("gavin",32,90.0);

        System.out.println(data01.equals(data02));


    }




}
