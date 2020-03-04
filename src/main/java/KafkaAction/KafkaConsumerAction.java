package KafkaAction;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * kafkaConsumer
 * 消费者位移的管理,消费再均衡的原理
 *
 * @author gavin
 * @createDate 2020/3/4
 *
 * kafka的消费涉及消费偏移,消费者协调器,组协调器,消费者选举,分区分配的分发,再均衡的逻辑和心跳等内容
 *     1,再旧的消费者客户端中,消费者位移存在zookeeper中,在新的消费者客户端中,消费者位移存在kafka的内部主题_consumer_offsets.
 *       消费者再消费完消息之后,需要执行消费位移的提交
 *     2,消费再均衡原理
 *          触发再均衡的常见操作
 *          1,有新的消费者加入消费组
 *          2,有消费者宕机下线。消费者并不一定需要真正下线,例如长时间的gc,网络延迟消费等
 *          3,有消费者主动退出群组,例如客户端调用了unsubscribe()操作取消主题订阅
 *          4,消费组对应的GoupCoorinator节点发生变更
 *          5,消费组内所订阅的任一主题发生变更
 *
 *          再均衡过程:
 *          1,消费者需要确定所属消费组对应的GroupCoordinator所在的broker,并创建与该broker的通信连接
 *          2,消费者向GroupCoordiantor申请加入消费者组,将当前的消费位移提交给GroupCoordiantor进行持久化
 *          3,选举消费者leader,如果消费者组内没有leader,第一个加入的就是leader,如果消费者leader因为某些原因退出群组,随机选举一个leader
 *          4,选举分区分配策略,每个消费者上报分区策略,从候选分区策略中进行选举
 *          5,leader通过GroupCoordinator将选举出来的分区策略同步给其他消费者
 *          6,消费者根据新的分区策略,拉取新的消费位移开始消费
 *
 *
 *
 */
public class KafkaConsumerAction {
    public static final String brokerList = "192.168.57.101:9092,192.168.57.102:9092,192.168.57.103:9092";
    public static final String topic = "topic-demo";
    public static final String groupid = "group.demo";
    public static final AtomicBoolean isRunning = new AtomicBoolean(true);

    public static Properties inintConfig(){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,brokerList);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,groupid);
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG,"consumer.client.id.demo");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        return properties;

    }

    public static void main(String[] args) {

        Properties properties = inintConfig();

        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer(properties);

        kafkaConsumer.subscribe(Arrays.asList(topic));

        try{

            while(isRunning.get()){

                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(1000));

                for(ConsumerRecord<String,String> consumerRecord:records){
                    System.out.println(consumerRecord.topic()+"-"+consumerRecord.partition()+"-"+
                            consumerRecord.offset()+"-"+consumerRecord.key()+"-"+consumerRecord.value());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            kafkaConsumer.close();
        }


    }

    public void getTopicMetaInfo(KafkaConsumer kafkaConsumer){

        List<TopicPartition> partitions = new ArrayList<>();
//        public class PartitionInfo(分区元信息)
//        private final String topic; 主题名称
//        private final int partition; 分区号
//        private final Node leader; leader节点
//        private final Node[] replicas; 备份节点列表
//        private final Node[] inSyncReplicas; ISR节点列表
//        private final Node[] offlineReplicas; OSR节点列表
        List<PartitionInfo> partitionInfos = kafkaConsumer.partitionsFor(topic);

        if(partitionInfos != null){
            for(PartitionInfo partitionInfo:partitionInfos){
                partitions.add(new TopicPartition(partitionInfo.topic(),partitionInfo.partition()));
            }
        }

        //consumer可以通过assign消费执行partition消息
        kafkaConsumer.assign(partitions);


    }
    
}
