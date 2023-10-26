package by.ratkevich.objectpool.pool;

import by.ratkevich.objectpool.connection.Connection;

import java.util.function.Supplier;

public class ConnectionPool extends AbstractPool <Connection> {

    public ConnectionPool (int size) {
        super (new ConnectionSupplier(), size);
    }

    @Override
    public void cleanObject (Connection connection) {
        connection.setAutocommit(true);
    }

    private static class ConnectionSupplier implements Supplier <Connection> {

        private int nextIndex;

       @Override
       public Connection get() {
           return new Connection(this.nextIndex++, true);
       }
    }
}
