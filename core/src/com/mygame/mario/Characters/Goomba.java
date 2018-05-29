package com.mygame.mario.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.mygame.mario.Levels.BaseScreen;
import com.mygame.mario.MainClass;

public class Goomba extends Enemys {

    private float timeGoomba;
    private Array<TextureRegion> frames;
    private Animation <TextureRegion> goombaRun;

    public Goomba(BaseScreen screen, float x, float y) {
        super(screen, x, y);
        screen.getTexAtlas().findRegion("goomba_edited");

        frames = new Array<TextureRegion> ();
        loadTextures();
        timeGoomba = 0;

        setBounds(getX(), getY(), 16 / MainClass.PPM, 16 / MainClass.PPM);
    }


    public void loadTextures() {

        for(int i = 1; i < 3; i++)
        {
            //region loaded
            frames.add(new TextureRegion(screen.getTexAtlas().findRegion("goomba_edited"), i*16, 0, 16, 16));
        }
        goombaRun = new Animation(0.4f, frames);
        //frames.clear();
    }

    @Override
    protected void defineEnenmy() {
        BodyDef bodyd = new BodyDef();
        //goomba ta a frente do mario
        bodyd.position.set(50/ MainClass.PPM, 50/ MainClass.PPM);
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
        body.createFixture(fixtured).setUserData("head");*/
    }

    public void update(float fl) {
        timeGoomba += fl;
        //sprite a movimentar-se
        setPosition(body.getPosition().x - getRegionWidth() / 2, body.getPosition().y - getHeight() / 2);
        //the problem is here
        setRegion(goombaRun.getKeyFrame(timeGoomba, true));
    }
}
