package com.mygdx.game;

/**
 * Created by Marla Scrub on 4/21/2017.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class GameScreen implements Screen {

    //FIELDS
    private MyGdxGame game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private final int LEVEL_WIDTH;
    private final int LEVEL_HEIGHT;

    //Initialized Objects: Order of spawning in
    Player player;
    Laser laser;

    //CONSTRUCTOR
    public GameScreen(MyGdxGame game) {
        this.game = game;

        Laser[] lasers = new Laser[10];

        LEVEL_WIDTH = MyGdxGame.V_WIDTH;
        LEVEL_HEIGHT = MyGdxGame.V_HEIGHT;
        gameCam = new OrthographicCamera();
        gamePort = new ExtendViewport(LEVEL_WIDTH, LEVEL_HEIGHT, gameCam);


        //All following: Makes one or multiple new objects
        player = new Player(game.batch);
        laser = new Laser(game.batch);
    }

    //METHODS
    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        update(delta);

        //Clears Screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Allows images to be transparent
        game.batch.enableBlending();

        game.batch.begin();
        game.batch.draw(new Texture("bg.jpg"), 0, 0);
        player.render();
        laser.render();

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {}


    //Methods

    public void update(float delta) {
        player.update(delta);
        laser.update(delta);
    }
}

