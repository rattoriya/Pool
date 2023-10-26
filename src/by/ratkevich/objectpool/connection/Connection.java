package by.ratkevich.objectpool.connection;

import java.util.Objects;

public class Connection {

    private int id;

    private boolean autocommit;

    public Connection (int id, boolean autocommit) {

        this.id = id;
        this.autocommit = autocommit;
    }

    public int getId() {
        return id;
    }

    public boolean isAutocommit() {
        return autocommit;
    }

    public void setAutocommit(boolean autocommit) {
        this.autocommit = autocommit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return id == that.id && autocommit == that.autocommit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, autocommit);
    }

    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", autocommit=" + autocommit +
                '}';
    }
}
