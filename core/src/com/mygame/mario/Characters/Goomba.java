package com.mygame.mario.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.mygame.mario.Levels.BaseScreen;
import com.mygame.mario.MainClass;

public class Goomba extends Sprite {

    public World world;
    public Body body;

    private TextureRegion goombaRegion;
    private Array<TextureRegion> frames;
    private Animation goombaRun;

    public Goomba(World world, BaseScreen screen) {
        super(screen.getTexAtlas().findRegion("Sprites_ready/goomba_edited"));
        this.world = world;

        frames = new Array<TextureRegion> ();
        loadTextures(frames);

        defineGoomba();

        goombaRegion = new TextureRegion(getTexture(), 0, 10, 16, 16);
        setBounds(0, 0, 16 / MainClass.PPM, 16 / MainClass.PPM);
        setRegion(goombaRegion);
    }

    private void defineGoomba()
    {
        BodyDef bodyd = new BodyDef();
        bodyd.position.set(12/ MainClass.PPM, 12/ MainClass.PPM);
        bodyd.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyd);

        FixtureDef fixtured = new FixtureDef();
        CircleShape cshape = new CircleShape();
        cshape.setRadius(6 / MainClass.PPM);

        fixtured.shape = cshape;
        body.createFixture(fixtured);
    }

    public void loadTextures(Array<TextureRegion> frames) {

        for(int i= 0; i<2; i++)
        {
            frames.add(new TextureRegion(getTexture(), i*16, 10, 16, 16));
        }
        goombaRun = new Animation(0.1f, frames);
        frames.clear();
    }
}
