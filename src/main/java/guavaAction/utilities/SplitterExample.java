package guavaAction.utilities;

import com.google.common.base.Splitter;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @Author : gavin
 * @Date : 2019-11-1411:36
 * @DESC : Splitter案例
 */
public class SplitterExample {

    public static void main(String[] args) {
        testSplit_On_Split();
        testSplit_on_Split_OmitEmpty_TrimResult();
        testSplit_Fix_Length();
        testSplit_OnPattern_String();

    }

    public static void testSplit_On_Split(){

        List<String> result = Splitter.on("|").splitToList("hello|world");

        assertThat(result.size(), equalTo(2));

    }

    public static void testSplit_on_Split_OmitEmpty_TrimResult(){
        //去除空元素,去除字符串中的空格

        List<String> result = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("hello | world|||");

        assertThat(result.size(),equalTo(2));
        assertThat(result.get(0),equalTo("hello"));
        assertThat(result.get(1),equalTo("world"));

    }

    public static void testSplit_Fix_Length(){

        List<String> result = Splitter.fixedLength(4).splitToList("aaaabbbbcccc");
        assertThat(result.size(), equalTo(3));
        assertThat(result.get(1), equalTo("bbbb"));

    }

    public static void testSplit_OnPattern_String(){

        List<String> result = Splitter.onPattern("\\|").trimResults().omitEmptyStrings().splitToList("hello | world|||");

        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0),equalTo("hello"));
        assertThat(result.get(1),equalTo("world"));

    }






}
