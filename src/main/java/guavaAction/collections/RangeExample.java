package guavaAction.collections;

import com.google.common.collect.Range;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * @Author : gavin
 * @Date : 2019-11-1415:11
 * @DESC : range案例
 */
public class RangeExample {

    public static void main(String[] args) {

        testColsedRange();

    }

    public static void testColsedRange() {
        Range<Integer> closedRange = Range.closed(0, 9);
        System.out.println(closedRange);

        assertThat(closedRange.lowerEndpoint(), equalTo(0));
        assertThat(closedRange.upperEndpoint(), equalTo(9));

    }

    public static void testOpenRange() {
        // a<x<b
        Range<Integer> openRange = Range.open(0, 9);

        // a<x<=b
        Range<Integer> openClosedRange = Range.openClosed(0, 9);

        // a<=x<b
        Range<Integer> closedOPen = Range.closedOpen(0, 9);
    }


}
