package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public abstract class Entity {

    //FIELDS
    public Texture texture;
    public SpriteBatch batch;  //Draw Textures
    public int width;
    public int height;
    public int posx;
    public int posy;
    public int velx;
    public int vely;
    public int ID;             //Unique identity

    //STATIC FIELDS
    public static ArrayList<Entity> entities = new ArrayList<Entity>();

    //CONSTRUCTOR
    //Can make values doubles and correct by changing to int when defining this. to allow use of doubles elsewhere
    //Note: The field values are left as int above
    public Entity(Texture texture, int posx, int posy, int width, double height, int velx, int vely, int ID, SpriteBatch batch){
        this.texture = texture;
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = (int)height;
        this.velx = velx;
        this.vely = vely;
        this.ID = ID;
        this.batch = batch;
    }

    //METHODS
    //This tests for collision
    public boolean isCollide(Entity e){
        if(     posx < e.posx + e.width     &&
                posx + width > e.posx       &&
                posy < e.posy + e.height    &&
                height + posy > e.posy)
        {
            return true;
        } else {
            return false;
        }
    }

    //ABSTRACT METHODS
    //This generally says what to do when rendering and collision is happening
    //Varies depending on the subclass
    public abstract void render();
    public abstract void handleCollision(Entity e);
}
