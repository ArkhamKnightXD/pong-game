package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.PongGame;
import knight.arkham.helpers.Constants;
import knight.arkham.objects.Ball;
import knight.arkham.objects.EnemyPlayer;
import knight.arkham.objects.Player;
import knight.arkham.objects.Wall;
import java.awt.*;

public class GameScreen extends ScreenAdapter {

    private final PongGame game = PongGame.INSTANCE;

    private final Rectangle rectangle;

    private final Rectangle cpuPlayer;

    private final Music gameMusic;

    private final int playerSpeed;

    private boolean isCpuPlayerUp;

    private final OrthographicCamera camera;

    private final World gameWorld;

    //Siempre que se utiliza World esta variable es necesaria para poder realizar debug de nuestro world
    private final Box2DDebugRenderer box2DDebugRenderer;

    //player
    private Player player;
    private EnemyPlayer enemyPlayer;

    private Ball ball;

    //creamos dos objetos pared una para la parte de arriba y otra para la de abajo
    private Wall wallTop;
    private Wall wallBottom;

    public GameScreen(OrthographicCamera globalCamera) {

        camera = globalCamera;

        //seteando la posicion que tendra nuestra camara
        camera.position.set(new Vector3(Constants.MID_SCREEN_WIDTH, Constants.MID_SCREEN_HEIGHT, 0));

        rectangle = new Rectangle(0,0,128,128);
        cpuPlayer = new Rectangle(832,0,128,128);

        isCpuPlayerUp = true;

        playerSpeed = 500;

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music/epic.wav"));

        //para instanciar nuestro objeto world debemos pasarle un vector2 en el que especificamos la gravedad de X y Y
        //por ahora seran 0
        gameWorld = new World(new Vector2(0,0), false);
        box2DDebugRenderer = new Box2DDebugRenderer();

        //instanciamos nuestro player, con la posicion que deseamos que tenga, dividimos la altura para asi colocar
        //el player en la mitad de la pantalla
        player = new Player(16, Constants.MID_SCREEN_HEIGHT, this);
        enemyPlayer = new EnemyPlayer(game.getScreenWidth() - 16, Constants.MID_SCREEN_HEIGHT, this);

        ball = new Ball(this);

        wallBottom = new Wall(32, this);
        wallTop = new Wall(game.getScreenHeight() - 32, this);
    }

    //Creare un metodo update igual que en unity donde manejare la actualizacion de los objetos y lo llamare en render
    private void update(){

        //el 1/60 es porque el juego corre a 60fps, jugare con los valores luego que no se para que sirven
        gameWorld.step(1/60f, 6, 2);

        //camera.update debe ir antes de los metodos update de mis objetos, eso creo
        camera.update();

        //actualizamos nuestro player
        player.update();
        enemyPlayer.update();

        ball.updateBallPosition();

        //camera combined envia la Camera's view and projection matrices., este metodo debe ir despues de camera.update
        game.batch.setProjectionMatrix(camera.combined);

        //si presionamos r la pelota resetea su posicion, isKeyJustPressed es mas preciso que iskeypressed
        //el otro verifica la pulsacion muchas mas veces que este
        if (Gdx.input.isKeyJustPressed(Input.Keys.R))
            ball.resetBallPosition();

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

        //Aqui renderizamos nuestro objetos
        player.renderPlayer(game.batch);
        enemyPlayer.renderPlayer(game.batch);

        ball.renderBall(game.batch);

        wallTop.render(game.batch);
        wallBottom.render(game.batch);

        game.batch.end();

        //se recomienda llamar el debugrenderer despues del batch.end
        //Aqui le paso el world y la camara combined esto lo utilizare para hacer debug de los elementos box2d creados
        //establecemos escala, porque sino se veria muy pequeño el debug, para hacer debug correctamente
        //debemos de comentar todos los elementos que deseemos debug en el spritebatch estos deben de tener box 2d obviamente
        box2DDebugRenderer.render(gameWorld, camera.combined.scl(Constants.PIXELS_PER_METER));

//        playerMovement();
//        cpuPlayerMovement();
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


    public World getGameWorld() {
        return gameWorld;
    }

    public Ball getBall() { return ball; }

    public Player getPlayer() { return player; }

    public EnemyPlayer getEnemyPlayer() { return enemyPlayer; }

    @Override
    public void dispose() {

        gameWorld.dispose();
        gameMusic.dispose();
    }
}
