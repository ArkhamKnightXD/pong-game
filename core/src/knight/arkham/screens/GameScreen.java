package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.PongGame;
import java.awt.*;

public class GameScreen extends ScreenAdapter {

    private final PongGame game = PongGame.INSTANCE;

    private final Texture img;

    private final Rectangle player;

    private final Rectangle cpuPlayer;

    private final Music gameMusic;

    private final int playerSpeed;

    private boolean isCpuPlayerUp;

    private final OrthographicCamera camera;

    private final World gameWorld;

    //Siempre que se utiliza World esta variable es necesaria para poder realizar debug de nuestro world
    private final Box2DDebugRenderer box2DDebugRenderer;

    public GameScreen(OrthographicCamera globalCamera) {

        img = new Texture("badlogic.jpg");

        camera = globalCamera;

        //seteando la posicion que tendra nuestra camara
        camera.position.set(new Vector3(game.getScreenWidth()/2, game.getScreenHeight()/2, 0));

        player = new Rectangle(0,0,128,128);
        cpuPlayer = new Rectangle(832,0,128,128);

        isCpuPlayerUp = true;

        playerSpeed = 500;

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music/epic.wav"));

        //para instanciar nuestro objeto world debemos pasarle un vector2 en el que especificamos la gravedad de X y Y
        //por ahora seran 0
        gameWorld = new World(new Vector2(0,0), false);
        box2DDebugRenderer = new Box2DDebugRenderer();
    }

    //Creare un metodo update igual que en unity donde manejare la actualizacion de los objetos y lo llamare en render
    private void update(){

        //el 1/60 es porque el juego corre a 60fps, jugare con los valores luego que no se para que sirven
        gameWorld.step(1/60f, 6, 2);

//        cameraUpdate();

        //camera combined envia la Camera's view and projection matrices.
        game.batch.setProjectionMatrix(camera.combined);


        //cerrara el juego cuando se presione la tecla escape
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){

            dispose();
            Gdx.app.exit();
        }
    }


    @Override
    public void render(float delta) {

        update();

        ScreenUtils.clear(0,0,0,0);

        game.batch.begin();

        game.batch.draw(img, player.x, player.y, player.width, player.height);
        game.batch.draw(img, cpuPlayer.x, cpuPlayer.y, cpuPlayer.width, cpuPlayer.height);

        game.batch.end();

        //se recomienda llamar el debugrenderer despues del batch.end
        //Aqui le paso el world y la camara combined e indico la escala de pixeles
//        box2DDebugRenderer.render(gameWorld, camera.combined.scl(PIXELSPERMETER));

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

        if(player.y > 512)
            player.y = 512;
    }


    private void cpuPlayerMovement() {

        if(cpuPlayer.y < 512 && isCpuPlayerUp){

            cpuPlayer.y += playerSpeed * Gdx.graphics.getDeltaTime();

            if (cpuPlayer.y >= 512)
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

        gameMusic.play();
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.05f);
    }


    @Override
    public void hide() {

    }


    @Override
    public void dispose() {

        gameWorld.dispose();
        img.dispose();
        gameMusic.dispose();
    }
}
