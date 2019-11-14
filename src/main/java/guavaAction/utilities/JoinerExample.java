package guavaAction.utilities;

import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @Author : gavin
 * @Date : 2019-11-1410:54
 * @DESC : Joiner案例
 */
public class JoinerExample {

    private static final List<String> stringList = Arrays.asList(
            "Google", "Guava", "Java", "Scala", "Kafka"
    );

    private static final List<String> stringListWithNullValue = Arrays.asList(
            "Google", "Guava", "Java", "Scala", null
    );

    public static void main(String[] args) {
//        testJoinOnJoin();
//        testJoinOnJoinWithNullValueButSkip();
//        testJoinOnJoinWithNullValueUsedefault();
    }

    public static void testJoinOnJoin() {
        String result = Joiner.on("#").join(stringList);
        assertThat(result, equalTo("Google#Guava#Java#Scala#Kafka"));
    }

    public static void testJoinOnJoinWithNullValueButSkip(){
        String result = Joiner.on("#").skipNulls().join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Guava#Java#Scala"));
    }

    public static void testJoinOnJoinWithNullValueUsedefault(){
        String result = Joiner.on("#").useForNull("default").join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Guava#Java#Scala#default"));
    }




}
