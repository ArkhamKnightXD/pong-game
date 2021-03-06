package knight.arkham.helpers;

import com.badlogic.gdx.physics.box2d.*;

//Esta clase sera la encargada de manejar todos nuestros body, que vienen de la libreria box2d, box2d  basicamente
//brinda a nuestro objetos con cuerpos fisicos si 2 objetos que tienen un body definido se cruzan, estos chocaran y no
//pasaran de largo el uno con el otro
public class BodyHelper {

    //metodo encargado de la creacion de un body con todos sus elementos
    public static Body createBody(float x, float y, float width, float height, boolean isStatic, float density, World world, ContactType type){

        BodyDef bodyDef = new BodyDef();
        //hay 3 tipos de body el dinamico, el kinematico y el statico igual que en unity, probablemente es la misma libreria
        //aqui utilizare un simple if else, si static es falso que sea un dinamic body sino statci
        //un objeto static son los que se quedaran quietos siempre, uno dinamico son los que se mueven
        bodyDef.type = !isStatic ? BodyDef.BodyType.DynamicBody : BodyDef.BodyType.StaticBody;

        bodyDef.position.set(x / Constants.PIXELS_PER_METER, y /Constants.PIXELS_PER_METER);

        //con esto evitamos que nuestro body pueda rotar
        bodyDef.fixedRotation = true;

        //Creamos nuestro body, mediante nuestro world
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();

        //dividimos entre 2 para colocarlo en el medio y tambien debemos de dividir por nuestro pixel
        shape.setAsBox(width / 2 / Constants.PIXELS_PER_METER, height /2 /Constants.PIXELS_PER_METER);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;

        //Agregamos el fixture def a nuestro body y finalmente retornamos, al final le pasamos que type tendra el body
        //para que a la hora de hacer collisiones podamos reconocerlo de forma facil
        body.createFixture(fixtureDef).setUserData(type);

        //luego de utilizada nuestro shape en el body ya no es necesaria
        shape.dispose();

        return body;
    }
}
