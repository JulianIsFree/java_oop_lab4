package nsu.oop.car.factory.lab.visual.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public abstract class TextButtonWithListener extends TextButton implements Input.TextInputListener  {
    private class Listener extends ClickListener {
        private TextButtonWithListener button;
        private String hint;
        public Listener(TextButtonWithListener button, String hint) {
            this.button = button;
            this.hint = hint;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            Gdx.input.getTextInput(button, "", "", hint);
        }
    }

    private String defaultMsg;
    protected int value;

    public TextButtonWithListener(int x, int y, int width, int height, int value, String defaultMsg, Skin skin) {
        super(defaultMsg, skin, "default");
        this.defaultMsg = defaultMsg;
        this.value = value;

        this.setPosition(x, y);
        this.setSize(width, height);
        this.addListener(new Listener(this, String.valueOf(value)));
        this.setText(getString());
    }

    @Override
    public void input(String text) {
        try {
            value = Integer.valueOf(text);
            this.setText(getString());
        } catch (NumberFormatException ignored) {}
    }

    @Override
    public void canceled() {

    }

    protected String getString() {
        return defaultMsg + ": " + value;
    }
}
