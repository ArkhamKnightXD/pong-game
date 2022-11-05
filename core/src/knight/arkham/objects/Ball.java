package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import knight.arkham.helpers.BodyHelper;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.ContactType;
import knight.arkham.screens.GameScreen;

import static knight.arkham.helpers.Constants.*;

//clase encargada del manejo de la bola
public class Ball {

    private final Body body;

//    Con un rectangle almacenaré los datos referentes a la posición y height y width de la pelota.
//    De esta forma puedo manejar todos estos campos en una sola variable.
    private final Rectangle bounds;
    private float speed;

//    Lo que son la velocidad y posición es preferible manejarlo con vector
    private final Vector2 velocity;

    private final Texture ballTexture;
    private final GameScreen gameScreen;

    public Ball(GameScreen gameScreen) {

        this.gameScreen = gameScreen;

        bounds = new Rectangle(MID_SCREEN_WIDTH, MID_SCREEN_HEIGHT, 32, 32);

        speed = 8;

        //de esta forma preparo la velocidad, para determinar si la bola ira izq. O derecha
        velocity = new Vector2(getRandomDirection(), getRandomDirection());

        ballTexture = new Texture("img/white.png");

        //density = 0 ni idea por que iria asi en este caso
        body = BodyHelper.createBody(new Box2DBody(bounds, false,
                0, gameScreen.getGameWorld(), ContactType.BALL));
    }

    //función que se encargara de enviar la pelota en una dirección diferente siempre
    private float getRandomDirection(){

        //si el valor es 1 la bola ira der y si no izq
        return (Math.random() < 0.5) ? 1 : -1;
    }

//todos los objetos necesitan de un metodo update y render, el metodo update lo llamaré en la función
// update del gameScreen y los metodos render dentro del metodo render dentro del spritebatch
    public void update(){

        bounds.x = (int) (body.getPosition().x * PIXELS_PER_METER - (bounds.width / 2));
        bounds.y = (int) (body.getPosition().y * PIXELS_PER_METER - (bounds.height / 2));

        //preparo la velocidad aqui, ya que esta clase no hereda de otra o hereda hacia otra
        body.setLinearVelocity(velocity.x * speed, velocity.y * speed);

        //score
        if (bounds.x < 0){

            gameScreen.getEnemyPlayer().score += 1;
            resetBallPosition();
        }

        if (bounds.y > FULL_SCREEN_WIDTH){

            gameScreen.getPlayer().score += 1;
            resetBallPosition();
        }
    }


    public void render(SpriteBatch batch){

        batch.draw(ballTexture, bounds.x, bounds.y, bounds.width, bounds.height);
    }


    //Reset the ball position cuando salga de pantalla
    public void resetBallPosition(){

        //Pondré los valores de speed y velocity de forma normal para que la pelota continue moviéndose luego del reset
        velocity.x = getRandomDirection();
        velocity.y = getRandomDirection();
        speed = 8;

        //Preparo la position de la bola en la mitad de la pantalla, mediante transform, es como el transform de unity
        body.setTransform(MID_SCREEN_WIDTH / PIXELS_PER_METER, MID_SCREEN_HEIGHT / PIXELS_PER_METER, 0);
    }


    public void reverseVelocityX(){

        velocity.x *= -1;
    }


    public void reverseVelocityY(){

        velocity.y *= -1;
    }


    public void incrementSpeed(){

        velocity.x *= 1.1f;
    }
    public Texture getBallTexture() {return ballTexture;}

    public Rectangle getBounds() {return bounds;}
}
