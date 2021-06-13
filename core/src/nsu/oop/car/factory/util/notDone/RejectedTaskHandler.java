package nsu.oop.car.factory.util.notDone;

public interface RejectedTaskHandler {
    void handleRejected(Runnable task) throws Exception;
}
