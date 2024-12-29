package bao.com;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Master extends Game{
    SpriteBatch batch;
    BitmapFont font;
    Stage stage;
    static int status = 1;

    GameScreen gameScreen;
    @Override
    public void create() {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Lonely Cake.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameters.size = 48;
        fontParameters.color = Color.YELLOW;
        font = fontGenerator.generateFont(fontParameters);
        fontGenerator.dispose();

        gameScreen = new GameScreen(this);
        this.setScreen(new MenuScreen(this));// goi man hinh moi
    }
    public void render(){
        super.render();
    }
}
