package guavaAction.eventbus;

import com.google.common.eventbus.EventBus;
import guavaAction.eventbus.listeners.ConcreteListener;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/18
 * 532500648
 ***************************************/
public class InheritListenersEventBusExample
{
    public static void main(String[] args)
    {
        final EventBus eventBus = new EventBus();
        eventBus.register(new ConcreteListener());
        System.out.println("post the string event");
        eventBus.post("I am string event");
        System.out.println("post the int event");
        eventBus.post(1000);
    }
}
