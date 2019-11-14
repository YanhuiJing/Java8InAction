package guavaAction.utilities;

import com.google.common.base.Strings;
import lambdasinaction.chap11.Quote;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * @Author : gavin
 * @Date : 2019-11-1411:10
 * @DESC : 字符串案例
 */
public class StringExample {

    public static void main(String[] args) {
        testStringsMethod();
    }

    public static void testStringsMethod(){

        assertThat(Strings.emptyToNull(""),nullValue());
        assertThat(Strings.nullToEmpty(null),equalTo(""));
        assertThat(Strings.nullToEmpty("hello"),equalTo("hello"));
        assertThat(Strings.commonPrefix("hello","hit"),equalTo("h"));
        assertThat(Strings.commonPrefix("hello","gavin"),equalTo(""));
        assertThat(Strings.commonSuffix("hello","echo"), equalTo("o"));
        assertThat(Strings.isNullOrEmpty(null),equalTo(true));
        assertThat(Strings.isNullOrEmpty(""),equalTo(true));


        //字符串补位
        assertThat(Strings.padStart("7",3,'0'),equalTo("007"));
        assertThat(Strings.padEnd("7",3,'0'),equalTo("700"));



    }

}
