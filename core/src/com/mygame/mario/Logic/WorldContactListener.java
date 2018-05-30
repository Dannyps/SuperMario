package com.mygame.mario.Logic;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygame.mario.Characters.Bricks;
import com.mygame.mario.Characters.Coin;
import com.mygame.mario.Characters.InteractiveObjects;
import com.mygame.mario.MainClass;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        //player
        Fixture fixA = contact.getFixtureA();
        //an object
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "head" || fixB.getUserData() == "head")
        {
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if(object.getUserData() instanceof Coin)
            {
                ((InteractiveObjects) object.getUserData()).HeadColission();
                MainClass.manager.get("Audio/sounds/coin.wav",Sound.class).play();
            }
            else if( object.getUserData() instanceof Bricks){
                ((InteractiveObjects) object.getUserData()).HeadColission();
                MainClass.manager.get("Audio/sounds/breakblock.wav",Sound.class).play();
            }
            else if(object.getUserData() instanceof InteractiveObjects)
            {
                ((InteractiveObjects) object.getUserData()).HeadColission();
                MainClass.manager.get("Audio/sounds/bump.wav",Sound.class).play();
            }

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
