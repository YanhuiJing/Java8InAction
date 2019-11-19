package lombokAction;

import lombok.*;

/**
 * @Author : gavin
 * @Date : 2019-11-1914:44
 * @DESC : lombok插件@Data注解使用案例
 *
 * https://www.cnblogs.com/heyonggang/p/8638374.html
 *
 * @Data注解在类上,会为类的所有属性自动生成setter/getter,equals,canEqual,hashCode,toString方法
 *      如果为final属性,则不会为该属性生成setter方法
 */
@Data
public class DataExample {

    private String name;
    private int age;
    private double score;

    public static void main(String[] args) {

        DataExample data = new DataExample();


    }


}
