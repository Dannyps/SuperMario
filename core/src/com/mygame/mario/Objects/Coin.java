package com.mygame.mario.Objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.mygame.mario.Logic.BaseScreen;
import com.mygame.mario.MainClass;
import com.mygame.mario.Scenes.Hud;

public class Coin extends Item {
    private Fixture fixture;
    private TextureRegion coinRegion;
    private Array<TextureRegion> frames;
    private Animation coinAnimate;
    private float runTime;

    public Coin(BaseScreen screen, float x, float y) {
        super(screen, x, y);
        runTime = 0;
        frames = new Array<TextureRegion>();
        coinRegion = new TextureRegion(getTexRegion(), 0, 0, 20, 20);
        setBounds(0, 0, 20 / MainClass.PPM, 20 / MainClass.PPM);
        setRegion(coinRegion);
        loadTextures();
        fixture.setUserData(this);

    }
    public TextureRegion getTexRegion() {
        return screen.getTexAtlas().findRegion("coin");
    }

    public void loadTextures() {
        for(int i = 0; i<5; i++)
        {
            frames.add(new TextureRegion(getTexRegion(), i*20, 0, 20, 20));
        }
        coinAnimate = new Animation(0.1f, frames);
        frames.clear();

    }

    protected TextureRegion getFrame() {
        TextureRegion region;
        region = (TextureRegion) coinAnimate.getKeyFrame(runTime, true);
        return region;
    }

    @Override
    public void defineItem() {
        BodyDef bodyd = new BodyDef();
        bodyd.position.set(10/MainClass.PPM + getX()/ MainClass.PPM,10/MainClass.PPM+ getY()/ MainClass.PPM);
        bodyd.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyd);
        FixtureDef fixtured = new FixtureDef();
        fixtured.filter.categoryBits = MainClass.COIN_BIT;
        fixtured.filter.maskBits = MainClass.DEFAULT_BIT | MainClass.COIN_BIT | MainClass.BRICK_BIT | MainClass.OBJECT_BIT | MainClass.MARO_BIT;
        PolygonShape coin = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(6f / MainClass.PPM, 6f  / MainClass.PPM);
        vertice[1] = new Vector2(-6f / MainClass.PPM, 6f  / MainClass.PPM);
        vertice[2] = new Vector2(-6f / MainClass.PPM, -6f  / MainClass.PPM);
        vertice[3] = new Vector2(6f/ MainClass.PPM, -6f  / MainClass.PPM);
        coin.set(vertice);
        fixtured.shape = coin;
        fixture = body.createFixture(fixtured);

    }

    @Override
    public void use() {
        destroy();
        Hud.updateScore(30);
    }

    @Override
    public void update(float fl) {
        runTime += fl;
        super.update(fl);
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame());
    }
}
