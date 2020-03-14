package guavaAction.cache;

import com.google.common.base.Function;
import com.google.common.cache.*;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author gavin
 * @createDate 2020/3/14
 */
public class GuavaCacheAction {

    public static void main(String[] args) throws InterruptedException {


//        evictionCacheByAccessTime();
//        evictionCacheByWriteTime();
//        evictionCacheBySize();
//        evictionCacheByWeight();
//        removeNotificationCache();
        specCache();

    }

    /**
     * 通过access过期时间创建cache
     * @throws InterruptedException
     */
    public static void evictionCacheByAccessTime() throws InterruptedException {

        LoadingCache<String, Employee> cacheBuilder = CacheBuilder.newBuilder()
                .expireAfterAccess(2, TimeUnit.SECONDS)
                .build(createCacheLoader());

        cacheBuilder.getUnchecked("gavin");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Objects.isNull(cacheBuilder.getIfPresent("gavin")));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Objects.isNull(cacheBuilder.getIfPresent("gavin")));
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Objects.isNull(cacheBuilder.getIfPresent("gavin")));

    }

    /**
     * 通过write过期时间创建cache
     * @throws InterruptedException
     */
    public static void evictionCacheByWriteTime() throws InterruptedException {

        LoadingCache<String, Employee> cacheBuilder = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(createCacheLoader());

        cacheBuilder.getUnchecked("gavin");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Objects.isNull(cacheBuilder.getIfPresent("gavin")));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Objects.isNull(cacheBuilder.getIfPresent("gavin")));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Objects.isNull(cacheBuilder.getIfPresent("gavin")));

    }


    /**
     * 通过size控制缓存的移除规则
     * @throws InterruptedException
     */
    public static void evictionCacheBySize() throws InterruptedException{

        LoadingCache<String, Employee> cacheBuilder = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build(createCacheLoader());

        cacheBuilder.getUnchecked("gavin");
        cacheBuilder.getUnchecked("jack");
        cacheBuilder.getUnchecked("tom");
        cacheBuilder.getUnchecked("allen");

        System.out.println(Objects.isNull(cacheBuilder.getIfPresent("gavin")));


    }

    /**
     * 通过指定权重指定元素移除规则
     * @throws InterruptedException
     */
    public static void evictionCacheByWeight() throws InterruptedException{

        LoadingCache<String, Employee> cacheBuilder = CacheBuilder.newBuilder()
                .maximumWeight(15)
                .weigher(new Weigher<String, Employee>() {

                    @Override
                    public int weigh(String key, Employee value) {
                        return key.length();
                    }
                }).build(createCacheLoader());

        cacheBuilder.getUnchecked("gavin");
        cacheBuilder.getUnchecked("allen");
        cacheBuilder.getUnchecked("Kevin");

        cacheBuilder.getUnchecked("jack");

        System.out.println(Objects.isNull(cacheBuilder.getIfPresent("gavin")));


    }

    /**
     * 通过数据预加载的方式创建缓存
     * @throws InterruptedException
     */
    public static void preLoadCache() throws InterruptedException{

        LoadingCache<String, String> cacheBuilder = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build(CacheLoader.from(String::toUpperCase));

        Map<String, String> preData = new HashMap<String, String>()
        {
            {
                put("alex", "ALEX");
                put("hello", "hello");
            }
        };

        cacheBuilder.putAll(preData);

        System.out.println(cacheBuilder.size());

    }

    /**
     * 缓存中元素移除通知,可以对元素移除做对应的处理
     */
    public static void removeNotificationCache(){
        CacheLoader<String, String> cacheLoader = CacheLoader.from(new Function<String, String>() {
            @Nullable
            @Override
            public String apply(@Nullable String input) {
                return input.toUpperCase();
            }
        });

        LoadingCache<String, String> cacheBuilder = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, String> notification) {
                        System.out.println("元素:" + notification.getKey() + "被移除");
                    }
                }).build(cacheLoader);

        cacheBuilder.getUnchecked("gavin");
        cacheBuilder.getUnchecked("jack");
        cacheBuilder.getUnchecked("allen");
        cacheBuilder.getUnchecked("tom");

    }

    /**
     * 通过配置文件创建cache
     */
    public static void specCache(){

        //可以通过配置文件的方式定义cache,详细的配置可以参看CacheBuilderSpec
        String spec = "maximumSize=3,recordStats";
        CacheBuilderSpec builderSpec = CacheBuilderSpec.parse(spec);
        LoadingCache<String, Employee> cacheBuilder = CacheBuilder.
                            from(builderSpec).build(createCacheLoader());

        cacheBuilder.getUnchecked("gavin");
        cacheBuilder.getUnchecked("tom");
        cacheBuilder.getUnchecked("jack");
        cacheBuilder.getUnchecked("allen");

        System.out.println(Objects.isNull(cacheBuilder.getIfPresent("gavin")));

    }

    public static CacheLoader<String,Employee> createCacheLoader(){

        CacheLoader<String, Employee> cacheLoader = CacheLoader.from(new Function<String, Employee>() {

            @Nullable
            @Override
            public Employee apply(@Nullable String key) {
                System.out.println("创建新对象:"+key);
                return new Employee(key, key, key);
            }
        });

        return cacheLoader;

    }

}
