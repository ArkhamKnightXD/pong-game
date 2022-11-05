package knight.arkham.helpers;

import com.badlogic.gdx.physics.box2d.*;
import knight.arkham.screens.GameScreen;

//Clase encargada del manejo de las colisiones entre box2d
public class GameContactListener implements ContactListener {

    private final GameScreen gameScreen;

    public GameContactListener(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void beginContact(Contact contact) {

        //buscamos los dos objetos que colisionaron
       Fixture fixtureA = contact.getFixtureA();
       Fixture fixtureB = contact.getFixtureB();

        //distintas comprobaciones para evitar error
        if (fixtureA.getUserData() == null || fixtureB.getUserData() == null)
            return;

        //comprobación de elementos, en esta comparación no me importaría el orden de los elementos
        if (fixtureA.getUserData() == ContactType.BALL || fixtureB.getUserData() == ContactType.BALL){

            //si Ball hit the - Player
            if (fixtureA.getUserData() == ContactType.PLAYER || fixtureB.getUserData() == ContactType.PLAYER){

                //al final si hay contacto entre estos dos elementos llamamos las dos funciones para que invierta
                // dirección X y aumente velocidad
                gameScreen.getBall().reverseVelocityX();
                gameScreen.getBall().incrementSpeed();
            }

            //si Ball hit the - Wall
            if (fixtureA.getUserData() == ContactType.WALL || fixtureB.getUserData() == ContactType.WALL){

                //Si golpea la pelota queremos que invierta su dirección vertical
                gameScreen.getBall().reverseVelocityY();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
