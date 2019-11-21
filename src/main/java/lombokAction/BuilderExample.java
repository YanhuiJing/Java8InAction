package lombokAction;

import lombok.Builder;

/**
 * @Author : gavin
 * @Date : 2019-11-2115:47
 * @DESC : @builder注解使用案例
 * @builder注解通过链式方法创建对象
 */
@Builder
public class BuilderExample {

    private String name;
    private int age;
    private double score;

    public static void main(String[] args) {

        BuilderExample gavin = BuilderExample.builder().name("gavin").age(30).score(100.0).build();
    }
}
