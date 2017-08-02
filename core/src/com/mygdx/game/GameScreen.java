package com.mygdx.game;

/**
 * Created by Marla Scrub on 4/21/2017.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class GameScreen implements Screen {

    private MyGdxGame game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private final int LEVEL_WIDTH;
    private final int LEVEL_HEIGHT;

    //Background

    //Initialized Objects: Order of spawning in
    Player player;

    Laser laser;
    Laser invaderlaser;
    Laser invaderlaser2;

    Shield[] shield;
    Invaders[] invaders;
    Music music;

    Startscreen startscreen;
    Blackscreen blackscreen;
    Background background;
    Deathscreen deathscreen;

    //Normal variables

    //Makes to same invader doesn't shoot twice
    private int invaderjustshot = 2000;
    public static boolean slideblackbackground = true;
    boolean gameover = false;


    //CONSTRUCTOR
    public GameScreen(MyGdxGame game) {
        this.game = game;

        LEVEL_WIDTH = MyGdxGame.V_WIDTH;
        LEVEL_HEIGHT = MyGdxGame.V_HEIGHT;
        gameCam = new OrthographicCamera();
        gamePort = new ExtendViewport(LEVEL_WIDTH, LEVEL_HEIGHT, gameCam);

        music = new Music();
        if (Debug.musicallow) {
            music.play();
        }

        //Startup screens
        startscreen = new Startscreen(game.batch);
        blackscreen = new Blackscreen(game.batch);
        background = new Background(game.batch);
        deathscreen = new Deathscreen(game.batch);

        //All following: Makes one or multiple new objects
        player = new Player(game.batch);
        laser = new Laser(game.batch);
        invaderlaser = new Laser(game.batch);
        invaderlaser2 = new Laser(game.batch);

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
            invaders[i] = new Invaders(game.batch, (i * Invaders.spacebetween)
                    + MyGdxGame.V_WIDTH / (Invaders.numberofinvaders + 1) - (Invaders.invaderswidth/2));
            //Adds invaders to entities
            Entity.entities.add(invaders[i]);
        }
        Entity.entities.add(player);


        //Invader variables
        invaderlaser.vely = -8;
        invaderlaser2.vely = -8;

        //Defined variables
        invaderlaser.HoldingArea = 3000;
        invaderlaser2.HoldingArea = 4000;
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

        background.render();
        laser.render();
        invaderlaser.render();
        invaderlaser2.render();
        player.render();


        for (int i = 0; i <= (Shield.numberofshields - 1); i++) {
            shield[i].render();
        }
        for (int i = 0; i<=(Invaders.numberofinvaders - 1); i++) {
            invaders[i].render();
        }

        if (!Startscreen.go || gameover) {
            blackscreen.render();
        }

        if(startscreen.showStart) {
            startscreen.render();
        }

        if(gameover){
            deathscreen.render();
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


    //Restarts game
    public void restartgame () {
        startscreen.startGame = false;
        blackscreen.vely = 0;
        blackscreen.posy = 0;

        //Prevents start from rendering after game ends
        startscreen.showStart = false;
        //Prevents slide after game first starts
        slideblackbackground = false;


        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            startscreen.startGame = true;
            gameover = false;

            //Resets positions of entities
            for (int i = 0; i <= (Invaders.numberofinvaders - 1); i++) {
                invaders[i].posx = ((i * Invaders.spacebetween)
                        + MyGdxGame.V_WIDTH / (Invaders.numberofinvaders + 1) - (Invaders.invaderswidth/2));
                invaders[i].posy = 600;
                invaders[i].movingright = true;
            }
            for (int i = 0; i <= (Shield.numberofshields - 1); i++) {
                shield[i].height = (int)Shield.shieldheight;
                shield[i].posx = (i * MyGdxGame.V_WIDTH / (Shield.numberofshields + 1)
                        + MyGdxGame.V_WIDTH / (Shield.numberofshields + 1) - (Shield.shieldwidth/2));
            }

            //Makes lasers disappear to prevent laser from retriggering restart
            for (Entity e : Entity.entities) {
                if (invaderlaser.isCollide(e)) {
                    invaderlaser.handleCollision(e);
                    e.handleCollision(invaderlaser);
                    invaderlaser2.handleCollision(e);
                    e.handleCollision(invaderlaser2);
                }
                if (invaderlaser2.isCollide(e)) {
                    invaderlaser2.handleCollision(e);
                    e.handleCollision(invaderlaser2);
                    invaderlaser.handleCollision(e);
                    e.handleCollision(invaderlaser);
                    invaderlaser2.handleCollision(e);
                }
            }
        }
    }


    //Methods

    public void update(float delta) {
        //Update methods
        background.update(delta);
        startscreen.update(delta);
        blackscreen.update(delta);
        deathscreen.update(delta);

        if(!gameover && startscreen.startGame && startscreen.go) {
            player.update(delta);
            laser.update(delta);
            for (int i = 0; i <= (Shield.numberofshields - 1); i++) {
                shield[i].update(delta);
            }
            for (int i = 0; i <= (Invaders.numberofinvaders - 1); i++) {
                invaders[i].update(delta);
            }

            //Methods of Game Screen
            for (int i = 0; i <= (Invaders.numberofinvaders - 1); i++) {

                //All only applies to invaders which are alive (ID 1)
                if (invaders[i].ID == 1) {

                    //Moves invaders all down together and makes go opposite direction once close enough to sides
                    //Also, corrects for slow drift right and left
                    if (invaders[i].posx < Invaders.edgesofmovement && invaders[i].posx > 0 && invaders[i].posx < MyGdxGame.V_WIDTH) {
                        //Moves all down
                        for (int j = 0; j <= (Invaders.numberofinvaders - 1); j++) {
                            //Only works for those onscreen
                            if (invaders[j].posx > 0 && invaders[j].posx < MyGdxGame.V_WIDTH) {
                                invaders[j].posy -= Invaders.ammountmovedown;
                                //Corrects for changes in spaces between
                                invaders[j].posx = Invaders.edgesofmovement + (j * Invaders.spacebetween);
                            }
                        }
                        Invaders.movingright = true;
                    }
                    //(Corrects for width of invader)
                    if (invaders[i].posx > (MyGdxGame.V_WIDTH - Invaders.edgesofmovement - Invaders.invaderswidth)
                            && invaders[i].posx < MyGdxGame.V_WIDTH && invaders[i].posx > 0) {
                        //Moves all down
                        for (int j = 0; j <= (Invaders.numberofinvaders - 1); j++) {
                            //Only if onscreen
                            if (invaders[j].posx < MyGdxGame.V_WIDTH && invaders[i].posx > 0) {
                                invaders[j].posy -= Invaders.ammountmovedown;
                                //Corrects posx shifts
                                invaders[j].posx = (MyGdxGame.V_WIDTH - Invaders.edgesofmovement - Invaders.invaderswidth)
                                        - (j * Invaders.spacebetween);
                            }
                        }
                        Invaders.movingright = false;
                    }
                }


                //Invaders aiming system (for two lasers)
                //Ony works if Debug
                if (Debug.enemyfire) {

                    if (((invaders[i].posx + invaders[i].width / 2) >= player.posx) && ((invaders[i].posx + invaders[i].width / 2)
                            <= (player.posx + player.width)) && !invaderlaser.xInBound) {
                        if (i != invaderjustshot) {
                            invaderlaser.posx = (invaders[i].posx + invaders[i].width / 2);
                            invaderlaser.posy = invaders[i].posy;
                            invaderlaser.vely = -10;
                            invaderjustshot = i;
                        }
                    }
                    if (((invaders[i].posx + invaders[i].width / 2) >= player.posx) && ((invaders[i].posx + invaders[i].width / 2)
                            <= (player.posx + player.width)) && !invaderlaser2.xInBound) {
                        if (i != invaderjustshot) {
                            invaderlaser2.posx = (invaders[i].posx + invaders[i].width / 2);
                            invaderlaser2.posy = invaders[i].posy;
                            invaderlaser2.vely = -10;
                            invaderjustshot = i;
                        }
                    }
                }
            }
            invaderlaser.update(delta);
            invaderlaser2.update(delta);


            //Shoots laser based from player posx (only if off screen)
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !laser.xInBound) {
                laser.posx = player.posx + (player.width / 2);
            }


            //Checks for collision. Comes from Entity class. Collision happens after everything updates.
            //Note: Only checks collision for things defined, not all entities
            //Generally tests an entity collides with anything on the list of entities
            for (Entity e : Entity.entities) {

                //Checks collision for laser specifically
                if (laser.isCollide(e)) {
                    //Says all handling denoted within respective class
                    if(e.ID!=2) {
                        laser.handleCollision(e);
                        e.handleCollision(laser);
                    }
                }
                if (invaderlaser.isCollide(e)) {
                    //Says all handling denoted within respective class
                    if (e.ID != 1 && e.ID!=2) {
                        invaderlaser.handleCollision(e);
                        e.handleCollision(invaderlaser);
                    }
                    if (e.ID == 2) {
                        gameover = true;
                        System.out.println(gameover);
                    }
                }
                if (invaderlaser2.isCollide(e)) {
                    //Says all handling denoted within respective class
                    if (e.ID != 1 && e.ID!=2) {
                        invaderlaser2.handleCollision(e);
                        e.handleCollision(invaderlaser2);
                    }
                    if (e.ID == 2) {
                        gameover = true;
                        startscreen.startGame=false;
                        System.out.println(gameover);
                    }
                }

            }
        }

        //End of if !gameover statment

        //Runs restart game method
        if(gameover){
            restartgame();
        }

        //End of update method
    }
}

