package nsu.oop.car.factory.lab.model.factory.providers;

import nsu.oop.car.factory.lab.model.factory.details.DetailTypeC;
import nsu.oop.car.factory.lab.model.factory.storage.DetailStorage;

import java.util.logging.Logger;

public class ProvidersNetC extends ProvidersNet {
    private final DetailStorage<DetailTypeC> storage;

    public ProvidersNetC(int delay, int poolSize, DetailStorage<DetailTypeC> storage, Logger logger) {
        super(delay, poolSize, logger);
        this.storage = storage;
    }

    @Override
    protected void execute() {
        storage.waitUntilNotFull();
        for (int i = 0; i < poolSize; ++i)
            providerPool.execute(new AddDetailTypeCTask(storage, new DetailTypeC(random.nextInt()), logger));
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
