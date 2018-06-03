package com.mygame.mario.Characters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygame.mario.Logic.BaseScreen;

public abstract class Enemy extends Sprite {

    private PolygonShape polygonShape;
    private Fixture fixtureDef;
    private BodyDef bodyDef;
    protected World world;
    protected BaseScreen screen;
    protected float runTime;
    public Body body;
    protected Array<TextureRegion> frames;
    protected Rectangle bounds;

    public Enemy(BaseScreen screen, float x, float y)
    {
        this.world = screen.getWorld();
        this.screen = screen;
        runTime = 0;
        setPosition(x,y);
        defineEnemy();
        body.setActive(false);
    }

    protected abstract void loadTextures();
    protected abstract void defineEnemy();
    protected abstract TextureRegion getTexRegion();
    public abstract void update (float fl);
    protected abstract TextureRegion getFrame();
    public abstract void hitOnHead();
}
