package com.mygame.mario.Characters;

import com.badlogic.gdx.math.Vector2;
import com.mygame.mario.Logic.BaseScreen;
import com.mygame.mario.MainClass;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MarioTest extends GameTest{
    MainClass main;
    BaseScreen screen;
    Mario player;

    @Before
    public void setUp() throws Exception {
        main = new MainClass();
        screen = new BaseScreen(main);
        player = new Mario(screen);

    }
    @Test
    public void testMarioJump(){
        float initialX = player.body.getPosition().x;
        player.body.applyLinearImpulse(new Vector2(0.1f, 0), player.body.getWorldCenter(), true);
        screen.update(1/300);
        assertNotSame(initialX, player.body.getPosition().x);
    }
}