package com.mygame.mario.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygame.mario.Levels.BaseScreen;
import com.mygame.mario.MainClass;

public class Mario extends Sprite {

    public World world;
    public Body body;

    public enum State {Fall, Jump, Stand, Run};
    public State currentState;
    public State lastState;

    private TextureRegion marioRegion;
    private Animation marioRun;
    private Animation marioJump;
    private Array<TextureRegion> frames;

    private float stateTime;
    private boolean runRight;
    private boolean marioIsBig;

    public Mario (World world, BaseScreen screen)
    {
        super(screen.getTexAtlas().findRegion("Sprites_ready/little_mario"));
        this.world = world;

        currentState = State.Stand;
        lastState = State.Stand;
        stateTime = 0;
        runRight = true;

        frames = new Array<TextureRegion> ();
        loadTextures(frames);

        defineMario();

        marioRegion = new TextureRegion(getTexture(), 0, 10, 16, 16);
        setBounds(0, 0, 16 / MainClass.PPM, 16 / MainClass.PPM);
        setRegion(marioRegion);

        marioIsBig = true;
    }

    //load das texturas para as diferentes animações
    public void loadTextures(Array<TextureRegion> frames)
    {
        for(int i= 1; i<4; i++)
        {
            frames.add(new TextureRegion(getTexture(), i*16, 10, 16, 16));
        }
        marioRun = new Animation(0.1f, frames);
        frames.clear();

        for(int i= 4; i<6; i++)
        {
            frames.add(new TextureRegion(getTexture(), i*16, 10, 16, 16));
        }
        marioJump = new Animation(0.1f, frames);
        frames.clear();
    }

    public State getState()
    {
        if(body.getLinearVelocity().y > 0)
            return  State.Jump;

        else if(body.getLinearVelocity().y < 0)
            return  State.Fall;

        else if(body.getLinearVelocity().x != 0)
            return  State.Run;

        else
            return  State.Stand;
    }

    public TextureRegion getFrame(float fl)
    {
        currentState = getState();

        TextureRegion region;
        switch (currentState)
        {
            case Jump:
                region = (TextureRegion) marioJump.getKeyFrame(stateTime);
                break;
            case Run:
                region = (TextureRegion) marioRun.getKeyFrame(stateTime, true);
                break;
            case Fall:
            case Stand:
            default:
                region = marioRegion;
                break;

        }

        if((body.getLinearVelocity().x < 0 || !runRight) && !region.isFlipX())
        {
            region.flip(true, false);
            runRight = false;
        }

        else if((body.getLinearVelocity().x > 0 || runRight) && region.isFlipX())
        {
            region.flip(true, false);
            runRight = true;
        }

        stateTime = currentState == lastState ? stateTime + fl :0;
        lastState = currentState;
        return region;
    }

    public void update(float fl)
    {
        setPosition(body.getPosition().x -getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(fl));
    }
    public boolean marioBig(){
        return marioIsBig;
    }
/*
    public void grow(){
        if(!marioBig()){
            marioIsBig = true;
        }
    }*/
    private void defineMario()
    {
        BodyDef bodyd = new BodyDef();
        bodyd.position.set(10/ MainClass.PPM, 10/ MainClass.PPM);
        //bodyd.position.set(33 / MainClass.PPM, 33 / MainClass.PPM);
        bodyd.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyd);

        FixtureDef fixtured = new FixtureDef();
        CircleShape cshape = new CircleShape();
        cshape.setRadius(6 / MainClass.PPM);

        fixtured.filter.categoryBits = MainClass.MARO_BIT;
        fixtured.filter.maskBits = MainClass.DEFAULT_BIT | MainClass.COIN_BIT | MainClass.BRICK_BIT;

        fixtured.shape = cshape;
        body.createFixture(fixtured);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / MainClass.PPM, 6 / MainClass.PPM), new Vector2(2 / MainClass.PPM, 6 / MainClass.PPM));
        fixtured.shape = head;
        fixtured.isSensor = true;

        body.createFixture(fixtured).setUserData("head");
    }
}