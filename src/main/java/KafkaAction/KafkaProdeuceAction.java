package KafkaAction;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.utils.Utils;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * kafkaProdeucer示例
 *
 * @author gavin
 * @createDate 2020/3/4
 * kafka集群启动命令
 * bin/kafka-server-start.sh config/server.properties
 * 后台启动命令
 * bin/kafka-server-start.sh -daemon config/server.properties
 * bin/kafka-server-start.sh config/server.properties &
 *
 * kafkaProducer发送数据的三种方式:
 *  发后即忘(fire-and-forget)
 *     只管发送消息,不关心数据是否发送成功,消息可能发生丢失,这种方式性能高,可靠性差
 *  同步（sync）
 *      同步发送可靠性高,消息要么发送成功,要么发生异常,性能会差很多,需要阻塞等待一条消息发送完成之后
 *      才能发送下一条
 *  异步(async)
 *
 *  kafkaProducer一般会发生两种类型异常:可重试的异常和不可重试的异常
 *      常见的可重试异常有:NetworkException,LeaderNotAvailableException,UnKnownTopicOrPartitionException
 *          NotEnoughReplicasException,NotCoordinatorException
 *        NetworkException表示网络异常,这个可能是由于网络故障导致的,可以通过重试进行解决
 *        LeaderNotAvailableException,表示leader分区副本不可用,这个异常通常发生在leader副本下线而新的leader副本
 *          没有选举完成之前,重试之后可以解决
 *      RecoderTooLargeException,表示发送消息太大,kafkaProducer对此不会进行重试,直接抛出异常
 *  kafkaProducer通过send()方法发往broker的过程,有可能需要经过拦截器(Interceptor),序列化器(Serializer)和分区器
 *      （Partitoner）一系列作用之后才能真正发往broker。三者均可以自定以实现
 *
 *  kafkaProducer参数设置:
 *     acks
 *     acks=1.默认值即为1,生产者发送消息之后,只要分区的leader副本写入消息,那么它就会收到来自服务端的成功响应
 *     acks=0.生产者发送消息之后不需要任何响应
 *     acks=-1或acks=all.生产者在消息发送之后,需要等待ISR中的所有副本都成功写入消息才能收到服务器端的响应,该参数需要配合
 *            min.insync.replicas(最小副本数)等参数设置
 *     max.request.size(默认值为1048576B,即1MB)
 *         如果broker端的message.max.bytes配置为10,而max.request.size配置为20,如果消息的大小为15则会报错
 *
 *
 */
public class KafkaProdeuceAction {

    public static final String brokerList = "192.168.57.101:9092,192.168.57.102:9092,192.168.57.103:9092";
    public static final String topic = "topic-demo";

    public static Properties inintConfig(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,brokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"producer.client.id.demo");
        //如果设置了retry次数,只要在规定的重试次数内自省恢复,就不会抛出异常
        properties.put(ProducerConfig.RETRIES_CONFIG,10);
        //ack参数设置必须为字符串
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,DemoPartitioner.class.getName());

        return properties;

    }


    public static void main(String[] args) {

        Properties properties = inintConfig();

        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>(properties);
        ProducerRecord<String,String> producerRecord = new ProducerRecord<>(topic,"hello flink");


        kafkaProducer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {

                if(Objects.nonNull(exception)){
                    exception.printStackTrace();
                }else {
                    System.out.println(metadata.topic()+"-"+metadata.partition()+"-"+metadata.offset());
                }

            }
        });

    }
}

/**
 * 自定义实现分区器,使用自定义分区器需要在配置中进行设置
 */
class DemoPartitioner implements Partitioner{

    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        Integer partitionCount = cluster.partitionCountForTopic(topic);

        if(Objects.nonNull(keyBytes)){
            return counter.getAndIncrement()%partitionCount;
        }else{
            return Utils.toPositive(Utils.murmur2(keyBytes)) % partitionCount;
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
