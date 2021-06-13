package nsu.oop.car.factory.lab.visual.buttons.storagesizes;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import nsu.oop.car.factory.lab.model.factory.details.Detail;
import nsu.oop.car.factory.lab.model.factory.storage.DetailStorage;
import nsu.oop.car.factory.lab.visual.buttons.TextButtonWithListener;

public class DetailStorageButton<T extends Detail> extends TextButtonWithListener {
    private final DetailStorage<T> storage;

    public DetailStorageButton(int x, int y, int width, int height, int value, String defaultMsg, Skin skin, DetailStorage<T> storage) {
        super(x, y, width, height, value, defaultMsg, skin);
        this.storage = storage;
    }

    @Override
    public void input(String text) {
        super.input(text);
        storage.setMaxSize(value);
    }
}
