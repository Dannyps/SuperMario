package com.mygame.mario.Logic;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygame.mario.Characters.Enemy;
import com.mygame.mario.Characters.Goomba;
import com.mygame.mario.Characters.Plant;
import com.mygame.mario.Objects.Bricks;
import com.mygame.mario.Objects.Coin;
import com.mygame.mario.Objects.Item;
import com.mygame.mario.Objects.PipeLevel;
import com.mygame.mario.Objects.Qmark;
import com.mygame.mario.MainClass;

public class LoadMap {

    private BodyDef bodyd;
    private PolygonShape shape;
    private FixtureDef fixtured;
    private Body body;
    private Qmark qmark;
    private Bricks bricks;
    private Coin coin;
    private PipeLevel pipeLevel;
    private World world;
    private TiledMap map;
    private Array<Goomba> goombas;
    private Array<Plant> plants;
    private Array<Coin> coins;

    public LoadMap (BaseScreen screen)
    {
        bodyd = new BodyDef();
        shape = new PolygonShape();
        fixtured = new FixtureDef();
        world = screen.getWorld();
        map = screen.getMap();

        recognizeMap(screen);
    }


    void recognizeMap(BaseScreen screen){

        // recognize empty block
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyd.type = BodyDef.BodyType.StaticBody;
            bodyd.position.set((rectangle.getX() + rectangle.getWidth() / 2) / MainClass.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / MainClass.PPM);

            body = world.createBody(bodyd);

            shape.setAsBox(rectangle.getWidth() / 2 / MainClass.PPM, rectangle.getHeight() / 2 / MainClass.PPM);
            fixtured.shape = shape;
            body.createFixture(fixtured);
        }

        // recognize ground
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyd.type = BodyDef.BodyType.StaticBody;
            bodyd.position.set((rectangle.getX() + rectangle.getWidth() / 2) / MainClass.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / MainClass.PPM);

            body = world.createBody(bodyd);

            shape.setAsBox(rectangle.getWidth() / 2 / MainClass.PPM, rectangle.getHeight() / 2 / MainClass.PPM);
            fixtured.shape = shape;
            body.createFixture(fixtured);
        }

        // recognize bricks
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bricks = new Bricks(screen, rectangle);
        }

        // recognize pipes
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyd.type = BodyDef.BodyType.StaticBody;
            bodyd.position.set((rectangle.getX() + rectangle.getWidth() / 2) / MainClass.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / MainClass.PPM);

            body = world.createBody(bodyd);

            shape.setAsBox(rectangle.getWidth() / 2 / MainClass.PPM, rectangle.getHeight() / 2 / MainClass.PPM);
            fixtured.shape = shape;
            fixtured.filter.categoryBits = MainClass.OBJECT_BIT;
            body.createFixture(fixtured);
        }


        // recognize pipe level
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            pipeLevel = new PipeLevel(screen, rectangle);
            /*bodyd.type = BodyDef.BodyType.StaticBody;
            bodyd.position.set((rectangle.getX() + rectangle.getWidth() / 2) / MainClass.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / MainClass.PPM);

            body = world.createBody(bodyd);

            shape.setAsBox(rectangle.getWidth() / 2 / MainClass.PPM, rectangle.getHeight() / 2 / MainClass.PPM);
            fixtured.shape = shape;
            body.createFixture(fixtured);*/
        }

        // recognize question mark block
        for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            qmark = new Qmark(screen, rectangle);
        }


        goombas = new Array<Goomba>();
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            goombas.add(new Goomba(screen, rect.getX(),rect.getY()));
        }

        plants = new Array<Plant>();
        for(MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            plants.add(new Plant(screen, rect.getX(),rect.getY()));
        }

        coins = new Array<Coin>();
        for(MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            coins.add(new Coin(screen, rect.getX(),rect.getY()));
        }
    /*
        // recognize goombas Por agora não funciona
        for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyd.type = BodyDef.BodyType.KinematicBody;
            bodyd.position.set((rectangle.getX() + rectangle.getWidth() / 2) / MainClass.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / MainClass.PPM);

            body = world.createBody(bodyd);

            shape.setAsBox(rectangle.getWidth() / 2 / MainClass.PPM, rectangle.getHeight() / 2 / MainClass.PPM);
            fixtured.shape = shape;
            body.createFixture(fixtured);
        }

        // recognize plants
        for (MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyd.type = BodyDef.BodyType.KinematicBody;
            bodyd.position.set((rectangle.getX() + rectangle.getWidth() / 2) / MainClass.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / MainClass.PPM);

            body = world.createBody(bodyd);

            shape.setAsBox(rectangle.getWidth() / 2 / MainClass.PPM, rectangle.getHeight() / 2 / MainClass.PPM);
            fixtured.shape = shape;
            body.createFixture(fixtured);
        }
*/
    }

    public Array<Goomba> getGoombas() {
        return goombas;
    }
    public Array<Enemy> getEnemies() {
        Array<Enemy> enemies = new Array<Enemy>();
        enemies.addAll(goombas);
        enemies.addAll(plants);
        return enemies;
    }

    public Array<Item> getItems() {
        Array<Item> items = new Array<Item>();
        items.addAll(coins);
        return items;
    }
}
