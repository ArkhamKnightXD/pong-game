package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.PongGame;
import knight.arkham.helpers.Constants;

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

        game.font.draw(game.batch, "Welcome to the Pong!!! ", Constants.MID_SCREEN_WIDTH-100, Constants.MID_SCREEN_HEIGHT);
        game.font.draw(game.batch, "Press Enter for single player", Constants.MID_SCREEN_WIDTH-100, Constants.MID_SCREEN_HEIGHT-50);
        game.font.draw(game.batch, "Press Space for 2 players vs!", Constants.MID_SCREEN_WIDTH-100, Constants.MID_SCREEN_HEIGHT-100);

        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){

            game.setScreen(new GameScreen(camera, true));
            dispose();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){

            game.setScreen(new GameScreen(camera, false));
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
