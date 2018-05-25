package com.mygame.mario.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.mario.Levels.BaseScreen;
import com.mygame.mario.MainClass;
import com.mygame.mario.Scenes.Hud;

public class CoinBrick extends InteractiveObjects{

    private static TiledMapTileSet tileSet;
    private final int EMPTY_BLOCK = 3;

    public CoinBrick(BaseScreen screen, Rectangle bounds) {
        super(screen, bounds);

        tileSet = map.getTileSets().getTileSet("tile_supermario");

        fixture.setUserData(this);
        setCategoryFilter(MainClass.COIN_BIT);
    }

    @Override
    public void HeadColission() {

        Gdx.app.log("Coin", "Collission");
        getCell().setTile(tileSet.getTile(EMPTY_BLOCK));
        Hud.updateScore(50);

    }
}
