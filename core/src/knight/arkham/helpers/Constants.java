package knight.arkham.helpers;

import knight.arkham.PongGame;

//Esta clase sera la encargada de manejar todas las constantes de mi juego
public class Constants {

    //si queremos mover objetos en nuestro juego debemos de mult o dividirlo por esta variable
    public static final float PIXELS_PER_METER = 32.0f;
    public static final int FULL_SCREEN_HEIGHT = PongGame.INSTANCE.getScreenHeight();
    public static final int FULL_SCREEN_WIDTH = PongGame.INSTANCE.getScreenWidth();
    public static final int MID_SCREEN_HEIGHT = PongGame.INSTANCE.getScreenHeight() / 2;
    public static final int MID_SCREEN_WIDTH = PongGame.INSTANCE.getScreenWidth() / 2;
}
