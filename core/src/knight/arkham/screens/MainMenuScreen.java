package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.PongGame;

public class MainMenuScreen extends ScreenAdapter {

    private final PongGame game = PongGame.INSTANCE;

    private final OrthographicCamera camera;


    public MainMenuScreen(OrthographicCamera globalCamera) {

        camera = globalCamera;
    }


    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(0,0,0,0);

        game.batch.begin();

        game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);

        game.batch.end();

        if (Gdx.input.isTouched()){

            game.setScreen(new GameScreen(camera));
            dispose();
        }

    }


    @Override
    public void hide() {

    }


    @Override
    public void dispose() {

    }
}
