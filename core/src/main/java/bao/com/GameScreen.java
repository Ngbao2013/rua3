package bao.com;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

import static com.badlogic.gdx.Gdx.input;

public class GameScreen implements Screen {
    OrthographicCamera camera;
    Texture logo;
    Master game;
    Stage stage;
    Background nen;
    Turtle turtle;
    Solid rock;
    Solid rock2;
    Solid rock3;
    Solid rock4;
    Sign sigh;
    Sign sigh2;
    StarFish baoquan;
    StarFish haiquan;
    StarFish bao;
    StarFish sao;
    StarFish nawn;
    Shark tank;
    Shark tank2;
    Shark tank3;
    ShapeRenderer shapeRenderer;
    Random ran;
    Whirlpool an;
    Sound dropSound;
    Sound lasersound;
    Music nenMusic;
    Texture rockImage;
    Texture sighImage;
    Texture win;
    Texture gameover;
    Texture pressc;
    Texture soundButtonImage;
    int starfish = 5;
    Array<Laze> lasers;
    Boolean kt = false;
    Boolean audio = true;
    public GameScreen(Master game){
        this.game = game;
        stage = new Stage();
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        ran = new Random();

        kt = false;
        starfish = 5;

        lasers = new Array<>();

        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop1.ogg"));
        lasersound = Gdx.audio.newSound(Gdx.files.internal("sfx_laser1.ogg"));
        nenMusic = Gdx.audio.newMusic(Gdx.files.internal("assets_Master_of_the_Feast.ogg"));

        nen = new Background(0,0,stage);
        rockImage = new Texture("rock.png");
        sighImage = new Texture("sign.png");
        win = new Texture("you-win.png");
        gameover = new Texture("game-over.png");
        pressc = new Texture("message-continue.png");
        soundButtonImage = new Texture("audio.png");

        rock = new Solid(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight()-64),stage,rockImage);
        rock2 = new Solid(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage,rockImage);
        rock3 = new Solid(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage,rockImage);
        rock4 = new Solid(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage,rockImage);
        sigh = new Sign(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage,sighImage);
        sigh2 = new Sign(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage,sighImage);
        turtle = new Turtle(new Texture("sprite.png"),3,2,stage);
        baoquan = new StarFish(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage) ;
        haiquan = new StarFish(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage) ;
        bao = new StarFish(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage) ;
        nawn = new StarFish(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage) ;
        sao = new StarFish(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage) ;
        tank = new Shark(Gdx.graphics.getWidth() - 100,Gdx.graphics.getHeight() - 10,stage);


        tank2 = new Shark(Gdx.graphics.getWidth() - 300,Gdx.graphics.getHeight() - 300,stage);
        tank3 = new Shark(Gdx.graphics.getWidth() - 30,Gdx.graphics.getHeight() - 500,stage);

        rock.polygon.setPosition(rock.getX(),rock.getY());
        rock2.polygon.setPosition(rock2.getX(),rock2.getY());
        rock3.polygon.setPosition(rock3.getX(),rock3.getY());
        rock4.polygon.setPosition(rock4.getX(),rock4.getY());
        sigh .polygon.setPosition(sigh .getX(),sigh .getY());
        sigh2.polygon.setPosition(sigh2.getX(),sigh2.getY());
        turtle.polygon.setPosition(turtle .getX(),turtle .getY());
        tank.polygon.setPosition(tank.getX(),tank.getY());
        tank2.polygon.setPosition(tank2.getX(),tank2.getY());
        tank3.polygon.setPosition(tank3.getX(),tank3.getY());


        stage.act();
        System.out.println(kt);


//        game.layout = new GlyphLayout();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = game.font;
        style.fontColor = Color.WHITE;
        style.up = new TextureRegionDrawable(soundButtonImage);

