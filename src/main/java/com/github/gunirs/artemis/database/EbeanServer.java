package com.github.gunirs.artemis.database;

import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;

import java.util.Properties;

public class EbeanServer {
    private final Database database;

    public EbeanServer() {
        DatabaseConfig cfg = new DatabaseConfig();

        Properties properties = new Properties();
        properties.put("ebean.db.ddl.generate", "true");
        properties.put("ebean.db.ddl.run", "true");
        properties.put("datasource.db.username", "root");
        properties.put("datasource.db.password", "root");
        properties.put("datasource.db.databaseUrl","jdbc:mysql://127.0.0.1/bot");
        properties.put("datasource.db.databaseDriver", "com.mysql.jdbc.Driver");

        cfg.loadFromProperties(properties);
        this.database = DatabaseFactory.create(cfg);
    }

    public Database getDatabase() {
        return database;
    }
}
