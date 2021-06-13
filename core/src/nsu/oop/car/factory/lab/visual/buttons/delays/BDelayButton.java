package nsu.oop.car.factory.lab.visual.buttons.delays;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import nsu.oop.car.factory.lab.model.factory.providers.ProvidersNetB;
import nsu.oop.car.factory.lab.visual.buttons.TextButtonWithListener;

public class BDelayButton extends TextButtonWithListener {
    private final ProvidersNetB net;

    public BDelayButton(int x, int y, int width, int height, int value, Skin skin, ProvidersNetB net) {
        super(x, y, width, height, value, "B delay", skin);
        this.net = net;
    }

    @Override
    public void input(String text) {
        super.input(text);
        net.setDelay(value);
    }
}
