package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import knight.arkham.helpers.BodyHelper;
import knight.arkham.helpers.Constants;
import knight.arkham.helpers.ContactType;
import knight.arkham.screens.GameScreen;

public class Wall {

    private final Body body;
    private final float positionX;
    private final float positionY;
    private final int width;
    private final int height;
    private final Texture wallTexture;


    public Wall(float positionY, GameScreen gameScreen) {

        this.positionX = Constants.MID_SCREEN_WIDTH;
        this.positionY = positionY;
        this.width = Constants.FULL_SCREEN_WIDTH;
        this.height = 64;
        this.wallTexture = new Texture("img/wall.png");

        //isStatic es true debido a que es un objeto que no se movera debido a que es una pared
        this.body = BodyHelper.createBody(positionX, positionY, width, height, true,
                0, gameScreen.getGameWorld(), ContactType.WALL);
    }

    //como el objeto es estatico no hay necesidad de update metodo, pues este se utiliza para el cambio de position
    public void render(SpriteBatch batch){

        //como no utilizamos body para setear pos debido a que no existe el metodo update debemos de agregar estos calculos a la posicion
        batch.draw(wallTexture, positionX - (width / 2), positionY - (height / 2), width, height);
    }
}
