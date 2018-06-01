package com.mygame.mario.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.mygame.mario.Levels.BaseScreen;
import com.mygame.mario.MainClass;

import java.awt.geom.RectangularShape;

public class Plant extends Enemy {

    private final TextureRegion plantRegion;
    private Animation plantAnimate;

    public Plant(BaseScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        plantRegion = new TextureRegion(getTexRegion(), 10, 0, 16, 33);
        setBounds(0, 0, 16 / MainClass.PPM, 16 / MainClass.PPM);
        setRegion(plantRegion);
        loadTextures();
    }

    @Override
    protected void loadTextures() {
        for(int i=0 ; i<2; i++)
        {
            frames.add(new TextureRegion(getTexRegion(), i*16, 0, 16, 33));
        }
        plantAnimate = new Animation(0.2f, frames);
        frames.clear();

    }

    @Override
    protected void defineEnemy() {
        BodyDef bodyd = new BodyDef();
        bodyd.position.set(getX()/ MainClass.PPM,getY()/ MainClass.PPM);
        bodyd.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(bodyd);

        FixtureDef fixtured = new FixtureDef();
        fixtured.filter.categoryBits = MainClass.ENEMY_BIT;
        fixtured.filter.maskBits = MainClass.DEFAULT_BIT | MainClass.COIN_BIT | MainClass.BRICK_BIT | MainClass.OBJECT_BIT | MainClass.MARO_BIT;
        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-4.5f / MainClass.PPM, 9 / MainClass.PPM);
        vertice[1] = new Vector2(4.5f / MainClass.PPM, 9 / MainClass.PPM);
        vertice[2] = new Vector2(-5 / MainClass.PPM, 0 / MainClass.PPM);
        vertice[3] = new Vector2(5/ MainClass.PPM, 0 / MainClass.PPM);
        head.set(vertice);
        fixtured.shape = head;
        fixtured.restitution = 0.5f;
        body.createFixture(fixtured);


    }

    @Override
    protected TextureRegion getTexRegion() {
        return screen.getTexAtlas().findRegion("plant");
    }

    @Override
    public void update(float fl) {
        runTime += fl;
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame());
    }

    @Override
    protected TextureRegion getFrame() {
        TextureRegion region;
        region = (TextureRegion) plantAnimate.getKeyFrame(runTime, true);
        return region;
    }

    @Override
    public void hitOnHead() {

    }
}
