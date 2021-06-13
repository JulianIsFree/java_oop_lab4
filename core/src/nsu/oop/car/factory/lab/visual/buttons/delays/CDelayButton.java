package nsu.oop.car.factory.lab.visual.buttons.delays;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import nsu.oop.car.factory.lab.model.factory.providers.ProvidersNetC;
import nsu.oop.car.factory.lab.visual.buttons.TextButtonWithListener;

public class CDelayButton extends TextButtonWithListener {
    private final ProvidersNetC net;

    public CDelayButton(int x, int y, int width, int height, int value, Skin skin, ProvidersNetC net) {
        super(x, y, width, height, value, "C delay", skin);
        this.net = net;
    }

    @Override
    public void input(String text) {
        super.input(text);
        net.setDelay(value);
    }
}
