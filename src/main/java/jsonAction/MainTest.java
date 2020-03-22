package jsonAction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.*;

/**
 * @Author : gavin
 * @Date : 2019-11-1315:22
 * @DESC : fastJson测试
 */
public class MainTest {

    public static void main(String[] args) {
//        jsonNullTest();
        jsonDateTest();

    }

    public static void jsonTest() {
        Grade group = new Grade();

        group.setId(1l);
        group.setName("admin");


        Student stu01 = new Student();
        stu01.setId(1001l);
        stu01.setName("张三");


        Student stu02 = new Student();
        stu02.setId(1002l);
        stu02.setName("赵四");

        group.addStudent(stu01);
        group.addStudent(stu02);

        String jsonString = JSON.toJSONString(group);
        System.out.println(jsonString);

        Grade grade = JSON.parseObject(jsonString, Grade.class);
        System.out.println(grade);
    }

    /**
     * SerializerFeature可以设置多种类型的序列化格式
     */
    public static void jsonNullTest() {

        List<String> strs = new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();

        map.put("test", strs);


        String text = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty);

        System.out.println(text);


    }


    public static void jsonDateTest() {
        Date date = new Date();

        String date01 = JSON.toJSONStringWithDateFormat(date, "yyyy-MM-dd HH:mm:ss");

        String date02 = JSON.toJSONString(date, SerializerFeature.UseISO8601DateFormat);

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        String date03 = JSON.toJSONString(date, SerializerFeature.WriteDateUseDateFormat);

        Map<String, Date> map= new HashMap<String, Date>();

        map.put("test",date);
        String date04 = JSON.toJSONString(map, SerializerFeature.WriteDateUseDateFormat);


        System.out.println(date01+"\n"+date02+"\n"+date03+"\n"+date04);
    }


}
