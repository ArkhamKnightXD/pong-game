package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import knight.arkham.helpers.BodyHelper;
import knight.arkham.helpers.Constants;
import knight.arkham.helpers.ContactType;
import knight.arkham.screens.GameScreen;

//clase encargada del manejo de la bola
public class Ball {

    private final Body body;
    private float positionX;
    private float positionY;
    private float speed;
    private float velocityY;
    private float velocityX;
    private final int width;
    private final int height;
    private final Texture ballTexture;
    private final GameScreen gameScreen;

    public Ball(GameScreen gameScreen) {

        this.positionX = Constants.MID_SCREEN_WIDTH;
        this.positionY = Constants.MID_SCREEN_HEIGHT;
        this.speed = 8;
        this.width = 32;
        this.height = 32;

        //de esta forma seteo la velocidad, para determinar si la bola ira izq o derecha
        this.velocityX = getRandomDirection();
        this.velocityY = getRandomDirection();

        this.gameScreen = gameScreen;
        this.ballTexture = new Texture("img/white.png");

        //density = 0 ni idea por que iria asi en este caso
        this.body = BodyHelper.createBody(positionX, positionY, width, height, false,
                0, gameScreen.getGameWorld(), ContactType.BALL);

    }

    //funcion que se encargara de enviar la pelota en una direccion diferente siempre
    private float getRandomDirection(){

        //si el valor es 1 la bola ira der y sino izq
        return (Math.random() < 0.5) ? 1 : -1;
    }

//todos los objetos necesitan de un metodo update y render, el metodo update lo llamare en la funcion update del gameScreen
    //y los metodos render dentro del metodo render dentro del spritebatch
    public void update(){

        positionX = body.getPosition().x * Constants.PIXELS_PER_METER - (width / 2);
        positionY = body.getPosition().y * Constants.PIXELS_PER_METER - (height / 2);

        //seteo la velocidad aqui ya que esta clase no hereda de otra o hereda hacia otra
        this.body.setLinearVelocity(velocityX * speed, velocityY * speed);

        //score
        if (positionX < 0){

            gameScreen.getEnemyPlayer().score += 1;
            resetBallPosition();
        }

        if (positionX > Constants.FULL_SCREEN_WIDTH){

            gameScreen.getPlayer().score += 1;
            resetBallPosition();
        }
    }


    public void render(SpriteBatch batch){

        batch.draw(ballTexture, positionX, positionY, width, height);
    }


    //Reset the ball position cuando salga de pantalla
    public void resetBallPosition(){

        //pondre los valores de speed y velocity de forma normal para que la pelo continue moviendose luego del reset
        this.velocityX = getRandomDirection();
        this.velocityY = getRandomDirection();
        this.speed = 8;

        //seteo la position de la bola en la mitad de la pantalla, mediante transform, es como el transform de unity
        this.body.setTransform(Constants.MID_SCREEN_WIDTH / Constants.PIXELS_PER_METER, Constants.MID_SCREEN_HEIGHT / Constants.PIXELS_PER_METER, 0);
    }


    public void reverseVelocityX(){

        velocityX *= -1;
    }


    public void reverseVelocityY(){

        velocityY *= -1;
    }


    public void incrementSpeed(){

        velocityX *= 1.1f;
    }


    public float getPositionY() { return positionY; }
}
