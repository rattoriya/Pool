package by.ratkevich.objectpool.task;

import by.ratkevich.objectpool.pool.AbstractPool;

public abstract class AbstractPoolTask<T> implements Runnable {

    private AbstractPool<T> pool;

    public AbstractPoolTask(AbstractPool<T> pool)  {
        this.pool = pool;
    }

    @Override
    public void run () {

         T object = pool.acquire();
         System.out.println( object + " was acquired");
         handle (object);
         System.out.println( object + " was released");
         pool.release(object);
    }

    protected abstract void handle (T t);

}
