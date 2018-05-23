package com.mygame.mario.Characters;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.math.Rectangle;
import com.mygame.mario.MainClass;

public abstract class InteractiveObjects {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    protected BodyDef bodyDef;
    protected FixtureDef fixtureDef;
    protected PolygonShape polygonShape;

    public InteractiveObjects(World world, TiledMap map, Rectangle bounds) {
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        polygonShape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / MainClass.PPM, (bounds.getY() + bounds.getHeight() / 2) / MainClass.PPM);

        body = world.createBody(bodyDef);

        polygonShape.setAsBox(bounds.getWidth() / 2 / MainClass.PPM, bounds.getHeight() / 2 / MainClass.PPM);
        fixtureDef.shape = polygonShape;
        fixture = body.createFixture(fixtureDef);

    }

    public abstract void HeadColission ();
}
