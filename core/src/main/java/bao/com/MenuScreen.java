package bao.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

import java.security.Key;

public class MenuScreen implements Screen {
    Texture sea;
    Stage stage;
    Texture ten;
    Texture s;
    Texture startButtonImage;
    Texture exitButtonImage;
    Master game;
    OrthographicCamera camera;
    GlyphLayout layout;
    public MenuScreen(Master game){
        this.game = game;
        stage = new Stage();
    }
    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch = new SpriteBatch();

        sea = new Texture("water.jpg");
        ten = new Texture("starfish-collector.png");
        s = new Texture("message-start.png");
        startButtonImage = new Texture("start.png");
        exitButtonImage = new Texture("exit.png");

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = game.font;
        style.fontColor = Color.WHITE;
        style.up = new TextureRegionDrawable(startButtonImage);
        TextButton startButton = new TextButton( "",style);
        startButton.setPosition(Gdx.graphics.getWidth()/2 - startButton.getWidth() - 10,
            Gdx.graphics.getHeight()/2 - startButton.getHeight() - 5) ;
        stage.addActor(startButton);
        Gdx.input.setInputProcessor(stage);
        startButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(game.gameScreen);
            }
        });

        TextButton.TextButtonStyle style2 = new TextButton.TextButtonStyle();
        style2.font = game.font;
        style2.fontColor = Color.WHITE;
        style2.up = new TextureRegionDrawable(exitButtonImage);
        TextButton exitButton = new TextButton( "",style2);
        exitButton.setPosition(Gdx.graphics.getWidth()/2 + 10,
            Gdx.graphics.getHeight()/2 - exitButton.getHeight() - 5) ;
        stage.addActor(exitButton);
        Gdx.input.setInputProcessor(stage);
        exitButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLUE);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(sea, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.draw(ten,Gdx.graphics.getWidth()/2 - ten.getWidth()/2,Gdx.graphics.getHeight()/2 - ten.getHeight()/2 + 100);
        game.batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
