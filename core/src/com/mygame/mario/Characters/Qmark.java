package com.mygame.mario.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.mario.MainClass;
import com.badlogic.gdx.math.Rectangle;
import com.mygame.mario.Scenes.Hud;

public class Qmark extends InteractiveObjects {

    private static TiledMapTileSet tileSet;
    private final int EMPTY_BLOCK = 28;

    public Qmark(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);

        fixture.setUserData(this);

        tileSet = map.getTileSets().getTileSet("tile-supermario");

        /*BodyDef bodyd = new BodyDef();
        FixtureDef fixtured = new FixtureDef();
        PolygonShape shape = new PolygonShape();


            bodyd.type = BodyDef.BodyType.StaticBody;
            bodyd.position.set((bounds.getX() + bounds.getWidth() / 2) / MainClass.PPM, (bounds.getY() + bounds.getHeight() / 2) / MainClass.PPM);

            body = world.createBody(bodyd);

            shape.setAsBox(bounds.getWidth() / 2 / MainClass.PPM, bounds.getHeight() / 2 / MainClass.PPM);
            fixtured.shape = shape;
            body.createFixture(fixtured);*/
        setCategoryFilter(MainClass.BRICK_BIT);

    }

    @Override
    public void HeadColission() {

        Gdx.app.log("QMark", "Colission");
        //setCategoryFilter(MainClass.BRICK_BIT);
        if(getCell().getTile().equals(MainClass.BRICK_BIT)) {
            Hud.updateScore(20);
        }

        getCell().setTile(tileSet.getTile(EMPTY_BLOCK)); //change image

    }
}
