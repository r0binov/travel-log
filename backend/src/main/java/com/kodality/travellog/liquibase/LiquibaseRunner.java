package com.kodality.travellog.liquibase;

import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.sql.SQLException;

@Singleton
public class LiquibaseRunner{

    private final DataSource dataSource;

    @Inject
    public LiquibaseRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @EventListener
    public void onStartup(StartupEvent event) throws LiquibaseException, SQLException {
        JdbcConnection connection = new JdbcConnection(dataSource.getConnection());
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(connection);
        database.setDefaultSchemaName("public");

        Liquibase liquibase = new Liquibase("db/changelog/changelog.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update(new Contexts());
    }
}
