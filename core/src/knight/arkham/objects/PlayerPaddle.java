package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import knight.arkham.helpers.BodyHelper;
import knight.arkham.helpers.Constants;
import knight.arkham.helpers.ContactType;
import knight.arkham.screens.GameScreen;

//En el package de objects establecere todos los objectos referentes a mis entidades del juego, por ejemplo jugador o enemigos
//esta clase sera luego extendidas por mis clases jugadores
public abstract class PlayerPaddle {

    //Este sera el box2d del jugador
    protected Body body;

    //Aqui defino los distintos atributos de mi player, le pongo protected, para que estas propiedades
    //puedan ser accedidas por clases del mismo paquete
    protected float positionX;
    protected float positionY;

    //Esta variable se encarga de manejar la velocidad en que se movera nuestro personaje
    protected float speed;
    protected float velocityY;

    protected int width;
    protected int height;
    protected int score;

    protected Texture playerTexture;

    protected GameScreen gameScreen;

    public PlayerPaddle(float positionX, float positionY, GameScreen gameScreen) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.gameScreen = gameScreen;
        this.speed = 10;
        this.width = 16;
        this.height = 64;

        this.playerTexture = new Texture("white.png");

        //Especificamos el body con los datos y al final indicamos que este body es de nuestro player, la densidad en 10k
        //es un valor razonable y puede cambiarse al gusto
        this.body = BodyHelper.createBody(positionX, positionY, width, height, false, 10000,
                gameScreen.getGameWorld(), ContactType.PLAYER);
    }


    //Aqui actualizaremos la posicion de nuestro player mediante el body
    public void update(){

        positionX = body.getPosition().x * Constants.PIXELS_PER_METER - (width / 2);
        positionY = body.getPosition().y * Constants.PIXELS_PER_METER - (height / 2);

        //necesario definir esto en 0, sino mi personaje continuara moviendose aunque ya haya soltado el boton
        velocityY = 0;
    }


    public void renderPlayer(SpriteBatch batch){

        batch.draw(playerTexture, positionX, positionY, width, height);
    }
}
