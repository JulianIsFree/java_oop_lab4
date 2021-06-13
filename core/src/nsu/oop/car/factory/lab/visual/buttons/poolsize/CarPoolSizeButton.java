package nsu.oop.car.factory.lab.visual.buttons.poolsize;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import nsu.oop.car.factory.lab.model.factory.carfactory.CarFactoryNet;
import nsu.oop.car.factory.lab.visual.buttons.TextButtonWithListener;

public class CarPoolSizeButton extends TextButtonWithListener {
    private final CarFactoryNet net;

    public CarPoolSizeButton(int x, int y, int width, int height, int value, Skin skin, CarFactoryNet net) {
        super(x, y, width, height, value, "Car builders", skin);
        this.net = net;
    }

    @Override
    public void input(String text) {
        super.input(text);
        net.setPoolSize(value);
    }
}
