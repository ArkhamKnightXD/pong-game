package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import knight.arkham.screens.GameScreen;

public class EnemyPlayer extends PlayerPaddle {

    private final boolean isCpuPlayer;

    public EnemyPlayer(float positionX, float positionY, GameScreen gameScreen, boolean isCpuPlayer) {

        super(positionX, positionY, gameScreen);

        this.isCpuPlayer = isCpuPlayer;
    }

    @Override
    public void update() {

        super.update();

        cpuPlayerMovement();
    }

    private void cpuPlayerMovement() {
        //codigo encargado de manejar el movimiento cuando estemos jugando contra la cpu
        if (isCpuPlayer){

            //la AI del enemigo lo unico que hara sera seguir la posicion en y de la pelota
            Ball ball = gameScreen.getBall();
            //los +10 y -10 son para darle algo de respiro a nuestro cpu player, y que su movimiento no sea tan estricto
            if (ball.getBounds().y + 10 > playerBounds.y && ball.getBounds().y - 10 > playerBounds.y)
                velocityY = 0.75f;

            if (ball.getBounds().y + 10 < playerBounds.y && ball.getBounds().y - 10 < playerBounds.y)
                velocityY = -0.75f;
        }

        else {

            if (Gdx.input.isKeyPressed(Input.Keys.O))
                velocityY = 1.5f;

            if (Gdx.input.isKeyPressed(Input.Keys.L))
                velocityY = -1.5f;
        }
        
        body.setLinearVelocity(0, velocityY * speed);
    }
}
