package uib.teamdank.cargame.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import uib.teamdank.cargame.CarGame;
import uib.teamdank.cargame.Player;
import uib.teamdank.common.Game;
import uib.teamdank.common.util.WeatherData;
import uib.teamdank.common.util.WeatherData.WeatherType;
import uib.teamdank.common.gui.CreditScreen;

public class StartMenuScreen implements uib.teamdank.common.gui.StartMenuScreen {
	private static final String LOGO = "Images/CarGameLogo.png";
	private static final String PLAY = "Images/Buttons/start.png";
	private static final String HIGHSCORE = "Images/Buttons/cg_highscore.png";
	private static final String SHOP = "Images/Buttons/bs_shop.png";
	private static final String CREDIT = "Images/Buttons/bs_credit.png";
	private static final String EXIT = "Images/Buttons/bs_quit.png";

	private Stage stages;
	private Texture myTexture;
	private Table menu;
	private ImageButton logoButton;
	private ImageButton playButton;
	private ImageButton highscoreButton;
	private ImageButton shopButton;
	private ImageButton creditButton;
	private ImageButton exitButton;
	private Array<Button> buttons;
	private HighscoreMenuScreen highscoreMenuScreen;
	private CreditScreen creditScreen;
	private Game game;
	private ShopScreen shopScreen;
	private Player player;
	private WeatherData wData;

	public StartMenuScreen(CarGame game) {
		this.game = game;
		stages = new Stage(new FitViewport(1920, 1080));
		highscoreMenuScreen = new HighscoreMenuScreen(game);
		creditScreen = new CreditScreen(game, "Images/Buttons/bs_back.png", "Data/credit_crasher.txt");
		shopScreen = new ShopScreen(game);
		buttons = new Array<Button>();
		menu = new Table();
		
		// Weather Data
		wData = new WeatherData();
		
		logoButton = setupButton(LOGO);
		playButton = setupButton(PLAY);
		highscoreButton = setupButton(HIGHSCORE);
		shopButton = setupButton(SHOP);
		creditButton = setupButton(CREDIT);
		exitButton = setupButton(EXIT);

		addButtonListener();
		addToTables();
		
		// Player initialization
		player = new Player(getWeather());
		player.getTexture();
		player.setScale(.5f);
				
				
		menu.setFillParent(true);
		stages.addActor(menu);
		Gdx.input.setInputProcessor(stages);
	}

	private void addToTables() {
		buttons.add(playButton);
		buttons.add(highscoreButton);
		buttons.add(shopButton);
		buttons.add(creditButton);
		buttons.add(exitButton);
		
		menu.add(logoButton).height((float) (logoButton.getHeight() /1.3)).pad(10, 0, 0, 0);
		menu.row();
		for (Button but : buttons) {
			menu.add(but).width((float) (but.getWidth() / 4)).height((float) (but.getHeight() / 4)).pad(5);
			menu.row();
		}
	}

	// Setting the buttons up
	public ImageButton setupButton(String imageString) {
		myTexture = new Texture(Gdx.files.internal(imageString));
		TextureRegion myTextureRegion = new TextureRegion(myTexture);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		ImageButton logo = new ImageButton(myTexRegionDrawable);
		return logo;
	}
	
	@Override
	public void exitGame() {
		Gdx.app.exit();
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stages.act(delta);
		stages.draw();
	}

	@Override
	public void resize(int width, int height) {
		stages.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stages);
	}

	@Override
	public void viewHighscores() {
		game.setScreen(highscoreMenuScreen);
	}

	public void viewCredit() {
		game.setScreen(creditScreen);
	}
	
	public void viewShop() {
		game.setScreen(shopScreen);
	}
	
	@Override
	public void newGame() {
		game.newGame();
	}
	
	@Override
	public void dispose() { 
		//TODO 
	}
	
	@Override
	public void pause() { 
		//TODO
	}
	
	@Override
	public void resume() { 
		//TODO
	}
	
	public Player getPlayer(){
		return player;
	}
	
	// 
	private void addButtonListener() {
		playButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage stage = event.getTarget().getStage();
				Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					game.setScreen(game.newGame());
				}
			}
		});

		highscoreButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage stage = event.getTarget().getStage();
				Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					viewHighscores();
				}
			}
		});

		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage stage = event.getTarget().getStage();
				Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					exitGame();
				}
			}
		});

		creditButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage stage = event.getTarget().getStage();
				Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					viewCredit();
				}
			}
		});
		
		shopButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Stage stage = event.getTarget().getStage();
				Vector2 mouse = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

				if (stage.hit(mouse.x, mouse.y, true) == event.getTarget()) {
					viewShop();
				}
			}
		});
	}
	
	public WeatherType getWeather() {
		return wData.pullWeather("Norway", "Hordaland", "Bergen", "Bergen");
	}
}