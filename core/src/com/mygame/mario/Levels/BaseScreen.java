package com.mygame.mario.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygame.mario.Characters.Mario;
import com.mygame.mario.MainClass;
import com.mygame.mario.Scenes.Hud;
import com.mygame.mario.Logic.LoadMap;
import com.mygame.mario.Scenes.MainMenu;

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

    public BaseScreen(MainClass game)
    {
        this.game = game;

        gameCam = new OrthographicCamera();
        //gamePort = new StretchViewport(800, 400, gameCam);
        //gamePort = new ScreenViewport(gameCam);
        gamePort = new FitViewport(MainClass.V_WIDTH/MainClass.PPM, MainClass.V_HEIGTH/ MainClass.PPM, gameCam);

        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("level1m.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/MainClass.PPM);
        gameCam.position.set(gamePort.getScreenWidth()/2, gamePort.getScreenHeight()/2, 0);

        world = new World (new Vector2(0, -10), true);
        player = new Mario(world);

        // to recognize pixels map
        b2dr = new Box2DDebugRenderer();

        loadMap = new LoadMap(world, map);


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        // screen color
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render game map
        renderer.render();

        // render b2dr the update method
        b2dr.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);

        hud.stage.draw();


    }

    @Override
    public void resize(int width, int height)
    {
        gamePort.update(width,height);
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.body.applyLinearImpulse(new Vector2(0, 4f), player.body.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.body.getLinearVelocity().x <= 2)
        {
            player.body.applyLinearImpulse(new Vector2(0.1f, 0), player.body.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.body.getLinearVelocity().x >= -2)
        {
            player.body.applyLinearImpulse(new Vector2(-0.1f, 0), player.body.getWorldCenter(), true);
        }
    }

    public void update (float fl)
    {
        handleInput(fl);

        world.step(1/60f, 6, 2);

        gameCam.position.x = player.body.getPosition().x;
        gameCam.update();

        renderer.setView(gameCam);
    }
}
