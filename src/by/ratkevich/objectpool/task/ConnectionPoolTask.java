package by.ratkevich.objectpool.task;

import by.ratkevich.objectpool.connection.Connection;
import by.ratkevich.objectpool.pool.AbstractPool;
import by.ratkevich.objectpool.pool.ConnectionPool;

public class ConnectionPoolTask extends AbstractPoolTask <Connection>{

    public ConnectionPoolTask (ConnectionPool pool) {
        super(pool);
    }
    @Override
    protected void handle (Connection connection) {
        connection.setAutocommit(false);
    }
}
