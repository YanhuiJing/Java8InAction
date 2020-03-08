package algorithm.linkedlist;

import java.util.Objects;

/**
 * @author gavin
 * @createDate 2020/3/7
 */
public class NodeAction {

    public static void main(String[] args) {

        Node node01 = new Node(0, null);
        Node node02 = new Node(1, node01);
        Node node03 = new Node(2, node02);
        Node node04 = new Node(3, node03);
        Node node05 = new Node(4, node04);


        System.out.println(removeLastKNode(node05,3));

    }

    //反转单向链表
    //判断是否为回文串,首先进行链表反转然后进行元素对比
    public static Node reverseNode(Node node){
        Node curr = node;
        Node pre = null;

        //如果当前值不为空,将当前值的指针指向上一个元素,
        //交换当前和上一个元素的引用
        while(Objects.nonNull(curr)){
            // 首先取出当前元素的下一个元素用于引用交换
            Node next = curr.getNext();
            curr.setNext(pre);
            pre = curr;
            curr = next;
        }

        return pre;

    }

    // 利用速度差检测链表是否有环
    public static Boolean checkCircle(Node node){
        if(Objects.isNull(node)){
            return false;
        }

        Node fast = node.getNext();
        Node slow = node;

        while(Objects.nonNull(fast) && Objects.nonNull(fast.getNext())){

            fast = fast.getNext().getNext();
            slow = slow.getNext();

            if(fast == slow){
                return true;
            }
        }

        return false;

    }

    // 合并有序链表
    public static Node MergeSortList(Node node1,Node node2){
        Node soldier = new Node(0,null);
        Node p = null;

        while(Objects.nonNull(node1) && Objects.nonNull(node2)){

            if(node1.getData()<node2.getData()){
                p.setNext(node1);
                node1 = node1.getNext();
            }else{
                p.setNext(node2);
                node2 = node2.getNext();
            }

            if(Objects.isNull(node1)) {p.setNext(node2);}

            if(Objects.isNull(node2)) {p.setNext(node1);}

        }

        return soldier.getNext();
    }

    //删除倒数第k个节点
    public static Node removeLastKNode(Node node,int k){

        Node fast = node;

        int i = 0;
        while(Objects.nonNull(fast) && i<k){
            fast = fast.getNext();
            ++i;
        }

        if(Objects.isNull(fast)) {return fast;}

        Node slow = node;
        Node pre = null;
        while(Objects.nonNull(fast)){

            fast = fast.getNext();
            pre = slow;
            slow = slow.getNext();

        }

        if(Objects.isNull(pre)){
            node = node.getNext();
        }else {
            pre.setNext(pre.getNext().getNext());
        }

        return node;
    }

    //寻找中间节点
    public static Node findMidleNode(Node node){
        if(Objects.isNull(node)) {return node;}

        Node fast = node;
        Node slow = node;

        while(Objects.nonNull(fast) && Objects.nonNull(fast.getNext())){

            fast = fast.getNext().getNext();
            slow = slow.getNext();

        }

        return slow;

    }

}
