package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import knight.arkham.screens.GameScreen;

//En el package de objects establecere todos los objectos referentes a mis entidades del juego, por ejemplo jugador o enemigos
public abstract class PlayerPaddle {

    //Este sera el box2d del jugador
    private Body body;

    //Aqui defino los distintos atributos de mi player
    private float positionX;
    private float positionY;
    private float speed;
    private float velocityY;

    private int width;
    private int height;
    private int score;

    private Texture playerTexture;

    private GameScreen gameScreen;

    public PlayerPaddle(float positionX, float positionY, GameScreen gameScreen) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.gameScreen = gameScreen;
        this.speed = 6;
        this.width = 16;
        this.height = 64;

        this.playerTexture = new Texture("badlogic.jpg");
    }
}
