package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import knight.arkham.screens.GameScreen;

//implemento herencia básicamente, asi puedo heredar todos los metodos de mi clase padre
public class Player extends PlayerPaddle{

    public Player(float positionX, float positionY, GameScreen gameScreen) {

        super(positionX, positionY, gameScreen);
    }

    @Override
    public void update(){

        //llamo al metodo update de la clase padre
        super.update();

        playerMovement();
    }

    private void playerMovement() {
        //la velocidad será positiva o negativa dependiendo de la tecla que presionemos
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            velocityY = 1.5f;

        else if (Gdx.input.isKeyPressed(Input.Keys.S))
            velocityY = -1.5f;

        //finalmente, indicamos la velocidad a nuestro body
        body.setLinearVelocity(0, velocityY * speed);
    }

}
