package com.mygame.mario.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.mario.MainClass;

public class CoinBrick extends InteractiveObjects{
    public CoinBrick(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);

        setCategoryFilter(MainClass.COIN_BIT);
    }

    @Override
    public void HeadColission() {

        Gdx.app.log("Coin", "Collission");

    }
}
