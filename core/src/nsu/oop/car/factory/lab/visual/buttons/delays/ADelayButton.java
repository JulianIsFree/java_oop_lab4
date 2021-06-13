package nsu.oop.car.factory.lab.visual.buttons.delays;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import nsu.oop.car.factory.lab.model.factory.providers.ProvidersNetA;
import nsu.oop.car.factory.lab.visual.buttons.TextButtonWithListener;

public class ADelayButton extends TextButtonWithListener {
    private final ProvidersNetA net;

    public ADelayButton(int x, int y, int width, int height, int value, Skin skin, ProvidersNetA net) {
        super(x, y, width, height, value, "A delay", skin);
        this.net = net;
    }

    @Override
    public void input(String text) {
        super.input(text);
        net.setDelay(value);
    }
}
