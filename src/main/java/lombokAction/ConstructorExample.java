package lombokAction;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @Author : gavin
 * @Date : 2019-11-1916:18
 * @DESC : lombok构造方法注解使用案例
 *
 * @NoArgsConstructor和@AllArgsConstructor注解,分别对应无参构造和全参构造
 * @RequiredArgsConstructor(staticName = "of") 通过静态方法构建对象
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class ConstructorExample {

    private String name;
    private int age;
    private double score;

    public static void main(String[] args) {

        ConstructorExample data01 = new ConstructorExample();
        ConstructorExample data02 = new ConstructorExample("gavin",30,90.0);
        ConstructorExample of = ConstructorExample.of();

    }


}
