package knight.arkham;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import knight.arkham.screens.GameScreen;

public class PongGame extends Game {

	public static PongGame INSTANCE;

	public SpriteBatch batch;

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

		OrthographicCamera globalCamera = new OrthographicCamera();
		globalCamera.setToOrtho(false, screenWidth, screenHeight);
		
		setScreen(new GameScreen(globalCamera));
	}


	public int getScreenWidth() { return screenWidth; }

	public int getScreenHeight() { return screenHeight; }


	public void render () { super.render(); }


	@Override
	public void dispose () {

		batch.dispose();
	}
}
