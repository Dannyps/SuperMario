package com.mygame.mario.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygame.mario.MainClass;
import com.mygame.mario.Scenes.Hud;
import com.mygame.mario.Logic.LoadMap;

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

    public BaseScreen(MainClass game)
    {
        this.game = game;

        gameCam = new OrthographicCamera();
        //gamePort = new StretchViewport(800, 400, gameCam);
        //gamePort = new ScreenViewport(gameCam);
        gamePort = new FitViewport(MainClass.V_WIDTH, MainClass.V_HEIGTH, gameCam);

        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("C:\\Users\\mcaro\\Documents\\Aulas\\Universidade\\2º Ano\\2º Semestre\\LPOO\\level1m.tmx");
        //map = new TmxMapLoader().load("../../assets/level1m.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getScreenWidth(), gamePort.getScreenHeight(), 0);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        //game.batch.setProjectionMatrix(gameCam.combined);


    }

    @Override
    public void resize(int width, int height) {
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

    }

    public void handleInput(float fl)
    {
        if(Gdx.input.isTouched())
        {
            gameCam.position.x += 70 * fl;
        }
    }

    public void update (float fl)
    {
        handleInput(fl);
        gameCam.update();
        renderer.setView(gameCam);
    }
}
