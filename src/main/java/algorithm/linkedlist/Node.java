package algorithm.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author gavin
 * @createDate 2020/3/7
 */
@Data
@AllArgsConstructor
public class Node {
    private int data;
    private Node next;

    public static Node createNode(){
        return new Node(0,null);
    }

}
