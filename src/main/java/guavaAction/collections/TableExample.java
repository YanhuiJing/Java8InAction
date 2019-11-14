package guavaAction.collections;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * @Author : gavin
 * @Date : 2019-11-1414:23
 * @DESC : table案例
 */
public class TableExample {

    public static void main(String[] args) {

        testTable();

    }

    public static void testTable(){

        Table<String, String, String> table = HashBasedTable.create();
        table.put("Language", "Java", "1.8");
        table.put("Language", "Scala", "2.3");
        table.put("Database", "Oracle", "12C");
        table.put("Database", "Mysql", "7.0");
        System.out.println(table);

    }
}
