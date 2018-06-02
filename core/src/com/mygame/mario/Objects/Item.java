package com.mygame.mario.Objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.mario.Logic.BaseScreen;
import com.mygame.mario.MainClass;

public abstract class Item extends Sprite {
    protected BaseScreen screen;
    protected World world;
    protected Vector2 velocity;
    protected boolean toDestroy;
    protected boolean destroyed;
    protected Body body;

    public Item(BaseScreen screen, float x, float y)
    {
        this.screen = screen;
        this.world = screen.getWorld();
        setPosition(x,y);
        setBounds(getX(),getY(),16/ MainClass.PPM,16/MainClass.PPM);
        defineItem();
        toDestroy = false;
        destroyed = false;
    }

    public abstract void defineItem();
    public abstract void use();

    public void update(float fl){
        if(toDestroy && !destroyed){
            world.destroyBody(body);
            destroyed = true;
        }
    }
    public void draw(Batch batch){
        if(!destroyed){
            super.draw(batch);
        }
    }

    public void destroy(){
        toDestroy = true;
    }

}
