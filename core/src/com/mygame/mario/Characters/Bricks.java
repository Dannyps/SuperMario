package com.mygame.mario.Characters;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.mario.MainClass;

public class Bricks extends InteractiveObjects{
    public Bricks(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);

        BodyDef bodyd = new BodyDef();
        FixtureDef fixtured = new FixtureDef();
        PolygonShape shape = new PolygonShape();

            bodyd.type = BodyDef.BodyType.StaticBody;
            bodyd.position.set((bounds.getX() + bounds.getWidth() / 2) / MainClass.PPM, (bounds.getY() + bounds.getHeight() / 2) / MainClass.PPM);

            body = world.createBody(bodyd);

            shape.setAsBox(bounds.getWidth() / 2 / MainClass.PPM, bounds.getHeight() / 2 / MainClass.PPM);
            fixtured.shape = shape;
            body.createFixture(fixtured);
    }
}
