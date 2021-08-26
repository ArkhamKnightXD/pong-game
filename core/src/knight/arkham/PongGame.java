package knight.arkham;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import knight.arkham.screens.GameScreen;

public class PongGame extends Game {

	public static PongGame INSTANCE;

	public SpriteBatch batch;

	public PongGame() {

		INSTANCE = this;
	}

	@Override
	public void create () {

		int screenWidth = Gdx.graphics.getWidth();
		int screenHeight = Gdx.graphics.getHeight();

		batch = new SpriteBatch();

		OrthographicCamera globalCamera = new OrthographicCamera();
		globalCamera.setToOrtho(false, screenWidth, screenHeight);
		
		setScreen(new GameScreen(globalCamera));
	}


	public void render () { super.render(); }


	@Override
	public void dispose () {

		batch.dispose();
	}
}
