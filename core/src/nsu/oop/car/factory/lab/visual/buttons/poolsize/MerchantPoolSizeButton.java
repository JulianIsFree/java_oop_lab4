package nsu.oop.car.factory.lab.visual.buttons.poolsize;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import nsu.oop.car.factory.lab.model.factory.merchants.MerchantsNet;
import nsu.oop.car.factory.lab.visual.buttons.TextButtonWithListener;

public class MerchantPoolSizeButton extends TextButtonWithListener {
    private MerchantsNet net;

    public MerchantPoolSizeButton(int x, int y, int width, int height, int value, Skin skin, MerchantsNet net) {
        super(x, y, width, height, value, "Merchants", skin);
        this.net = net;
    }

    @Override
    public void input(String text) {
        super.input(text);
        net.setPoolSize(value);
    }
}
