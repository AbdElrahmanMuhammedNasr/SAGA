//package com.example.eventdriven.config;
//
//import org.axonframework.eventsourcing.eventstore.jdbc.EventSchema;
//import org.axonframework.eventsourcing.eventstore.jdbc.EventTableFactory;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//@Component
//public class CustomEventTableFactory implements EventTableFactory {
//
//
//    @Override
//    public PreparedStatement createDomainEventTable(Connection connection, EventSchema schema) throws SQLException {
//        return null;
//    }
//
//    @Override
//    public PreparedStatement createSnapshotEventTable(Connection connection, EventSchema schema) throws SQLException {
//        return null;
//    }
//}
