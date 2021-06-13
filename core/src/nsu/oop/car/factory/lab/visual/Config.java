package nsu.oop.car.factory.lab.visual;

public class Config {
    public final int AStorageSize;
    public final int BStorageSize;
    public final int CStorageSize;
    public final int carStorageSize;

    public final int ADelay;
    public final int BDelay;
    public final int CDelay;
    public final int merchantDelay;

    public final int APoolSize;
    public final int BPoolSize;
    public final int CPoolSize;
    public final int factoryPoolSize;
    public final int merchantsPoolSize;

    public Config() {
        AStorageSize = 100;
        BStorageSize = 100;
        CStorageSize = 100;
        carStorageSize = 25;
        ADelay = 5000;
        BDelay = 5000;
        CDelay = 5000;
        merchantDelay = 5000;
        APoolSize = 3;
        BPoolSize = 3;
        CPoolSize = 3;
        factoryPoolSize = 3;
        merchantsPoolSize = 3;
    }
}
