package nsu.oop.car.factory.lab.model.factory.providers;

import nsu.oop.car.factory.lab.model.factory.storage.DetailStorage;
import nsu.oop.car.factory.lab.model.factory.details.DetailTypeB;

import java.util.logging.Logger;

public class AddDetailTypeBTask extends AddDetailTask<DetailTypeB> {
    public AddDetailTypeBTask(DetailStorage<DetailTypeB> detailStorage, DetailTypeB detail, Logger logger) {
        super(detailStorage, detail, logger);
    }
}
