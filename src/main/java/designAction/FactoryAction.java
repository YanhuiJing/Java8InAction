package designAction;

/**
 * @author gavin
 * @createDate 2020/3/22
 */
public class FactoryAction {



}


class Girl{
}

class AGirl extends Girl{
}

class BGirl extends Girl{
}

class CGirl extends Girl{
}

/**
 * 定义抽象工厂实现方法
 */
interface AbstractGirlFactory{

    Girl createGirl(String girlType);

}

/**
 * 根据不同的需求创建不同类型的工厂
 */
class BigFactory implements AbstractGirlFactory{

    @Override
    public Girl createGirl(String girlType) {

        if("a".equals(girlType)){
            return new AGirl();
        }else if("b".equals(girlType)){
            return new BGirl();
        }else {
            return null;
        }
    }
}


class SmallFactory implements AbstractGirlFactory{

    @Override
    public Girl createGirl(String girlType) {
        if("c".equals(girlType)){
            return new CGirl();
        }else {
            return null;
        }
    }
}

/**
 * 工厂商店(类似于策略模式),传入指定类型的工厂,根据传入条件创建对应的对象
 */
class GirlStore{

    private AbstractGirlFactory abstractGirlFactory;

    public GirlStore(AbstractGirlFactory abstractGirlFactory){

        this.abstractGirlFactory = abstractGirlFactory;

    }

    public Girl createGirl(String girlType){
        return abstractGirlFactory.createGirl(girlType);
    }

}