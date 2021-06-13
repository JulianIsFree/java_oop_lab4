package nsu.oop.car.factory.lab.model.factory.storage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Isn't synchronized at all
 * Synchronization routines must be held by user of storage
 * @param <T> T means Class of Entity stored in storage
 */
public abstract class EntityStorage<T extends Entity> {
    private static int capacityLimit = 1000;

    private AtomicInteger maxSize;
    private final ArrayList<T> storage;

    protected EntityStorage(int maxSize) {
        this.maxSize = new AtomicInteger(maxSize);
        this.storage = new ArrayList<>();
    }

    public void add(T e)  {
        synchronized (storage) {
            while(storage.size() >= maxSize.get()) {
                try {
                    storage.wait();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            if (storage.size() < maxSize.get())
                storage.add(e);

            storage.notify();
        }
    }

    public T extractFirst() {
        T e;
        synchronized (storage) {
            while (storage.size() <= 0) {
                try {
                    storage.wait();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            e = storage.get(0);
            storage.remove(e);
            storage.notify();
        }
        return e;
    }

    public int getCurrentSize() {
        return storage.size();
    }

    public int getMaxSize() {
        return maxSize.get();
    }

    public void setMaxSize(int maxSize) {
        maxSize = capacityLimit > maxSize ? maxSize : capacityLimit;
        this.maxSize.set(maxSize);
    }

    public boolean isFull() {
        return storage.size() >= maxSize.get();
    }

    public void waitUntilNotFull() {
        synchronized (storage) {
            while (storage.size() >= maxSize.get()) {
                try {
                    storage.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            storage.notify();
        }
    }

    public void waitUntilNotEmpty() {
        synchronized (storage) {
            while (storage.size() <= 0) {
                try {
                    storage.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            storage.notify();
        }
    }
}
