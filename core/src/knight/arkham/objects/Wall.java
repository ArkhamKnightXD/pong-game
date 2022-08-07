package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import knight.arkham.helpers.BodyHelper;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Constants;
import knight.arkham.helpers.ContactType;
import knight.arkham.screens.GameScreen;

public class Wall {

    private final float positionX;
    private final float positionY;
    private final int width;
    private final int height;
    private final Texture wallTexture;


    public Wall(float positionY, GameScreen gameScreen) {

        this.positionY = positionY;
        positionX = Constants.MID_SCREEN_WIDTH;
        width = Constants.FULL_SCREEN_WIDTH;
        height = 64;
        wallTexture = new Texture("img/wall.png");

        //isStatic es true debido a que es un objeto que no se movera debido a que es una pared
        BodyHelper.createBody(new Box2DBody(positionX, positionY, width, height,
                true, 0, gameScreen.getGameWorld(), ContactType.WALL));
    }

    //como el objeto es estatico no hay necesidad de update metodo, pues este se utiliza para el cambio de position
    public void render(SpriteBatch batch){

        //como no utilizamos body para setear pos debido a que no existe el metodo update
        // debemos de agregar estos calculos a la posicion, para dejar la pared en la misma posicion
        batch.draw(wallTexture, positionX - (width / 2), positionY - (height / 2), width, height);
    }
}
