package by.ratkevich.objectpool;

import by.ratkevich.objectpool.pool.ConnectionPool;
import by.ratkevich.objectpool.task.ConnectionPoolTask;

/*
Реализация пула объектов с использованием семафора количеством разрешений 3.
 */

public class Main {
    public static void main(String[] args) {

        ConnectionPool connectionPool= new ConnectionPool(3);
        ConnectionPoolTask connectionTask = new ConnectionPoolTask(connectionPool);

        Thread thread1 = new Thread (connectionTask);
        thread1.start();

        Thread thread2 = new Thread (connectionTask);
        thread2.start();

        Thread thread3 = new Thread (connectionTask);
        thread3.start();

        Thread thread4 = new Thread (connectionTask);
        thread4.start();

        Thread thread5 = new Thread (connectionTask);
        thread5.start();

        Thread thread6 = new Thread (connectionTask);
        thread6.start();

        Thread thread7 = new Thread (connectionTask);
        thread7.start();
    }
}