        TextButton startButton = new TextButton( "",style);
        startButton.setPosition(Gdx.graphics.getWidth() - startButton.getWidth() - 5,
            Gdx.graphics.getHeight() - startButton.getHeight() - 5) ;
        stage.addActor(startButton);
        Gdx.input.setInputProcessor(stage);
        startButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                if(audio){
                    audio = false;
                }else{
                    audio = true;
                }
            }
        });

        System.out.println(stage.getCamera().position.x + " "+stage.getCamera().position.y);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        if((turtle.getX()>400-(turtle.speed * MathUtils.cosDeg(turtle.getRotation()))-turtle.getWidth()/2)&&(turtle.getX()<800-(turtle.speed * MathUtils.cosDeg(turtle.getRotation()))-turtle.getWidth()/2)) {
            stage.getCamera().position.x = turtle.getX()+ turtle.getWidth()/2;
        }
        if((turtle.getY()>300- (turtle.speed*MathUtils.sinDeg(turtle.getRotation()))-turtle.getHeight()/2)&&(turtle.getY()<500-(turtle.speed*MathUtils.sinDeg(turtle.getRotation()))-turtle.getHeight()/2)) {
            stage.getCamera().position.y = turtle.getY()+turtle.getHeight()/2;
        }


        if(stage.getCamera().position.x<400){
            stage.getCamera().position.x=400;
        }
        if(stage.getCamera().position.x>800){
            stage.getCamera().position.x=800;
        }
        if(stage.getCamera().position.y<300){
            stage.getCamera().position.y=300;
        }
        if(stage.getCamera().position.y>500){
            stage.getCamera().position.y=500;
        }



        game.batch.begin();
        game.font.draw(game.batch, "starfish:" + String.valueOf(starfish), 0, Gdx.graphics.getHeight());

        if (starfish < 1) {
            game.batch.draw(win, Gdx.graphics.getWidth() / 2 - win.getWidth() / 2, Gdx.graphics.getHeight() / 2 - win.getHeight() / 2);
            game.batch.draw(pressc, Gdx.graphics.getWidth() / 2 - pressc.getWidth() / 2, Gdx.graphics.getHeight() / 2 - pressc.getHeight() / 2 - gameover.getHeight() - 10);
            kt = true;
        }
        if (Intersector.overlapConvexPolygons(turtle.getPolygon(), tank.getPolygon())) {
            game.batch.draw(gameover, Gdx.graphics.getWidth() / 2 - gameover.getWidth() / 2, Gdx.graphics.getHeight() / 2 - gameover.getHeight() / 2 + 10);
            game.batch.draw(pressc, Gdx.graphics.getWidth() / 2 - pressc.getWidth() / 2, Gdx.graphics.getHeight() / 2 - pressc.getHeight() / 2 - gameover.getHeight() - 10);
            kt = true;
            stage.clear();
            nenMusic.stop();
        }
        if (Intersector.overlapConvexPolygons(turtle.getPolygon(), tank2.getPolygon())) {
            game.batch.draw(gameover, Gdx.graphics.getWidth() / 2 - gameover.getWidth() / 2, Gdx.graphics.getHeight() / 2 - gameover.getHeight() / 2 + 10);
            game.batch.draw(pressc, Gdx.graphics.getWidth() / 2 - pressc.getWidth() / 2, Gdx.graphics.getHeight() / 2 - pressc.getHeight() / 2 - gameover.getHeight() - 10);
            kt = true;
            stage.clear();
            nenMusic.stop();
        }
        if (Intersector.overlapConvexPolygons(turtle.getPolygon(), tank3.getPolygon())) {
            game.batch.draw(gameover, Gdx.graphics.getWidth() / 2 - gameover.getWidth() / 2, Gdx.graphics.getHeight() / 2 - gameover.getHeight() / 2 + 10);
            game.batch.draw(pressc, Gdx.graphics.getWidth() / 2 - pressc.getWidth() / 2, Gdx.graphics.getHeight() / 2 - pressc.getHeight() / 2 - gameover.getHeight() - 10);
            kt = true;
            stage.clear();
            nenMusic.stop();
        }
        game.batch.end();
        if(kt == false){
            if(audio){
                nenMusic.play();
            }
            if (input.isKeyJustPressed(Input.Keys.SPACE)) {
                Laze laser = new Laze(0, 0, stage);
                laser.setPosition(turtle.getX() + turtle.getWidth() / 2 - laser.getWidth() / 2, turtle.getY() + turtle.getHeight() / 2 - laser.getHeight() / 2);
                laser.setRotation(turtle.getRotation());
                lasersound.play();
                lasers.add(laser);
                starfish += 2;
            }

            for (Laze laser : lasers) {
                if (Intersector.overlapConvexPolygons(laser.getPolygon(), rock.getPolygon())) {
                    rock.setPosition(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64));
                    laser.remove();
                }
                if (Intersector.overlapConvexPolygons(laser.getPolygon(), rock2.getPolygon())) {
                    rock2.setPosition(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64));
                    laser.remove();
                }
                if (Intersector.overlapConvexPolygons(laser.getPolygon(), rock3.getPolygon())) {
                    rock3.setPosition(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64));
                    laser.remove();
                }
                if (Intersector.overlapConvexPolygons(laser.getPolygon(), rock4.getPolygon())) {
                    rock4.setPosition(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64));
                    laser.remove();
                }
                if (Intersector.overlapConvexPolygons(laser.getPolygon(), sigh.getPolygon())) {
                    sigh.setPosition(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64));
                    laser.remove();
                }
                if (Intersector.overlapConvexPolygons(laser.getPolygon(), sigh2.getPolygon())) {
                    sigh2.setPosition(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64));
                    laser.remove();
                }

                if (Intersector.overlapConvexPolygons(laser.getPolygon(), tank.getPolygon())) {
                    tank.dan = 1;
                    laser.remove();
                }
                if (Intersector.overlapConvexPolygons(laser.getPolygon(), tank2.getPolygon())) {
                    tank2.dan = 1;
                    laser.remove();
                }
                if (Intersector.overlapConvexPolygons(laser.getPolygon(), tank3.getPolygon())) {
                    tank3.dan = 1;
                    laser.remove();
                }
            }

            if (input.isTouched()) {
                System.out.println("x = " + input.getX() + " y = " + (Gdx.graphics.getHeight() - input.getY()));
            }
            System.out.println(stage.getCamera().position.x + " "+stage.getCamera().position.y);


            if (Intersector.overlapConvexPolygons(turtle.getPolygon(), rock.getPolygon())
                || Intersector.overlapConvexPolygons(turtle.getPolygon(), rock2.getPolygon())
                || Intersector.overlapConvexPolygons(turtle.getPolygon(), rock3.getPolygon())
                || Intersector.overlapConvexPolygons(turtle.getPolygon(), rock4.getPolygon())
                || Intersector.overlapConvexPolygons(turtle.getPolygon(), sigh.getPolygon())
                || Intersector.overlapConvexPolygons(turtle.getPolygon(), sigh2.getPolygon())) {
                turtle.moveBy(-3 * MathUtils.cosDeg(turtle.getRotation()), -3 * MathUtils.sinDeg(turtle.getRotation()));
                if ((45 < turtle.getRotation() && turtle.getRotation() < 90) || (-90 < turtle.getRotation() && turtle.getRotation() < -45)) {
                    turtle.moveBy(0, -1);
                } else if ((0 < turtle.getRotation() && turtle.getRotation() < 45) || (90 < turtle.getRotation() && turtle.getRotation() < 135)) {
                    turtle.moveBy(-1, 0);

                } else if ((90 < turtle.getRotation() && turtle.getRotation() < 135) || (225 < turtle.getRotation() && turtle.getRotation() < 270)) {
                    turtle.moveBy(0, 1);
                } else if ((-45 < turtle.getRotation() && turtle.getRotation() < 0) || (180 < turtle.getRotation() && turtle.getRotation() < 225)) {
                    turtle.moveBy(1, 0);
                }
                turtle.koda = false;
                turtle.speed = 0f;
            } else {
                turtle.koda = true;
            }

            if (Intersector.overlapConvexPolygons(turtle.getPolygon(), baoquan.getPolygon())) {
                an = new Whirlpool(new Texture("whirlpool.png"), 5, 2);

                if (starfish < 2){
                    an = new Whirlpool(new Texture("sparkle.png"),8,8);
                }

                an.setPosition(baoquan.getX(), baoquan.getY());
                baoquan.setPosition(ran.nextInt((int) (Gdx.graphics.getWidth() - baoquan.getWidth())), ran.nextInt((int) (Gdx.graphics.getHeight() - baoquan.getHeight())));
                stage.addActor(an);
                dropSound.play();
                starfish--;

            }

            if (Intersector.overlapConvexPolygons(turtle.getPolygon(), haiquan.getPolygon())) {
                an = new Whirlpool(new Texture("whirlpool.png"), 5, 2);

                if (starfish < 2){
                    an = new Whirlpool(new Texture("sparkle.png"),8,8);
                }
                an.setPosition(haiquan.getX(), haiquan.getY());
                haiquan.setPosition(ran.nextInt((int) (Gdx.graphics.getWidth() - haiquan.getWidth())), ran.nextInt((int) (Gdx.graphics.getHeight() - haiquan.getHeight())));
                stage.addActor(an);
                dropSound.play();
                starfish--;

            }

            if (Intersector.overlapConvexPolygons(turtle.getPolygon(), bao.getPolygon())) {
                an = new Whirlpool(new Texture("whirlpool.png"), 5, 2);

                if (starfish < 2){
                    an = new Whirlpool(new Texture("sparkle.png"),8,8);
                }
                an.setPosition(bao.getX(), bao.getY());
                bao.setPosition(ran.nextInt((int) (Gdx.graphics.getWidth() - bao.getWidth())), ran.nextInt((int) (Gdx.graphics.getHeight() - bao.getHeight())));
                stage.addActor(an);
                dropSound.play();
                starfish--;


            }


            if (Intersector.overlapConvexPolygons(turtle.getPolygon(), nawn.getPolygon())) {
                an = new Whirlpool(new Texture("whirlpool.png"), 5, 2);

                if (starfish < 2){
                    an = new Whirlpool(new Texture("sparkle.png"),8,8);
                }
                an.setPosition(nawn.getX(), nawn.getY());
                nawn.setPosition(ran.nextInt((int) (Gdx.graphics.getWidth() - nawn.getWidth())), ran.nextInt((int) (Gdx.graphics.getHeight() - nawn.getHeight())));
                stage.addActor(an);
                dropSound.play();
                starfish--;
            }



            if (Intersector.overlapConvexPolygons(turtle.getPolygon(), sao.getPolygon())) {
                an = new Whirlpool(new Texture("whirlpool.png"), 5, 2);

                if (starfish < 2){
                    an = new Whirlpool(new Texture("sparkle.png"),8,8);
                }
                an.setPosition(sao.getX(), sao.getY());
                sao.setPosition(ran.nextInt((int) (Gdx.graphics.getWidth() - sao.getWidth())), ran.nextInt((int) (Gdx.graphics.getHeight() - sao.getHeight())));
                stage.addActor(an);
                dropSound.play();
                starfish--;

            }



            stage.draw();
            stage.act();
            tank.duoi(turtle.getX(), turtle.getY());
            tank2.duoi(turtle.getX(), turtle.getY());
            tank3.duoi(turtle.getX(), turtle.getY());




            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//            shapeRenderer.polygon(turtle.getPolygon().getTransformedVertices());
//            shapeRenderer.polygon(tank.getPolygon().getTransformedVertices());
            shapeRenderer.end();
        }
        if(input.isKeyJustPressed(Input.Keys.C) && kt){
            game.setScreen(new MenuScreen(game));// goi man hinh moi
        }
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.polygon(turtle.getPolygon().getTransformedVertices());
//        shapeRenderer.polygon(tank.getPolygon().getTransformedVertices());
        shapeRenderer.end();

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
