package nsu.oop.car.factory.lab.model.factory.providers;

import nsu.oop.car.factory.lab.model.factory.storage.DetailStorage;
import nsu.oop.car.factory.lab.model.factory.details.DetailTypeC;

import java.util.logging.Logger;

public class AddDetailTypeCTask extends AddDetailTask<DetailTypeC> {
    public AddDetailTypeCTask(DetailStorage<DetailTypeC> detailStorage, DetailTypeC detail, Logger logger) {
        super(detailStorage, detail, logger);
    }
}
