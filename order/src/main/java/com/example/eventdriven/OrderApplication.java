package com.example.eventdriven;

import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jdbc.HsqlEventTableFactory;
import org.axonframework.eventsourcing.eventstore.jdbc.JdbcEventStorageEngine;
import org.axonframework.spring.jdbc.SpringDataSourceConnectionProvider;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

//	@Configuration
//	class AxonConfiguration {
//
//		@Bean
//		public EventStorageEngine eventStorageEngine(DataSource dataSource,
//													 PlatformTransactionManager transactionManager) {
//			JdbcEventStorageEngine eventStorageEngine = new JdbcEventStorageEngine(
//					new SpringDataSourceConnectionProvider(dataSource),
//					new SpringTransactionManager(transactionManager)) {
//			};
//			eventStorageEngine.createSchema(HsqlEventTableFactory.INSTANCE);
//			return eventStorageEngine;
//		}
//	}

}
