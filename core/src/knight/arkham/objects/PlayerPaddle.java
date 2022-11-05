package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import knight.arkham.helpers.BodyHelper;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.ContactType;
import knight.arkham.screens.GameScreen;

import static knight.arkham.helpers.Constants.PIXELS_PER_METER;

//En el package de objects estableceré todos los objetos referentes a mis entidades del juego, por ejemplo jugador
// o enemigos esta clase será luego extendidas por mis clases jugadores
public abstract class PlayerPaddle {

    //Aqui defino los distintos atributos de mi player, le pongo protected, para que estas propiedades
    //puedan ser accedidas por clases del mismo paquete

    //Este será el box2d del jugador
    protected Body body;

    protected Rectangle playerBounds;

    //Esta variable se encarga de manejar la velocidad en que se moverá nuestro personaje
    protected float speed;

//    Cuando trabajamos con física la variable velocidad es la encargada de manejar el movimiento del jugador
//    Asi que esta variable se le sumara o se le restara dependiendo hacia donde queremos ir
    protected float velocityY;
    protected int score;

    protected Texture playerTexture;

    protected GameScreen gameScreen;

    public PlayerPaddle(float positionX, float positionY, GameScreen gameScreen) {

        this.gameScreen = gameScreen;

        playerBounds = new Rectangle(positionX, positionY, 16, 64);
        speed = 10;

        playerTexture = new Texture("img/players.png");

        //Especificamos el body con los datos y al final indicamos que este body es de nuestro player,
        // la densidad en 10k es un valor razonable y puede cambiarse al gusto.
        body = BodyHelper.createBody(new Box2DBody(playerBounds, false, 10000,
                gameScreen.getGameWorld(), ContactType.PLAYER));
    }


    //Aqui actualizaremos la posición de nuestro player mediante el body
    public void update(){

        playerBounds.x = body.getPosition().x * PIXELS_PER_METER - (playerBounds.width / 2);
        playerBounds.y = body.getPosition().y * PIXELS_PER_METER - (playerBounds.height / 2);

        //necesario definir esto en 0, si no mi personaje continuara moviéndose aunque ya haya soltado el botón
        velocityY = 0;
    }

    //Aqui dibujaremos en pantalla nuestro player
    public void render(SpriteBatch batch){

        batch.draw(playerTexture, playerBounds.x, playerBounds.y, playerBounds.width, playerBounds.height);
    }


    public int getScore() { return score; }

    //Creamos un setter porque necesitamos resetear el score a 0 cuando inicie un nuevo juego
    public void setScore(int score) { this.score = score; }

    public Texture getPlayerTexture() { return playerTexture; }
}
