package uib.teamdank.cargame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uib.teamdank.cargame.gui.GameScreen;
import uib.teamdank.cargame.gui.PauseMenuScreen;
import uib.teamdank.cargame.gui.StartMenuScreen;
import uib.teamdank.common.Game;


/**
 * The main game class for Car Game.
 */
public class CarGame extends Game {
<<<<<<< HEAD
	StartMenuScreen startMenuScreen;
	GameScreen gameScreen;
	PauseMenuScreen pauseMenuScreen;
	SpriteBatch batch;
	String title = "Carl The Crasher";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void create() {
		startMenuScreen = new uib.teamdank.cargame.gui.StartMenuScreen();
		gameScreen = new uib.teamdank.cargame.gui.GameScreen();
		pauseMenuScreen = new uib.teamdank.cargame.gui.PauseMenuScreen();
		batch = new SpriteBatch();
=======
	private StartMenuScreen startMenuScreen;
	private GameScreen gameScreen;
	private PauseMenuScreen pauseMenuScreen;

    public static void main(String[] args){
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.fullscreen = true;
        config.title = "CarGame";
        config.width = 1280;
        config.height = 720;
        new LwjglApplication(new CarGame(), config) ;
    }
	
	@Override
	public void create() {
		startMenuScreen = new StartMenuScreen();
		gameScreen = new GameScreen();
		pauseMenuScreen = new PauseMenuScreen();
>>>>>>> master
		setScreen(startMenuScreen);
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
		return title;
	}

	@Override
	public SpriteBatch getSpriteBatch() {
		return batch;
	}
	
	@Override 
	public void dispose() {
		batch.dispose();
	}
}
