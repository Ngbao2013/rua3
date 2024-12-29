package bao.com;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.badlogic.gdx.Gdx.input;


public class Laze extends Actor {
    TextureRegion textureregion;
    Polygon polygon;
    float laserx;
    float lasery;
    Laze(float x, float y, Stage s){
        textureregion = new TextureRegion(new Texture("laser.png")) ;
        setPosition(x,y);
        setSize(textureregion.getRegionWidth(), textureregion.getRegionHeight());
        setOrigin(getWidth()/2,getHeight()/2);
        s.addActor(this);
        float[]toadocacdiem = {
            0,0,
            getWidth(),0,
            getWidth(),getHeight(),
            0,getHeight()
        };
        polygon = new Polygon(toadocacdiem);
        polygon.setOrigin(getWidth()/2,getHeight()/2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureregion, getX(), getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),1,1,getRotation());
        moveBy(laserx,lasery);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        polygon.setPosition(getX(),getY());
        polygon.setRotation(getRotation());
        if(input.isKeyPressed(Input.Keys.SPACE)){
            laserx = 10* MathUtils.cosDeg(getRotation());
            lasery = 10* MathUtils.sinDeg(getRotation());
        }
    }

    public Rectangle getbounds(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Polygon getPolygon() {
        return polygon;
    }
}
