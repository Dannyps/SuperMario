package com.mygame.mario.Characters;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygame.mario.Levels.BaseScreen;
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
        killGoomba = false;
        killedGoomba = false;
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
    public void hitOnHead(Mario Mario) {
        killGoomba = true;
        MainClass.manager.get("Audio/sounds/stomp.wav", Sound.class).play();
    }


    public void draw(Batch batch){
        if(!killedGoomba || runTime < 1)
            super.draw(batch);
        }

    @Override
    protected void defineEnemy() {
        BodyDef bodyd = new BodyDef();
        bodyd.position.set(getX()/ MainClass.PPM,getY()/ MainClass.PPM);
        bodyd.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyd);

        FixtureDef fix = new FixtureDef();
        CircleShape cshape = new CircleShape();
        cshape.setRadius(6 / MainClass.PPM);
        fix.filter.categoryBits = MainClass.ENEMY_BIT;
        fix.filter.maskBits = MainClass.DEFAULT_BIT | MainClass.COIN_BIT |MainClass.ENEMY_BIT| MainClass.BRICK_BIT | MainClass.OBJECT_BIT | MainClass.MARO_BIT;

        fix.shape = cshape;
        body.createFixture(fix);

        PolygonShape head_enemy = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-4.5f / MainClass.PPM, 9 / MainClass.PPM);
        vertice[1] = new Vector2(4.5f / MainClass.PPM, 9 / MainClass.PPM);
        vertice[2] = new Vector2(-2.5f / MainClass.PPM, 5 / MainClass.PPM);
        vertice[3] = new Vector2(2.5f / MainClass.PPM, 5 / MainClass.PPM);
        head_enemy.set(vertice);
        fix.shape = head_enemy;
        fix.restitution = 0.5f;
        fix.filter.categoryBits = MainClass.ENEMY_HEAD_BIT;

        body.createFixture(fix);
    }

    @Override
    public void update(float fl) {
        runTime += fl;

        if (killGoomba && !killedGoomba) {
            world.destroyBody(body);
            setRegion(new TextureRegion(screen.getTexAtlas().findRegion("goomba"), 32, 0, 16, 16));
            killedGoomba = true;
            runTime = 0;
        } else if (!killedGoomba) {
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
            setRegion(getFrame());
        }
    }
}
