package bao.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Turtle extends Actor {
    Animation<TextureRegion> animation;
    float time;
    Polygon polygon;
    Float speed = 0f;
    Stage stage;
    Boolean koda = true;

    Turtle(Texture texture,int cot,int hang,Stage s){
        s.addActor(this);
        setSize(texture.getWidth()/cot, texture.getHeight()/hang);
        float speed = 0.1f;
        TextureRegion[][] tam = TextureRegion.split(texture, texture.getWidth()/cot, texture.getHeight()/hang);// đưa tất cả vào danh một danh sách ảnh, vì 6 cột 1 hàng nên sẽ có 6 phần tử: 6 x 1
        TextureRegion[] frames = new TextureRegion[cot*hang];
        int index = 0;
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < cot; j++) {
                frames[index++] = tam[i][j];
            }
        }
        animation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(speed, frames);
        animation.setPlayMode(Animation.PlayMode.LOOP);
        time = 0;
        float[]toadocacdiem = {
            7,48,
            14,33,
            18,29,
            30,22,
            59,24,
            73,37,
            74,54,
            71,59,
            60,70,
            46,72,
            40,70,
            18,66,
            10,58
        };
        polygon = new Polygon(toadocacdiem);
        polygon.setOrigin(getWidth()/2,getHeight()/2);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setOrigin(getWidth()/2,getHeight()/2);
        polygon.setPosition(getX(),getY());
        polygon.setRotation(getRotation());
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            time += delta;
        }
        if(koda){
            if(Gdx.input.isKeyPressed(Input.Keys.UP) && speed<6f){
                speed += 1f;
//                stage.getCamera().position.y += 1;


            }
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                rotateBy(2);
//                if(getX()>Gdx.graphics.getWidth()){
//                    rotateBy(-2);
//                }
//                if(getX()<0){
//                    rotateBy(-2);
//
//                }
            }
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                rotateBy(-2);
//                if(getX()>Gdx.graphics.getWidth()){
//                    rotateBy(2);
//                }
//                if(getX()<0){
//                    rotateBy(2);

 //               }
            }
            speed *= 1f;
        }
        moveBy(speed * MathUtils.cosDeg(getRotation()),speed * MathUtils.sinDeg(getRotation()));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion currentFrame = animation.getKeyFrame(time);
        batch.draw(currentFrame, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), 1, 1, getRotation());
    }
    public Rectangle getbounds(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    public Polygon getPolygon() {
        return polygon;
    }
}
