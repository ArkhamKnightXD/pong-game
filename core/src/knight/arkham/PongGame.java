package knight.arkham;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import knight.arkham.screens.MainMenuScreen;

public class PongGame extends Game {

	public static PongGame INSTANCE;

	public SpriteBatch batch;
	public BitmapFont font;

	private int screenWidth;
	private int screenHeight;

	public PongGame() {

		INSTANCE = this;
	}

	@Override
	public void create () {

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
		font = new BitmapFont();

		OrthographicCamera globalCamera = new OrthographicCamera();
		globalCamera.setToOrtho(false, screenWidth, screenHeight);
		
		setScreen(new MainMenuScreen(globalCamera));
	}


	public void render () { super.render(); }


	@Override
	public void dispose () {

		batch.dispose();
	}


	public int getScreenWidth() { return screenWidth; }

	public int getScreenHeight() { return screenHeight; }
}
