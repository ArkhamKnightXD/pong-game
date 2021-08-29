package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import knight.arkham.screens.GameScreen;

//clase encargada del manejo de la bola
public class Ball {

    private Body body;
    private float positionX;
    private float positionY;
    private float speed;
    private float velocityY;
    private int width;
    private int height;
    private Texture ballTexture;
    private GameScreen gameScreen;

    public Ball() {
    }
}
