package knight.arkham.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.PongGame;

public class GameScreen extends ScreenAdapter {

    private final PongGame game = PongGame.INSTANCE;

    private final Texture img;

    private final OrthographicCamera camera;

    public GameScreen(OrthographicCamera globalCamera) {

        img = new Texture("badlogic.jpg");

        camera = globalCamera;
    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(0,0,0,0);

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(img, 0,0);

        game.batch.end();
    }


    @Override
    public void show() {

    }


    @Override
    public void hide() {

    }


    @Override
    public void dispose() {

        img.dispose();
    }
}
