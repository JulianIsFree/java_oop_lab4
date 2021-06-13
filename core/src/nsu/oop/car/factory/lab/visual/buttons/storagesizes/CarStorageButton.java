package nsu.oop.car.factory.lab.visual.buttons.storagesizes;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import nsu.oop.car.factory.lab.model.factory.storage.CarStorage;
import nsu.oop.car.factory.lab.visual.buttons.TextButtonWithListener;

public class CarStorageButton extends TextButtonWithListener {
    private CarStorage storage;

    public CarStorageButton(int x, int y, int width, int height, int value, Skin skin, CarStorage storage) {
        super(x, y, width, height, value, "Car storage limit", skin);
        this.storage = storage;
    }

    @Override
    public void input(String text) {
        super.input(text);
        storage.setMaxSize(value);
    }
}
