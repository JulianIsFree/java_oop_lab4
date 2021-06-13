package nsu.oop.car.factory.lab.visual.buttons.poolsize;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import nsu.oop.car.factory.lab.model.factory.providers.ProvidersNetC;
import nsu.oop.car.factory.lab.visual.buttons.TextButtonWithListener;

public class CPoolSizeButton extends TextButtonWithListener {
    private final  ProvidersNetC net;

    public CPoolSizeButton(int x, int y, int width, int height, int value, Skin skin, ProvidersNetC net) {
        super(x, y, width, height, value, "C providers", skin);
        this.net = net;
    }
}
