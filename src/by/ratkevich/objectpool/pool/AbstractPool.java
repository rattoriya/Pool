package by.ratkevich.objectpool.pool;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public abstract class AbstractPool <T> {

    private List<PoolObject<T>> poolObjects;
    private Semaphore semaphore;

    public AbstractPool (Supplier<T> supplier, int size) {

        this.poolObjects = creationOfPoolObjects( supplier, size);
        semaphore = new Semaphore (size);
    }

    private <T> List<PoolObject<T>> creationOfPoolObjects (Supplier<T> supplier, int size) {
        return IntStream.range(0, size).mapToObj ((i)-> supplier.get()).map((Object)-> new PoolObject<>(Object,false)).toList();
    }

    public T acquire () {

        this.semaphore.acquireUninterruptibly();
        return acquireObject();
    }

    private synchronized T acquireObject () {

        return poolObjects.stream().filter(poolObject -> !poolObject.isIssued()).findFirst().map(poolObject -> markAsIssued(poolObject)).map(PoolObject::getObject).get();
    }

    public PoolObject<T>  markAsIssued ( PoolObject<T>  t) {
        t.setIsIssued(true);
        return t;
    }

    public void release (T t) {

        if (releaseObject(t)) {
            semaphore.release();
        }

    }

    private synchronized boolean releaseObject (T t) {
        return poolObjects.stream().filter(PoolObject::isIssued).filter (poolObject -> poolObject.getObject().equals(t)).findFirst().map(poolObject -> cleanPoolObject(poolObject)).isPresent();
    }

    public abstract void cleanObject (T t);

    public PoolObject<T> cleanPoolObject (PoolObject<T> poolObject) {

        poolObject.setIsIssued(false);
        cleanObject(poolObject.getObject());

        return poolObject;
    }


    private class PoolObject <T> {

        private T object;
        private boolean isIssued;

        public PoolObject (T object, boolean isIssued) {
            this.object = object;
            this.isIssued = isIssued;
        }

        public T getObject () {
            return object;
        }

        public boolean isIssued () {
            return isIssued;
        }

        public void setIsIssued (boolean isIssued) {
            this.isIssued = isIssued;
        }

    }
}
