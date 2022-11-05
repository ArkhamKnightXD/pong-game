package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.PongGame;
import knight.arkham.helpers.GameContactListener;
import knight.arkham.objects.Ball;
import knight.arkham.objects.EnemyPlayer;
import knight.arkham.objects.Player;
import knight.arkham.objects.Wall;

import static knight.arkham.helpers.Constants.*;

public class GameScreen extends ScreenAdapter {

    private final PongGame game = PongGame.INSTANCE;

    private final Music gameMusic;

    private final OrthographicCamera camera;

    //este elemento viene de la libreria box2d
    private final World gameWorld;

    //Siempre que se utiliza World esta variable es necesaria para poder realizar debug de nuestro world
//    private final Box2DDebugRenderer box2DDebugRenderer;

    //players
    private final Player player;
    private final EnemyPlayer enemyPlayer;

    private final Ball ball;

    //creamos dos objetos pared una para la parte de arriba y otra para la de abajo
    private final Wall wallTop;
    private final Wall wallBottom;

    private final TextureRegion[] scoreNumbers;


    public GameScreen(OrthographicCamera globalCamera, boolean isCpuPlayer) {

        camera = globalCamera;


        //seteando la posicion que tendra nuestra camara en esta pantalla
        camera.position.set(new Vector3(MID_SCREEN_WIDTH, MID_SCREEN_HEIGHT, 0));

//        gameMusic = localAssetsManager.get("music/epic.wav", Music.class);
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music/epic.wav"));


        gameMusic.play();
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.05f);

        //para instanciar nuestro objeto world debemos pasarle un vector2 en el que especificamos la gravedad de X y Y
        //por ahora seran 0
        gameWorld = new World(new Vector2(0,0), false);

        //instanciamos nuestro player, con la posicion que deseamos que tenga, dividimos la altura para asi colocar
        //el player en la mitad de la pantalla
        player = new Player(16, MID_SCREEN_HEIGHT, this);
        enemyPlayer = new EnemyPlayer(FULL_SCREEN_WIDTH - 16, MID_SCREEN_HEIGHT, this, isCpuPlayer);

        ball = new Ball(this);

        wallBottom = new Wall(32, this);
        wallTop = new Wall(FULL_SCREEN_HEIGHT - 32, this);

        GameContactListener gameContactListener = new GameContactListener(this);

        //Luego de instanciar el gamecontactlistener se lo agregamos a nuestro world
        gameWorld.setContactListener(gameContactListener);

        scoreNumbers = loadTextureSprite("img/numbers.png", 10);

        //        Comentada mientras no se utilice
//        box2DDebugRenderer = new Box2DDebugRenderer();
    }

    //Creare un metodo update igual que en unity donde manejare la actualizacion de los objetos y lo llamare en render
    private void update(){

        //el 1/60 es porque el juego corre a 60fps, jugare con los valores luego que no se para que sirven
        gameWorld.step(1/60f, 6, 2);

        //camera.update debe ir antes de los metodos update de mis objetos, eso creo
        //para que los box2d se visualicen correctamente con el debugrenderer camera.update debe de implementarse
        camera.update();

        //camera combined envia la Camera's view and projection matrices., este metodo debe ir despues de camera.update
        game.batch.setProjectionMatrix(camera.combined);

        //actualizamos nuestro player
        player.update();
        enemyPlayer.update();

        ball.update();

        //si presionamos r la pelota resetea su posicion, isKeyJustPressed es mas preciso que iskeypressed
        //el otro verifica la pulsacion muchas mas veces que este
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)){

            ball.resetBallPosition();

            //setear el score de ambos player a 0 a la hora presionar r
            player.setScore(0);
            enemyPlayer.setScore(0);
        }

        //cerrara el juego cuando se presione la tecla escape
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){

            dispose();
            Gdx.app.exit();
        }

        setGameOverScreen();
    }

    private void setGameOverScreen() {

        if (player.getScore() > 4)
            game.setScreen(new MainMenuScreen(camera, true, true));


        else if (enemyPlayer.getScore() > 4)
            game.setScreen(new MainMenuScreen(camera, false, true));
    }


    @Override
    public void render(float delta) {

        update();

        ScreenUtils.clear(0,0,0,1);

        game.batch.begin();

        //Aqui renderizamos nuestro objetos
        player.render(game.batch);
        enemyPlayer.render(game.batch);

        ball.render(game.batch);

        wallTop.render(game.batch);
        wallBottom.render(game.batch);

        // el player score estara a la izquieda casi pegado de arriba y el enemy estara del lado derecho de la pantalla
        drawScoreNumbers(game.batch, player.getScore(), 64 , game.getScreenHeight() - 55, 30, 42);
        drawScoreNumbers(game.batch, enemyPlayer.getScore(), game.getScreenWidth()-96 ,
                game.getScreenHeight() - 55, 30, 42);

        game.batch.end();

        //se recomienda llamar el debugrenderer despues del batch.end
        //Aqui le paso el world y la camara combined esto lo utilizare para hacer debug de los elementos box2d creados
        //establecemos escala, porque sino se veria muy pequeño el debug, para hacer debug correctamente
        //debemos de comentar todos los elementos que deseemos debug en el spritebatch estos deben de tener box 2d obviamente
//        box2DDebugRenderer.render(gameWorld, camera.combined.scl(Constants.PIXELS_PER_METER));

        //sino se esta utilizando se debe dejar comentando
    }


    //Metodo encargado de manejar el spritesheet del score
    private TextureRegion[] loadTextureSprite(String fileName, int columns){

        Texture textureToSplit = new Texture(fileName);

        //le enviamos el spritesheet y los separamos, debemos indicar el ancho y dividirlo entre la cantidad de columnas
        //como solo tenemos una fila solo indicaremos el height y no habra division alguna
        //en conclusion esto me dividira el sprite en varias columnas y una fila
        return TextureRegion.split(textureToSplit, textureToSplit.getWidth()/columns, textureToSplit.getHeight())[0];
    }


    private void drawScoreNumbers(SpriteBatch batch, int scoreNumber, float x, float y, float width, float heigth){

        if (scoreNumber < 10)
            batch.draw(scoreNumbers[scoreNumber], x, y, width, heigth);

        //Aqui manejare poder imprimir numeros mayores de 10
        else {

            //debemos trabajar un string para esto, investigar como funciona subString
            batch.draw(scoreNumbers[Integer.parseInt((""+scoreNumber).substring(0, 1))], x, y, width, heigth);

            //le agrego 20 mas a la posicion en x pues cuando sean dos digitos el numero ocupara mas espacio y necesito moverlo
            batch.draw(scoreNumbers[Integer.parseInt((""+scoreNumber).substring(1, 2))], x+20, y, width, heigth);
        }
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {

        player.getPlayerTexture().dispose();
        enemyPlayer.getPlayerTexture().dispose();
        ball.getBallTexture().dispose();
        gameWorld.dispose();
        gameMusic.dispose();
    }


    public World getGameWorld() {
        return gameWorld;
    }

    public Ball getBall() { return ball; }

    public Player getPlayer() { return player; }

    public EnemyPlayer getEnemyPlayer() { return enemyPlayer; }
}
