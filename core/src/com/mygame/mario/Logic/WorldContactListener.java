package com.mygame.mario.Logic;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygame.mario.Characters.Goomba;
import com.mygame.mario.Characters.Mario;
import com.mygame.mario.Objects.Bricks;
import com.mygame.mario.Objects.Coin;
import com.mygame.mario.Objects.InteractiveObjects;
import com.mygame.mario.MainClass;
import com.mygame.mario.Objects.Item;
import com.mygame.mario.Objects.PipeLevel;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        //player
        Body bodA = contact.getFixtureA().getBody();
        Fixture fixA = contact.getFixtureA();
        //an object
        Body bodB = contact.getFixtureA().getBody();
        Fixture fixB = contact.getFixtureB();
        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits; // two collisions


        if(fixA.getUserData() == "head" || fixB.getUserData() == "head")
        {
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;


            if(object.getUserData() instanceof Bricks){
                ((InteractiveObjects) object.getUserData()).HeadColission();
                MainClass.manager.get("Audio/sounds/breakblock.wav",Sound.class).play();
            }
            else if(object.getUserData() instanceof InteractiveObjects)
            {
                ((InteractiveObjects) object.getUserData()).HeadColission();
                MainClass.manager.get("Audio/sounds/bump.wav",Sound.class).play();
            }
            else if(object.getUserData() instanceof Item)
            {
                ((Item) object.getUserData()).use();
                MainClass.manager.get("Audio/sounds/bump.wav",Sound.class).play();
            }



        }
        switch(cDef){
                case MainClass.MARO_BIT | MainClass.ENEMY_BIT:
                    if(fixA.getFilterData().categoryBits == MainClass.ENEMY_BIT){
                        //((Goomba)fixA.getUserData()).use();
                        MainClass.manager.get("Audio/sounds/coin.wav",Sound.class).play();}
                    else if(fixB.getFilterData().categoryBits == MainClass.ENEMY_BIT){
                        //((Goomba)fixA.getUserData()).use();
                        MainClass.manager.get("Audio/sounds/coin.wav",Sound.class).play();}
                case MainClass.MARO_BIT | MainClass.COIN_BIT:
                    if(fixA.getFilterData().categoryBits == MainClass.COIN_BIT){
                        ((Coin)fixA.getUserData()).use();
                        MainClass.manager.get("Audio/sounds/coin.wav",Sound.class).play();}
                    else if(fixB.getFilterData().categoryBits == MainClass.COIN_BIT){
                        ((Coin)fixB.getUserData()).use();
                        MainClass.manager.get("Audio/sounds/coin.wav",Sound.class).play();}
        }
        /* ATTEMPT 2


        Gdx.app.postRunnable(new Runnable() {

            @Override
            public void run () {
                switch(cDef){
                    case MainClass.MARO_BIT | MainClass.COIN_BIT:
                        if(fixA.getFilterData().categoryBits == MainClass.COIN_BIT)
                            ((Item)fixA.getUserData()).use();
                        else if(fixB.getFilterData().categoryBits == MainClass.COIN_BIT)
                            ((Item)fixB.getUserData()).use();
                }
            }
        });*/


        if(fixA.getUserData() == "bound" || fixB.getUserData() == "bound")
        {
            Fixture head = fixA.getUserData() == "bound" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if(object.getUserData() instanceof Goomba)
            {
                ((Goomba) object.getUserData()).hitOnHead();
                MainClass.manager.get("Audio/sounds/stomp.wav",Sound.class).play();

            }/*
            else if(object.getUserData() instanceof Coin){
                ((Coin) object.getUserData()).use();
                MainClass.manager.get("Audio/sounds/breakblock.wav",Sound.class).play();
            }*/
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
