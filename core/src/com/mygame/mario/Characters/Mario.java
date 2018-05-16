package com.mygame.mario.Characters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygame.mario.MainClass;

public class Mario extends Sprite {

    public World world;
    public Body body;

    public Mario (World world)
    {
        this.world = world;
        defineMario();
    }

    private void defineMario()
    {
        BodyDef bodyd = new BodyDef();
        bodyd.position.set(10/ MainClass.PPM, 10/ MainClass.PPM);
        //bodyd.position.set(33 / MainClass.PPM, 33 / MainClass.PPM);
        bodyd.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyd);

        FixtureDef fixtured = new FixtureDef();
        CircleShape cshape = new CircleShape();
        cshape.setRadius(5 / MainClass.PPM);

        fixtured.shape = cshape;
        body.createFixture(fixtured);
    }
}