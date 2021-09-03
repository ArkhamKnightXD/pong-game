package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import knight.arkham.screens.GameScreen;

//implemento herencia basicamente, asi puedo heredar todos los metodos de mi clase padre
public class Player extends PlayerPaddle{

    public Player(float positionX, float positionY, GameScreen gameScreen) {

        super(positionX, positionY, gameScreen);
    }


    @Override
    public void update(){

        //llamo al metodo update de la clase padre
        super.update();

        //la velocidad sera pos o neg dependiendo de la tecla que presionemos
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            velocityY = 1;

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            velocityY = -1;

        //finalmente indicamos la velocidad a nuestro body
        body.setLinearVelocity(0, velocityY * speed);
    }
}
