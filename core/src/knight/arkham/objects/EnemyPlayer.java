package knight.arkham.objects;

import knight.arkham.screens.GameScreen;

public class EnemyPlayer extends PlayerPaddle{


    public EnemyPlayer(float positionX, float positionY, GameScreen gameScreen) {
        super(positionX, positionY, gameScreen);
    }


    @Override
    public void update() {

        super.update();

        //la AI del enemigo lo unico que hara sera seguir la posicion en y de la pelota
        Ball ball = gameScreen.getBall();

        //los +10 y -10 son para darle algo de respiro a nuestro cpu player, y que su movimiento no sea tan estricto
        if (ball.getPositionY() + 10 > positionY && ball.getPositionY() - 10 > positionY)
            velocityY = 1;

        if (ball.getPositionY() + 10 < positionY && ball.getPositionY() - 10 < positionY)
            velocityY = -1;

        body.setLinearVelocity(0, velocityY * speed);
    }
}
