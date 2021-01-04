import javafx.application.Application;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import java.util.Properties;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
@ContextConfiguration(classes = SpringConfigurator.class)
public class BBTestAA {

    @Test
    public  void tt() throws InterruptedException {


        Properties properties = new Properties();
        properties.put("bootstrap.servers","172.20.3.195:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer = null;
        try{
            long ct = System.currentTimeMillis();
            producer = new KafkaProducer(properties);
            for(int i=0;i<1000000000;i++){
                ProducerRecord<String,String> message = new ProducerRecord<String, String>("pv-out-2",i+"");
                producer.send(message);

               if(i%1000000==0){
                   long ld = System.currentTimeMillis();
                   System.out.println("time :"+(ld-ct)/1000+"s"+"   message:+"+i);
               }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(producer != null){
                producer.close();
            }
        }


    }

}
