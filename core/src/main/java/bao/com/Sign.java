package bao.com;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Sign extends Actor {
    TextureRegion textureregion;
    int time;
    Polygon polygon;
    Sign(float x,float y, Stage s,Texture texture){
        textureregion = new TextureRegion(texture) ;
        setPosition(x,y);
        setSize(textureregion.getRegionWidth(), textureregion.getRegionHeight());
        s.addActor(this);
        float[]toadocacdiem = {
            25,0,
            25,6,
            0,6,
            0,49,
            25,49,
            31,55,
            38,49,
            64,49,
            64,6,
            38,6,
            38,0
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
