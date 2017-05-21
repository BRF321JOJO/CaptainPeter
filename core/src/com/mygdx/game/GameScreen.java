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

    private MyGdxGame game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private final int LEVEL_WIDTH;
    private final int LEVEL_HEIGHT;

    //Initialized Objects: Order of spawning in
    Player player;
    Laser laser;
    Shield[] shield;
    Invaders[] invaders;

    //Normal variables


    //CONSTRUCTOR
    public GameScreen(MyGdxGame game) {
        this.game = game;

        LEVEL_WIDTH = MyGdxGame.V_WIDTH;
        LEVEL_HEIGHT = MyGdxGame.V_HEIGHT;
        gameCam = new OrthographicCamera();
        gamePort = new ExtendViewport(LEVEL_WIDTH, LEVEL_HEIGHT, gameCam);

        //All following: Makes one or multiple new objects
        player = new Player(game.batch);
        laser = new Laser(game.batch);

        shield = new Shield[Shield.numberofshields];
        for (int i = 0; i <= (Shield.numberofshields - 1); i++) {
            //Values based off screen width and number of shields
            //Must account for width of shield
            shield[i] = new Shield(game.batch , (i * MyGdxGame.V_WIDTH / (Shield.numberofshields + 1)
                    //Calculates center of screen
                    + MyGdxGame.V_WIDTH / (Shield.numberofshields + 1) - (Shield.shieldwidth/2)));

            //Adds shield to entities
            Entity.entities.add(shield[i]);
        }

        invaders = new Invaders[Invaders.numberofinvaders];
        for (int i = 0; i<=(Invaders.numberofinvaders - 1); i++) {
            invaders[i] = new Invaders(game.batch, (i * 75)
                    + MyGdxGame.V_WIDTH / (Invaders.numberofinvaders + 1) - (Invaders.invaderswidth/2));
            //Adds invaders to entities
            Entity.entities.add(invaders[i]);
        }
    }

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
        player.render();
        laser.render();
        for (int i = 0; i <= (Shield.numberofshields - 1); i++) {
            shield[i].render();
        }
        for (int i = 0; i<=(Invaders.numberofinvaders - 1); i++) {
            invaders[i].render();
        }
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
        //Update methods
        player.update(delta);
        laser.update(delta);
        for (int i = 0; i <= (Shield.numberofshields - 1); i++) {
            shield[i].update(delta);
        }
        for (int i = 0; i<=(Invaders.numberofinvaders - 1); i++) {
            invaders[i].update(delta);
        }

        //Methods of Game Screen


        //Shoots laser based from player posx (only if off screen)
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !Laser.InBound) {
            laser.posx = player.posx + player.width/2;
        }



        //Checks for collision. Comes from Entity class. Collision happens after everything updates.
        //Note: Only checks collision for things defined, not all entities
        //Generally tests an entity collides with anything on the list of entities
        for (Entity e : Entity.entities) {

            //Checks collision for laser specifically
            if (laser.isCollide(e)) {
                //Says all handling denoted within respective class
                laser.handleCollision(e);
                e.handleCollision(laser);
            }

        }
        //End of update method
    }
}

