package com.example.eventdriven.config;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfiguration {

    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        return xStream;
    }


//    @Bean
//    public TransactionManager axonTransactionManager(PlatformTransactionManager transactionManager) {
//        return new SpringTransactionManager(transactionManager);
//    }
//
//
//
//
//
//
//    @Qualifier("eventSerializer")
//    @Bean
//    public Serializer eventSerializer() {
//        return JacksonSerializer.builder().build();
//    }
//
//    @Bean
//    public EventStore eventStore(EventStorageEngine storageEngine, GlobalMetricRegistry metricRegistry) {
//        return EmbeddedEventStore.builder()
//                .storageEngine(storageEngine)
//                .messageMonitor(metricRegistry.registerEventBus("eventStore"))
//                 .build();
//    }
//
//    @Bean
//    public EventStorageEngine eventStorageEngine(Serializer serializer,
//                                                 PersistenceExceptionResolver persistenceExceptionResolver,
//                                                 @Qualifier("eventSerializer") Serializer eventSerializer,
//                                                 EntityManagerProvider entityManagerProvider,
//                                                 TransactionManager transactionManager) throws SQLException {
//
//        JpaEventStorageEngine eventStorageEngine = JpaEventStorageEngine.builder()
//                .snapshotSerializer(serializer)
//                .persistenceExceptionResolver(persistenceExceptionResolver)
//                .eventSerializer(serializer)
//                .entityManagerProvider(entityManagerProvider)
//                .transactionManager(transactionManager)
//                .build();
//
//        return eventStorageEngine;
//    }



//    @Bean
//    public EventStore eventStore(EventStorageEngine storageEngine,
//                                 GlobalMetricRegistry metricRegistry) {
//        return EmbeddedEventStore.builder()
//                .storageEngine(storageEngine)
//                .messageMonitor(metricRegistry.registerEventBus("eventStore"))
//                  .build();
//    }
//
//     @Bean
//    public EventStorageEngine storageEngine(MongoDatabaseFactory factory,
//                                            TransactionManager transactionManager) {
//        return MongoEventStorageEngine.builder()
//                .mongoTemplate(SpringMongoTemplate.builder().factory(factory).build())
//                .transactionManager(transactionManager)
//                .build();
//    }



}
