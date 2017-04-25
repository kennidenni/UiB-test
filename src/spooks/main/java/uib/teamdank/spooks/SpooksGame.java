package uib.teamdank.spooks;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uib.teamdank.common.Game;
import uib.teamdank.spooks.gui.GameScreen;
import uib.teamdank.spooks.gui.PauseMenuScreen;
import uib.teamdank.spooks.gui.StartMenuScreen;

/**
 * The main game class for Spooks.
 */
public class SpooksGame extends Game {
	private static final String TITLE = "Spooks";
	private StartMenuScreen startMenuScreen;
    private GameScreen gameScreen;
	private PauseMenuScreen pauseMenuScreen;
	private SpriteBatch batch;

    public static void main(String[] args){
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = TITLE;
        config.width = 1280;
        config.height = 720;
        new LwjglApplication(new SpooksGame(), config) ;
    }

	@Override
	public void create() {
		startMenuScreen = new StartMenuScreen();
		gameScreen = new GameScreen(this);
		pauseMenuScreen = new PauseMenuScreen();
		setScreen(startMenuScreen);
	}

	@Override
	public void newGame() {
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

	@Override
	public GameScreen getGameScreen() {
		return gameScreen;
	}

	@Override
	public PauseMenuScreen getPauseMenuScreen() {
		return pauseMenuScreen;
	}

	@Override
	public StartMenuScreen getStartMenuScreen() {
		return startMenuScreen;
	}

	@Override
	public String getTitle() {
		return TITLE;
	}

	@Override
	public SpriteBatch getSpriteBatch() {
		return batch;
	}
	
	@Override 
	public void dispose() {
		super.dispose();
		batch.dispose();
		screen.dispose();
	}
}
