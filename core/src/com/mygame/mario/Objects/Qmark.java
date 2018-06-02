package com.mygame.mario.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.mygame.mario.Logic.BaseScreen;
import com.mygame.mario.MainClass;
import com.badlogic.gdx.math.Rectangle;
import com.mygame.mario.Scenes.Hud;

public class Qmark extends InteractiveObjects {

    private static TiledMapTileSet tileSet;
    private final int EMPTY_BLOCK = 28;

    public Qmark(BaseScreen screen, Rectangle bounds) {
        super(screen, bounds);

        fixture.setUserData(this);
        tileSet = map.getTileSets().getTileSet("tile-supermario");
        setCategoryFilter(MainClass.BRICK_BIT);

    }

    @Override
    public void HeadColission() {

        Gdx.app.log("QMark", "Colission");
        if((getCell().getTile().getId() == 25) || (getCell().getTile().getId() == 27)) {
            Hud.updateScore(20);
        }

        else
            Hud.updateScore(0);

        getCell().setTile(tileSet.getTile(EMPTY_BLOCK)); //change image

    }
}
