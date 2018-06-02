package com.mygame.mario.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygame.mario.Logic.BaseScreen;
import com.mygame.mario.MainClass;
import com.mygame.mario.Scenes.Hud;

public class Bricks extends InteractiveObjects{
    public Bricks(BaseScreen screen, Rectangle bounds) {
        super(screen, bounds);

        fixture.setUserData(this);
        setCategoryFilter(MainClass.BRICK_BIT);

    }

    @Override
    public void HeadColission()
    {
        Gdx.app.log("Brick", "Colission");
        setCategoryFilter(MainClass.DESTROYRD_BIT);
        getCell().setTile(null); //erase image object
        Hud.updateScore(10);

    }
}
