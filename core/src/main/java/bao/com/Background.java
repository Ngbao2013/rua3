package bao.com;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Background extends Actor {
    TextureRegion textureRegion;
    public Background(float x, float y , Stage s){
        textureRegion = new TextureRegion(new Texture("water-border.jpg"));
        setSize(1200,800);
        setPosition(x,y);
        s.addActor(this);




    }
    public void draw(Batch batch, float parentAlpha){
        batch.draw(textureRegion,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());

    }
}
