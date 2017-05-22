package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.FlappyBirdRemake;
import com.mygdx.game.HUD;
import com.mygdx.game.TbMenu;
import com.mygdx.game.TbText;

import java.util.Iterator;

/**
 * Created by Matthew on 5/22/2017.
 */

public class ImpossState implements Screen, InputProcessor {
    FlappyBirdRemake flappyBirdRemake;

    private TextureAtlas textureAtlas; //This is for the animation of the character.
    private Animation animation;
    private float fElapsedTime = 0f;

    SpriteBatch sbBatch;
    Stage stage;

    TbMenu tbMenu;
    TextButton TbBack;

    Texture txTopTube, txBottTube; //Tube Stuff.
    Sprite sprTopTube, sprBottTube;
    OrthographicCamera camera;
    Array<Sprite> arsprTopTube, arsprBottTube;
    long lMoveTime, lMoveTime2;
    int nSpawnTime;
    float fTopTubeY;

    public ImpossState(FlappyBirdRemake flappyBirdRemake){ //References the main class.
        this.flappyBirdRemake=flappyBirdRemake;
        flappyBirdRemake.hud = new HUD(flappyBirdRemake.sbBatch);
    }

    public void create(){
        sbBatch = new SpriteBatch();
        stage = new Stage();
        tbMenu= new TbMenu();
        Skin skin = tbMenu.getSkin();//calls skin
        TbBack = new TbText("Back", skin);
        TbBack.setPosition(0, 0);
        textureAtlas = new TextureAtlas(Gdx.files.internal("Bird.pack"));
        animation = new Animation(1f/6f, textureAtlas.getRegions());

        txTopTube = new Texture(Gdx.files.internal("TopTube.png")); //Tube Stuff.
        txBottTube = new Texture(Gdx.files.internal("BottomTube.png"));
        nSpawnTime = 100;
        sprTopTube = new Sprite(txTopTube);
        sprBottTube = new Sprite(txBottTube);
        //Creating sprite and camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        arsprTopTube = new Array<Sprite>();
        arsprBottTube = new Array<Sprite>();
        spawnTopTube();
        spawnBottTube();

        stage.addActor(TbBack);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {
        create();

    }

    private void spawnTopTube() {
        Sprite sprTopTube = new Sprite(txTopTube);
        sprTopTube.setX(750);
        sprTopTube.setY(MathUtils.random(400 - 200) + 250);
        arsprTopTube.add(sprTopTube);
        fTopTubeY = sprTopTube.getY() - 150;
        lMoveTime = TimeUtils.nanoTime();
    }

    private void spawnBottTube() {
        Sprite sprBottTube = new Sprite(txBottTube);
        sprBottTube.setX(750);
        sprBottTube.setY(fTopTubeY - 300);
        arsprBottTube.add(sprBottTube);
        lMoveTime2 = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1); //White background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        flappyBirdRemake.hud.stage.draw(); //Draw the hud.
        fElapsedTime += Gdx.graphics.getDeltaTime();
        sbBatch.setProjectionMatrix(camera.combined);
        sbBatch.begin();
        for (Sprite sprTopTube : arsprTopTube) {
            sbBatch.draw(sprTopTube, sprTopTube.getX(), sprTopTube.getY());
        }
        for (Sprite sprBottTube : arsprBottTube) {
            sbBatch.draw(sprBottTube, sprBottTube.getX(), sprBottTube.getY());
        }
        sbBatch.end();
        if (TimeUtils.nanoTime() - lMoveTime > 100000000 * nSpawnTime) spawnTopTube();
        Iterator<Sprite> iter = arsprTopTube.iterator();
        while (iter.hasNext()) {
            Sprite sprTopTube = iter.next();
            sprTopTube.setX(sprTopTube.getX() - (200) * Gdx.graphics.getDeltaTime());

        }
        if (TimeUtils.nanoTime() - lMoveTime2 > 100000000 * nSpawnTime) spawnBottTube();
        Iterator<Sprite> iters = arsprBottTube.iterator();
        while (iters.hasNext()) {
            Sprite sprBottTube = iters.next();
            sprBottTube.setX(sprBottTube.getX() - (200) * Gdx.graphics.getDeltaTime());
        }

        sbBatch.begin();
        sbBatch.draw(animation.getKeyFrame(fElapsedTime,true),150,180);
        sbBatch.end();
        flappyBirdRemake.hud.update();
        if(TbBack.isPressed()){
            flappyBirdRemake.currentState = FlappyBirdRemake.GameState.PLAY;
            flappyBirdRemake.updateState();
            flappyBirdRemake.hud.reset();

        }
    }

    @Override
    public void resize(int width, int height) {

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
        sbBatch.dispose();
        textureAtlas.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
