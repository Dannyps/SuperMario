package com.mygame.mario.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.mygame.mario.Logic.BaseScreen;
import com.mygame.mario.MainClass;

public class Goomba extends Enemy {

    private boolean goombaRight;
    private boolean killGoomba;
    private boolean killedGoomba;

    private TextureRegion goombaRegion;
    private Animation goombaMove;


    public Goomba(BaseScreen screen, float x, float y) {
        super(screen, x, y);
        goombaRight = true;
        frames = new Array<TextureRegion> ();
        goombaRegion = new TextureRegion(getTexRegion(), 0, 0, 16, 16);
        setBounds(0, 0, 16 / MainClass.PPM, 16 / MainClass.PPM);
        setRegion(goombaRegion);
        loadTextures();
    }

    @Override
    protected void loadTextures() {
        for(int i=0 ; i<2; i++)
        {
            frames.add(new TextureRegion(getTexRegion(), i*16, 0, 16, 16));
        }
        goombaMove = new Animation(0.2f, frames);
        frames.clear();
    }


    @Override
    protected TextureRegion getTexRegion() {
        return screen.getTexAtlas().findRegion("goomba");
    }

    @Override
    protected TextureRegion getFrame() {
        TextureRegion region;
        region = (TextureRegion) goombaMove.getKeyFrame(runTime, true);
        return region;
    }

    @Override
    public void hitOnHead() {
        killGoomba = true;
    }

    @Override
    protected void defineEnemy() {
        BodyDef bodyd = new BodyDef();
        bodyd.position.set(getX()/ MainClass.PPM,getY()/ MainClass.PPM);
        bodyd.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyd);

        FixtureDef fixtured = new FixtureDef();
        CircleShape cshape = new CircleShape();
        cshape.setRadius(6 / MainClass.PPM);
        fixtured.filter.categoryBits = MainClass.ENEMY_BIT;
        fixtured.filter.maskBits = MainClass.DEFAULT_BIT | MainClass.COIN_BIT | MainClass.BRICK_BIT | MainClass.OBJECT_BIT | MainClass.MARO_BIT;

        fixtured.shape = cshape;
        body.createFixture(fixtured);

        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-4.5f / MainClass.PPM, 9 / MainClass.PPM);
        vertice[1] = new Vector2(4.5f / MainClass.PPM, 9 / MainClass.PPM);
        vertice[2] = new Vector2(-2.5f / MainClass.PPM, 5 / MainClass.PPM);
        vertice[3] = new Vector2(2.5f / MainClass.PPM, 5 / MainClass.PPM);
        head.set(vertice);
        fixtured.shape = head;
        fixtured.restitution = 0.5f;
        fixtured.filter.categoryBits = MainClass.ENEMY_HEAD_BIT;

        body.createFixture(fixtured);
    }

    @Override
    public void update(float fl) {
        runTime += fl;

        if (killGoomba && !killedGoomba) {
            setRegion(new TextureRegion(screen.getTexAtlas().findRegion("goomba"), 32, 0, 16, 16));
            world.destroyBody(body);
            killedGoomba = true;
        } else if (!killedGoomba) {
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
            setRegion(getFrame());
        }
    }
}
