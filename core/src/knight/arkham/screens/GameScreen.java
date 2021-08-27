package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.PongGame;
import java.awt.*;

public class GameScreen extends ScreenAdapter {

    private final PongGame game = PongGame.INSTANCE;

    private final Texture img;

    private final Rectangle player;

    private final Rectangle cpuPlayer;

    private final int playerSpeed;

    private boolean isCpuPlayerUp;

    private final OrthographicCamera camera;

    public GameScreen(OrthographicCamera globalCamera) {

        img = new Texture("badlogic.jpg");

        camera = globalCamera;

        player = new Rectangle(0,0,128,128);
        cpuPlayer = new Rectangle(1152,0,128,128);

        isCpuPlayerUp = true;

        playerSpeed = 500;
    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(0,0,0,0);

//        Por ahora no necesito esto
//        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(img, player.x, player.y, player.width, player.height);
        game.batch.draw(img, cpuPlayer.x, cpuPlayer.y, cpuPlayer.width, cpuPlayer.height);

        game.batch.end();

        playerMovement();
        cpuPlayerMovement();
    }


    private void playerMovement() {

        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            player.y += playerSpeed * Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            player.y -= playerSpeed * Gdx.graphics.getDeltaTime();

        if(player.y < 0)
            player.y = 0;

        if(player.y > 592)
            player.y = 592;
    }


    private void cpuPlayerMovement() {

        if(cpuPlayer.y < 592 && isCpuPlayerUp){

            cpuPlayer.y += playerSpeed * Gdx.graphics.getDeltaTime();

            if (cpuPlayer.y >= 592)
                isCpuPlayerUp = false;
        }

        if(cpuPlayer.y > 0 && !isCpuPlayerUp){

            cpuPlayer.y -= playerSpeed * Gdx.graphics.getDeltaTime();

            if (cpuPlayer.y <= 0)
                isCpuPlayerUp = true;
        }
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
