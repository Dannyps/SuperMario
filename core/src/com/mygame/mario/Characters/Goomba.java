package com.mygame.mario.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygame.mario.Levels.BaseScreen;
import com.mygame.mario.MainClass;

public class Goomba extends Sprite {

    /*
    private float timeGoomba;
    private Array<TextureRegion> frames;
    private Animation <TextureRegion> goombaRun;

    public Goomba(BaseScreen screen, float x, float y) {
        super(screen, x, y);

        screen.getTexAtlas().findRegion("goomba_edited");
        frames = new Array<TextureRegion> ();
        loadTextures();
        timeGoomba = 0;

        setBounds(getX()/MainClass.PPM, getY()/MainClass.PPM, 16 / MainClass.PPM, 16 / MainClass.PPM);
    }

    public TextureRegion getTextureRegion() {
        return screen.getTexAtlas().findRegion("goomba_edited");
    }

    public void loadTextures() {

        for(int i = 1; i < 2; i++)
        {
            //region loaded
            frames.add(new TextureRegion(getTextureRegion(), i*16, 0, 16, 16));
        }
        goombaRun = new Animation(0.1f, frames);
        frames.clear();
    }



    @Override
    protected void defineEnemy() {
        BodyDef bodyd = new BodyDef();
        //goomba ta a frente do mario
        bodyd.position.set(getX()/ MainClass.PPM, getY()/ MainClass.PPM);
        bodyd.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyd);

        FixtureDef fixtured = new FixtureDef();
        CircleShape cshape = new CircleShape();
        cshape.setRadius(6 / MainClass.PPM);

        fixtured.filter.categoryBits = MainClass.ENEMY_BIT;
        fixtured.filter.maskBits = MainClass.DEFAULT_BIT | MainClass.COIN_BIT | MainClass.BRICK_BIT | MainClass.ENEMY_BIT |MainClass.OBJECT_BIT | MainClass.MARO_BIT;

        fixtured.shape = cshape;
        body.createFixture(fixtured);

        //Head

        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0]=new Vector2(-5,8).scl(1/MainClass.PPM);
        vertice[1]=new Vector2(5,8).scl(1/MainClass.PPM);
        vertice[2]=new Vector2(-3,3).scl(1/MainClass.PPM);
        vertice[3]=new Vector2(3,3).scl(1/MainClass.PPM);
        /* Esta a crashar por razão nenhuma, definiria a cabeca do mario
        fixtured.shape = head;
        fixtured.restitution = 0.5f;
        fixtured.filter.categoryBits = MainClass.ENEMY_HEAD_BIT;
        body.createFixture(fixtured).setUserData("enemy_head");
    }
    public void update(float fl) {
        timeGoomba += fl;
        //sprite a movimentar-se
        setPosition(body.getPosition().x - getRegionWidth() / 2, body.getPosition().y - getHeight() / 2);
        //the problem is here
        setRegion(goombaRun.getKeyFrame(timeGoomba, true));
    }
    */

    private final BaseScreen screen;
    private final int y;
    private final int x;
    public World world;
    public Body body;


    public enum State {Stomp, Run};
    public State currentState;
    public State lastState;

    private TextureRegion goombaRegion;
    private Animation goombaMove;
    private Animation goombaStomp;
    private Array<TextureRegion> frames;

    private float stateTime;




    public Goomba (BaseScreen screen,int x,int y)
    {
        super(screen.getTexAtlas().findRegion("goomba"));
     
        this.x = x;
        this.y = y;

        this.screen = screen;
        this.world = screen.getWorld();
        currentState = State.Run;
        lastState = State.Run;
        stateTime = 0;

        frames = new Array<TextureRegion> ();
        loadTextures();

        defineMario();

        goombaRegion = new TextureRegion(getTextureRegion(), 0, 0, 16, 16);

        setBounds(0, 0, 16 / MainClass.PPM, 16 / MainClass.PPM);
        setRegion(goombaRegion);
    }

    public TextureRegion getTextureRegion() {
        return screen.getTexAtlas().findRegion("goomba");
    }

    //load das texturas para as diferentes animações
    public void loadTextures()
    {
        for(int i= 1; i<2; i++)
        {
            frames.add(new TextureRegion(getTextureRegion(), i*16, 0, 16, 16));
        }
        goombaMove = new Animation(0.1f, frames);
        frames.clear();

        frames.add(new TextureRegion(getTextureRegion(), 3*16, 0, 16, 16));
        goombaStomp = new Animation(0.1f, frames);
        frames.clear();
    }

    public State getState()
    {
        if(body.getLinearVelocity().x != 0)
            return  State.Run;

        else
            return  State.Stomp;
    }

    public TextureRegion getFrame(float fl)
    {
        currentState = getState();

        TextureRegion region;
        switch (currentState)
        {
            case Run:
                region = (TextureRegion) goombaMove.getKeyFrame(stateTime, true);
                break;
            case Stomp:
                region = (TextureRegion) goombaStomp.getKeyFrame(stateTime);
                break;
            default:
                region = goombaRegion;
                break;

        }


        stateTime = currentState == lastState ? stateTime + fl :0;
        lastState = currentState;
        return region;
    }

    public void update(float fl)
    {
        setPosition(body.getPosition().x -getWidth() / 2, body.getPosition().y - getHeight() / 2);
        if(body.getLinearVelocity().x <= 2)
        body.applyLinearImpulse(new Vector2(0.1f/2, 0), body.getWorldCenter(), true);
        setRegion(getFrame(fl));

    }

    private void defineMario()
    {
        BodyDef bodyd = new BodyDef();
        bodyd.position.set(x/ MainClass.PPM,y/ MainClass.PPM);
        bodyd.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyd);

        FixtureDef fixtured = new FixtureDef();
        CircleShape cshape = new CircleShape();
        cshape.setRadius(6 / MainClass.PPM);

        fixtured.filter.categoryBits = MainClass.ENEMY_BIT;
        fixtured.filter.maskBits = MainClass.DEFAULT_BIT | MainClass.COIN_BIT | MainClass.BRICK_BIT | MainClass.ENEMY_BIT | MainClass.OBJECT_BIT | MainClass.MARO_BIT;

        fixtured.shape = cshape;
        body.createFixture(fixtured);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2( -2 / MainClass.PPM, 6 / MainClass.PPM), new Vector2(2 / MainClass.PPM, 6 / MainClass.PPM));
        fixtured.shape = head;
        fixtured.isSensor = true;
        body.createFixture(fixtured).setUserData("head_enemy");
    }
}
