package com.mygame.mario.Characters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.mario.Levels.BaseScreen;

public abstract class Enemys extends Sprite {

    protected World world;
    protected BaseScreen screen;
    public Body body;

    public Enemys(BaseScreen screen, float x, float y)
    {
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnenmy();
    }

    protected abstract void defineEnenmy();
    //protected abstract void update (float fl);
}
