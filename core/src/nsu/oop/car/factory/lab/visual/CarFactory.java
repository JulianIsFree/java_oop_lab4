package nsu.oop.car.factory.lab.visual;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import nsu.oop.car.factory.lab.model.factory.carfactory.CarFactoryNet;
import nsu.oop.car.factory.lab.model.factory.carfactory.Controller;
import nsu.oop.car.factory.lab.model.factory.details.DetailTypeA;
import nsu.oop.car.factory.lab.model.factory.details.DetailTypeB;
import nsu.oop.car.factory.lab.model.factory.details.DetailTypeC;
import nsu.oop.car.factory.lab.model.factory.merchants.MerchantsNet;
import nsu.oop.car.factory.lab.model.factory.providers.ProvidersNetA;
import nsu.oop.car.factory.lab.model.factory.providers.ProvidersNetB;
import nsu.oop.car.factory.lab.model.factory.providers.ProvidersNetC;
import nsu.oop.car.factory.lab.model.factory.storage.CarStorage;
import nsu.oop.car.factory.lab.model.factory.storage.DetailStorage;
import nsu.oop.car.factory.lab.visual.buttons.delays.ADelayButton;
import nsu.oop.car.factory.lab.visual.buttons.delays.BDelayButton;
import nsu.oop.car.factory.lab.visual.buttons.delays.CDelayButton;
import nsu.oop.car.factory.lab.visual.buttons.delays.MerchantDelayButton;
import nsu.oop.car.factory.lab.visual.buttons.poolsize.*;
import nsu.oop.car.factory.lab.visual.buttons.storagesizes.CarStorageButton;
import nsu.oop.car.factory.lab.visual.buttons.storagesizes.DetailStorageButton;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarFactory extends ApplicationAdapter {
	private static int fps = 60;

	private static int buttonWidth = 80;
	private static int buttonHeight = 60;

	private DetailStorage<DetailTypeA> storageA;
	private DetailStorage<DetailTypeB> storageB;
	private DetailStorage<DetailTypeC> storageC;
	private CarStorage carStorage;
	private Controller controller;

	private int carCounter;
	private int detailsA;
	private int detailsB;
	private int detailsC;

	private SpriteBatch batch;
	private BitmapFont font;
	private Stage stage;
	private Skin skin;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		stage.clear();

		skin = new Skin();
		skin.addRegions(new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
		skin.add("default-font", font);
		skin.load(Gdx.files.internal("ui/uiskin.json"));

		carCounter = 0;
		detailsA = 0;
		detailsB = 0;
		detailsC = 0;

		initFonts();
		startFabric();
	}

	private void startFabric() {
		Logger logger = Logger.getLogger("log");
		try {
			FileHandler fileHandler = new FileHandler("log.txt");
			fileHandler.setLevel(Level.ALL);
			logger.addHandler(fileHandler);
			logger.setLevel(Level.ALL);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Config config = new Config();
		storageA = new DetailStorage<>(config.AStorageSize);
		storageB = new DetailStorage<>(config.BStorageSize);
		storageC = new DetailStorage<>(config.CStorageSize);
		carStorage = new CarStorage(config.carStorageSize);

		ProvidersNetA netA = new ProvidersNetA(config.ADelay, config.APoolSize, storageA, logger);
		ProvidersNetB netB = new ProvidersNetB(config.BDelay, config.BPoolSize, storageB, logger);
		ProvidersNetC netC = new ProvidersNetC(config.CDelay, config.CPoolSize, storageC, logger);

		CarFactoryNet carFactoryNet = new CarFactoryNet(config.factoryPoolSize, logger, storageA, storageB, storageC, carStorage);
		controller = new Controller(carStorage, carFactoryNet);
		MerchantsNet merchantsNet = new MerchantsNet(config.merchantDelay, config.merchantsPoolSize, logger,
				controller);

		new Thread(carFactoryNet).start();
		new Thread(netA).start();
		new Thread(netB).start();
		new Thread(netC).start();
		new Thread(merchantsNet).start();

		stage.addActor(new APoolSizeButton(600, 500, buttonWidth, buttonHeight, config.APoolSize, skin, netA));
		stage.addActor(new BPoolSizeButton(600, 400, buttonWidth, buttonHeight, config.BPoolSize, skin, netB));
		stage.addActor(new CPoolSizeButton(600, 300, buttonWidth, buttonHeight, config.CPoolSize, skin, netC));
		stage.addActor(new CarPoolSizeButton(600, 200, buttonWidth, buttonHeight, config.factoryPoolSize, skin, carFactoryNet));
		stage.addActor(new MerchantPoolSizeButton(600, 100, buttonWidth, buttonHeight, config.merchantsPoolSize, skin, merchantsNet));

		stage.addActor(new ADelayButton(800, 500, buttonWidth, buttonHeight, config.ADelay, skin, netA));
		stage.addActor(new BDelayButton(800, 400, buttonWidth, buttonHeight, config.BDelay, skin, netB));
		stage.addActor(new CDelayButton(800, 300, buttonWidth, buttonHeight, config.CDelay, skin, netC));
		stage.addActor(new MerchantDelayButton(800, 200, buttonWidth, buttonHeight, config.merchantDelay, skin, merchantsNet));

		stage.addActor(new CarStorageButton(1000, 500, buttonWidth, buttonHeight, config.carStorageSize, skin, carStorage));
		stage.addActor(new DetailStorageButton<DetailTypeA>(1000, 400, buttonWidth, buttonHeight, config.AStorageSize, "A detail limit", skin, storageA));
		stage.addActor(new DetailStorageButton<DetailTypeB>(1000, 300, buttonWidth, buttonHeight, config.AStorageSize, "B detail limit", skin, storageB));
		stage.addActor(new DetailStorageButton<DetailTypeC>(1000, 200, buttonWidth, buttonHeight, config.AStorageSize, "C detail limit", skin, storageC));
	}

	private void initFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arcon.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
		params.size = 48;
		params.color = Color.BLACK;
		font = generator.generateFont(params);
	}

	void update() {
		carCounter = carStorage.getCurrentSize();
		detailsA = storageA.getCurrentSize();
		detailsB = storageB.getCurrentSize();
		detailsC = storageC.getCurrentSize();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );


		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();

		update();

		batch.begin();
		font.draw(batch, "Cars: " + carCounter, 0, 100);
		font.draw(batch, "Sold: " + controller.getSoldCounter(), 0, 500);
		font.draw(batch, "Type A details: " + detailsA, 0, 400);
		font.draw(batch, "Type B details: " + detailsB, 0, 300);
		font.draw(batch, "Type C details: " + detailsC, 0, 200);
		stage.draw();
		batch.end();

		try {
			Thread.sleep(1000/fps);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
