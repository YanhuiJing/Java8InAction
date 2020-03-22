package designAction;

/**
 * @author gavin
 * @createDate 2020/3/22
 * 策略模式 => 开闭原则,对修改关闭,对扩展开放,
 *      对修改关闭,就是不希望别人修改我们的代码,此路不通
 *      对扩展开放,就是希望别人通过继承接口扩展新的功能
 *
 * 适用于多种策略的选择
 *
 */
public class StrageyAction {

    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        calculator.setOperaction(new MultipOperation());

        System.out.println(calculator.doOperaction(10,10));

    }

}

interface Operaction{
    int doOPeraction(int a,int b);
}

class AddOperaction implements Operaction{

    @Override
    public int doOPeraction(int a, int b) {
        return a+b;
    }
}

class MultipOperation implements Operaction{

    @Override
    public int doOPeraction(int a, int b) {
        return a * b;
    }
}

class Calculator{

    private Operaction operaction;

    public void setOperaction(Operaction operaction){
        this.operaction = operaction;
    }

    public int doOperaction(int a,int b){
        return operaction.doOPeraction(a,b);
    }

}
