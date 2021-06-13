package nsu.oop.car.factory.lab.visual.buttons.poolsize;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import nsu.oop.car.factory.lab.model.factory.providers.ProvidersNetB;
import nsu.oop.car.factory.lab.visual.buttons.TextButtonWithListener;

public class BPoolSizeButton extends TextButtonWithListener {
    private ProvidersNetB net;

    public BPoolSizeButton(int x, int y, int width, int height, int value, Skin skin, ProvidersNetB net) {
        super(x, y, width, height, value, "B providers", skin);
        this.net = net;
    }

    @Override
    public void input(String text) {
        super.input(text);
        net.setPoolSize(value);
    }
}
