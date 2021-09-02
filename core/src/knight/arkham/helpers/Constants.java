package knight.arkham.helpers;

import knight.arkham.PongGame;

//Esta clase sera la encargada de manejar todas las constantes de mi juego
public class Constants {

    //si queremos mover objetos en nuestro juego debemos de mult o dividirlo por esta variable
    public static final float PIXELSPERMETER = 32.0f;

    public static final float MIDSCREENHEIGHT = PongGame.INSTANCE.getScreenHeight() / 2;
    public static final float MIDSCREENWIDTH = PongGame.INSTANCE.getScreenWidth() / 2;
}
