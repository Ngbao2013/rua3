package bao.com;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Solid extends Actor {
    TextureRegion textureregion;
    int time;
    Polygon polygon;
    Solid(float x,float y, Stage s,Texture texture){
        textureregion = new TextureRegion(texture) ;
        setPosition(x,y);
        setSize(textureregion.getRegionWidth(), textureregion.getRegionHeight());
        s.addActor(this);
        float[]toadocacdiem = {
            4,18,
            3,39,
            18,60,
            33,63,
            50,57,
            59,29,
            50,14,
            26,7,
            15,10
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
        polygon.setPosition(getX(),getY());
        polygon.setRotation(getRotation());
    }

    public Rectangle getbounds(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    public Polygon getPolygon() {
        return polygon;
    }
}
