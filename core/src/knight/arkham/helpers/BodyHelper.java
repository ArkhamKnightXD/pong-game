package knight.arkham.helpers;

import com.badlogic.gdx.physics.box2d.*;

import static knight.arkham.helpers.Constants.PIXELS_PER_METER;

//Esta clase será la encargada de manejar todos nuestro body, que vienen de la librería box2d, box2d básicamente
//brinda a nuestros objetos con cuerpos físicos si 2 objetos que tienen un body definido se cruzan, estos chocaran y no
//pasaran de largo el uno con el otro
public class BodyHelper {

    //metodo encargado de la creación de un body con todos sus elementos
    public static Body createBody(Box2DBody box2DBody){

        BodyDef bodyDef = new BodyDef();
        //hay 3 tipos de body el dynamic, el kinematic y el static igual que en unity, probablemente es la misma librería
        //aqui utilizaré un simple if else, si static es falso que sea un dynamic body si no static
        //un objeto static son los que se quedaran quietos siempre, uno dynamic son los que se mueven
        bodyDef.type = box2DBody.isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(box2DBody.bounds.x / PIXELS_PER_METER, box2DBody.bounds.y / PIXELS_PER_METER);

        //con esto evitamos que nuestro body pueda rotar
        bodyDef.fixedRotation = true;

        //Creamos nuestro body, mediante nuestro world
        Body body = box2DBody.world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();

        //dividimos entre 2 para colocarlo en el medio y también debemos de dividir por nuestro pixel
        shape.setAsBox(box2DBody.bounds.width /2/PIXELS_PER_METER, box2DBody.bounds.height /2/PIXELS_PER_METER);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = box2DBody.density;

        //Agregamos el fixture def a nuestro body y finalmente retornamos, al final le pasamos que type tendra el body
        //para que a la hora de hacer collisions podamos reconocerlo de forma fácil
        body.createFixture(fixtureDef).setUserData(box2DBody.contactType);

        //luego de utilizada nuestro shape en el body ya no es necesaria
        shape.dispose();

        return body;
    }
}
