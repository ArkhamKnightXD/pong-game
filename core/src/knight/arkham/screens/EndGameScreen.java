package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.PongGame;
import knight.arkham.helpers.Constants;

public class EndGameScreen extends ScreenAdapter {

    //Inicializo mi clase game con la instancia de game que ya existe
    private final PongGame game = PongGame.INSTANCE;

    private final OrthographicCamera camera;

    private final Sound playerWinSound;

    private final boolean player1HasWin;

    public EndGameScreen(OrthographicCamera globalCamera, boolean player1HasWin) {

        this.player1HasWin = player1HasWin;

        camera = globalCamera;

        playerWinSound = Gdx.audio.newSound(Gdx.files.internal("fx/win.wav"));
    }


    @Override
    public void show() {

        playerWinSound.play(0.1f);
    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0.2f, 1);

        game.batch.begin();

        if (player1HasWin)
            game.font.draw(game.batch, "Player 1 won the game", Constants.MID_SCREEN_WIDTH-100, Constants.MID_SCREEN_HEIGHT);

        else
            game.font.draw(game.batch, "Player 2 won the game", Constants.MID_SCREEN_WIDTH-100, Constants.MID_SCREEN_HEIGHT);

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
    public void dispose() {

        playerWinSound.dispose();
    }
}
