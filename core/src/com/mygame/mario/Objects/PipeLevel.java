package com.mygame.mario.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygame.mario.Logic.BaseScreen;
import com.mygame.mario.MainClass;

public class PipeLevel extends InteractiveObjects {
    public PipeLevel(BaseScreen screen, Rectangle bounds) {
        super(screen, bounds);

        fixture.setUserData(this);
        setCategoryFilter(MainClass.PIPE_LEVEL);
    }

    @Override
    public void HeadColission() {
        //Dont need them
    }

    public void BoundColission() {

        Gdx.app.log("Pipe", "Colission");

    }


}
