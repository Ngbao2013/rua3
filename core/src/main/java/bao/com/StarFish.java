package bao.com;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class StarFish extends Actor {
    TextureRegion textureregion;
    int time;
    Polygon polygon;
    StarFish(float x,float y, Stage s){
        textureregion = new TextureRegion(new Texture("starfish.png")) ;
        setPosition(x,y);
        setSize(textureregion.getRegionWidth(), textureregion.getRegionHeight());
        s.addActor(this);
        float[]toadocacdiem = {
            10,5,
            16,21,
            1,25,
            1,33,
            23,35,
            25,52,
            32,53,
            39,34,
            59,28,
            58,23,
            51,22,
            43,19,
            49,4,
            42,2,
            27,12,
            14,1
        };
        polygon = new Polygon(toadocacdiem);
        polygon.setOrigin(getWidth()/2,getHeight()/2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureregion, getX(), getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),1,1,getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setOrigin(getWidth()/2,getHeight()/2);
        polygon.setPosition(getX(),getY());
        polygon.setRotation(getRotation());
        rotateBy(-1);
    }

    public Rectangle getbounds(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    public Polygon getPolygon() {
        return polygon;
    }
}
