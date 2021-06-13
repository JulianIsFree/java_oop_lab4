package nsu.oop.car.factory.lab.model.factory.providers;

import nsu.oop.car.factory.lab.model.factory.details.DetailTypeB;
import nsu.oop.car.factory.lab.model.factory.storage.DetailStorage;

import java.util.logging.Logger;

public class ProvidersNetB extends ProvidersNet {
    private final DetailStorage<DetailTypeB> storage;;

    public ProvidersNetB(int delay, int poolSize, DetailStorage<DetailTypeB> storage, Logger logger) {
        super(delay, poolSize, logger);
        this.storage = storage;
    }

    @Override
    protected void execute() {
        storage.waitUntilNotFull();
        for (int i = 0; i < poolSize; ++i)
            providerPool.execute(new AddDetailTypeBTask(storage, new DetailTypeB(random.nextInt()), logger));
    }

    public void setPoolSize(int value) {
        if (value > poolSize) {
            providerPool.setMaximumPoolSize(value);
            providerPool.setCorePoolSize(value);
        } else {
            providerPool.setCorePoolSize(value);
            providerPool.setMaximumPoolSize(value);
        }

        poolSize = value;
    }
}
