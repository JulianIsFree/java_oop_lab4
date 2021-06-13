package nsu.oop.car.factory.lab.visual.buttons.poolsize;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import nsu.oop.car.factory.lab.model.factory.providers.ProvidersNetA;
import nsu.oop.car.factory.lab.visual.buttons.TextButtonWithListener;

public class APoolSizeButton extends TextButtonWithListener {
    private final ProvidersNetA net;

    public APoolSizeButton(int x, int y, int width, int height, int value, Skin skin, ProvidersNetA net) {
        super(x, y, width, height, value, "A providers", skin);
        this.net = net;
    }

    @Override
    public void input(String text) {
        super.input(text);
        net.setPoolSize(value);
    }
}
