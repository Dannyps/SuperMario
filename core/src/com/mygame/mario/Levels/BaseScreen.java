package com.mygame.mario.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygame.mario.Characters.Goomba;
import com.mygame.mario.Characters.Mario;
import com.mygame.mario.Logic.LoadMap;
import com.mygame.mario.Logic.WorldContactListener;
import com.mygame.mario.MainClass;
import com.mygame.mario.Scenes.Hud;

public class BaseScreen implements Screen {

    private MainClass game;
    private TextureAtlas texAtlas;

    //Camera objects
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    //Map Tiles objects
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box objects
    private World world;
    private Box2DDebugRenderer b2dr;
    private LoadMap loadMap;

    //Actors
    private Mario player;
    private Goomba goomba;

    //buttons
    private TextButton buttonUp;
    private TextButton buttonFire;

    public BaseScreen(MainClass game)
    {
        this.game = game;
        this.texAtlas = new TextureAtlas("Mario_friends.pack");

        gameCam = new OrthographicCamera();
        //gamePort = new StretchViewport(800, 400, gameCam);
        //gamePort = new ScreenViewport(gameCam);
        //gamePort = new FitViewport(MainClass.V_WIDTH/MainClass.PPM, MainClass.V_HEIGTH/ MainClass.PPM, gameCam);
        gamePort = new StretchViewport(3, 2, gameCam);

        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = level("level1m.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/MainClass.PPM);
        //gameCam.position.set(gamePort.getScreenWidth()/2, gamePort.getScreenHeight()/2, 0);
        gameCam.position.set(0, 1, 0);

        buttonUp = new TextButton("Jump", new Skin(Gdx.files.internal("Skins/flat-earth/skin/flat-earth-ui.json")));
        buttonFire = new TextButton("Shots", new Skin(Gdx.files.internal("Skins/flat-earth/skin/flat-earth-ui.json")));
        loadButtons();

        world = new World (new Vector2(0, -10f), true);
        player = new Mario(this);
        goomba = new Goomba(this,25,25);

        world.setContactListener(new WorldContactListener());

        // to recognize pixels map
        b2dr = new Box2DDebugRenderer();

        loadMap = new LoadMap(this);

    }


    public TextureAtlas getTexAtlas()
    {
        return texAtlas;
    }

    //function that load levels
    public TiledMap level(String level)
    {
        TiledMap map;
        map = maploader.load(level);
        return map;
    }

    //function that specifies buttons
    public void loadButtons()
    {
        //To buttonUp
        //buttonUp.getLabel().setFontScale(3.0f,3.0f);
        //buttonUp.getLabel().setSize(50.0f,50.0f);
        buttonUp.setSize(50, 50);
        buttonUp.setPosition(100, 100);
        hud.stage.addActor(buttonUp);
        Gdx.input.setInputProcessor(hud.stage);

        // if touched jump
        buttonUp.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //only jump if Mario is on the ground
                if(!((player.currentState.equals(Mario.State.Jump)) || (player.currentState.equals(Mario.State.Fall)))) {
                    player.body.applyLinearImpulse(new Vector2(0, 4f), player.body.getWorldCenter(), true);
                    return true;
                }

                return true;
            }
        });

        //To buttonFire
        //TO DO
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        // screen color
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render game map
        renderer.render();

        // render b2dr the update method
        b2dr.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();
        player.draw(game.batch);
        goomba.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();


    }

    @Override
    public void resize(int width, int height)
    {
        gamePort.update(width,height);
    }

    public TiledMap getMap()
    {
        return map;
    }

    public World getWorld()
    {
        return world;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();

        hud.dispose();

    }

    public void handleInput(float fl)
    {
        // for desktop
        /*if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.body.applyLinearImpulse(new Vector2(0, 4f), player.body.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.body.getLinearVelocity().x <= 2)
        {
            player.body.applyLinearImpulse(new Vector2(0.1f, 0), player.body.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.body.getLinearVelocity().x >= -2)
        {
            player.body.applyLinearImpulse(new Vector2(-0.1f, 0), player.body.getWorldCenter(), true);
        }*/

        //&& (!(Gdx.input.isButtonPressed((int) ((int) buttonUp.getX() + buttonUp.getWidth())))

        //for android
        if(Gdx.input.isTouched()  && (!(buttonUp.isPressed())))
        {
            //rigth one
            if ((Gdx.input.getX() > Gdx.graphics.getWidth() / 2) && (player.body.getLinearVelocity().x <= 2))
            {
                player.body.applyLinearImpulse(new Vector2(0.1f, 0), player.body.getWorldCenter(), true);
            }

            //left one
            if((Gdx.input.getX() < Gdx.graphics.getWidth() / 2) && (player.body.getLinearVelocity().x >= -2))
             {
                 player.body.applyLinearImpulse(new Vector2(-0.1f, 0), player.body.getWorldCenter(), true);
             }

        }

    }

    public void update (float fl)
    {
        handleInput(fl);

        world.step(1/60f, 6, 2);
        goomba.update(fl);
        player.update(fl);

        hud.update(fl);

        gameCam.position.x = player.body.getPosition().x;
        gameCam.update();

        renderer.setView(gameCam);
    }
}
