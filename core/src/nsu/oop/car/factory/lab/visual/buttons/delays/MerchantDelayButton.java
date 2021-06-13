package nsu.oop.car.factory.lab.visual.buttons.delays;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import nsu.oop.car.factory.lab.model.factory.merchants.MerchantsNet;
import nsu.oop.car.factory.lab.visual.buttons.TextButtonWithListener;

public class MerchantDelayButton extends TextButtonWithListener {
    private final MerchantsNet net;

    public MerchantDelayButton(int x, int y, int width, int height, int value, Skin skin, MerchantsNet net) {
        super(x, y, width, height, value, "Merchant delay", skin);
        this.net = net;
    }

    @Override
    public void input(String text) {
        super.input(text);
        net.setDelay(value);
    }
}
