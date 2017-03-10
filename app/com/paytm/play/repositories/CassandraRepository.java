package com.paytm.play.repositories;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import play.Logger;

import javax.inject.Inject;
import java.util.UUID;

public class CassandraRepository {

    private final Session session;
    private final Cluster cluster;

    private final String cassandra_host = "localhost";
    private final int cassandra_port = 9042;
    private final String cassandra_tableName = "testtable";
    private final String cassandra_dbName = "bolt";

    @Inject
    public CassandraRepository() {
        this.cluster = getCluster();
        this.session = getSession(cluster);
    }

    private Cluster getCluster() {
        return Cluster.builder().addContactPoint(cassandra_host).withPort(cassandra_port).build();
    }

    private Session getSession(Cluster cluster) {
        return cluster.connect(cassandra_dbName);
    }

    public String saveInCassandra(String data) {
        try {
            String key = UUID.randomUUID().toString();
            // INSERT INTO emp (emp_id, emp_name, emp_city,emp_phone, emp_sal) VALUES(2,'robin', 'Hyderabad', 9848022339, 40000);
            String query = "Insert into " + cassandra_dbName + "." + cassandra_tableName + " (id, data) values ('" + key + "', '" + data + "')";
            ResultSet resultSet = session.execute(query);
            return key;
        } catch (Exception e) {
            Logger.info("Exception Occured while saving to cassandra", e);
            throw e;
        }
    }
}