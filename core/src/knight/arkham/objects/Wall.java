package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import knight.arkham.helpers.BodyHelper;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.ContactType;
import knight.arkham.screens.GameScreen;
import static knight.arkham.helpers.Constants.FULL_SCREEN_WIDTH;
import static knight.arkham.helpers.Constants.MID_SCREEN_WIDTH;

public class Wall {

    private final Rectangle bounds;
    private final Texture wallTexture;


    public Wall(float positionY, GameScreen gameScreen) {

        bounds = new Rectangle(MID_SCREEN_WIDTH, positionY, FULL_SCREEN_WIDTH, 64);

        wallTexture = new Texture("img/wall.png");

        //isStatic es true debido a que es un objeto que no se moverá debido a que es una pared
        BodyHelper.createBody(new Box2DBody(bounds, true, 0, gameScreen.getGameWorld(), ContactType.WALL));
    }

    //como el objeto es static no hay necesidad de update metodo, pues este se utiliza para el cambio de position
    public void render(SpriteBatch batch){

        //como no utilizamos body para preparar la posición debido a que las paredes no se moverán
        // debemos de agregar estos cálculos a la posición, para indicar donde la pared será renderizada.
        batch.draw(wallTexture, bounds.x - (bounds.width / 2), bounds.y - (bounds.height / 2), bounds.width, bounds.height);
    }
}
