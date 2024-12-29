package bao.com;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Whirlpool extends Actor {

    Animation<TextureRegion> animation;
    float time;
    Whirlpool(Texture texture, int cot, int hang){
        setSize(texture.getWidth()/cot, texture.getHeight()/hang);
        float speed = 0.05f;
        TextureRegion[][] tam = TextureRegion.split(texture, texture.getWidth()/cot, texture.getHeight()/hang);// đưa tất cả vào danh một danh sách ảnh, vì 6 cột 1 hàng nên sẽ có 6 phần tử: 6 x 1
        TextureRegion[] frames = new TextureRegion[cot*hang];
        int index = 0;
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < cot; j++) {
                frames[index++] = tam[i][j];
            }
        }
        animation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(speed, frames);
        animation.setPlayMode(Animation.PlayMode.NORMAL);
        time = 0;
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        time += delta;
        if(animation.isAnimationFinished(time)){
            remove();
        }
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion currentFrame = animation.getKeyFrame(time);
        batch.draw(currentFrame, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), 1, 1, getRotation());
    }
    public Rectangle getbounds(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
