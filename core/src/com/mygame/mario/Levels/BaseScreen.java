package com.mygame.mario.Levels;

import com.badlogic.gdx.Gdx;
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

    public BaseScreen(MainClass game)
    {
        this.game = game;

        gameCam = new OrthographicCamera();
        //gamePort = new StretchViewport(800, 400, gameCam);
        //gamePort = new ScreenViewport(gameCam);
        gamePort = new FitViewport(MainClass.V_WIDTH/MainClass.classico_100, MainClass.V_HEIGTH/ MainClass.classico_100, gameCam);

        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("level1m.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getScreenWidth()/2, gamePort.getScreenHeight()/2, 0);

        world = new World (new Vector2(0, 0), true);
        // to recognize pixels map
        b2dr = new Box2DDebugRenderer();

        BodyDef bodyd = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtured = new FixtureDef();
        Body body;

        // recognize empty block
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyd.type = BodyDef.BodyType.StaticBody;
            bodyd.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);
            body = world.createBody(bodyd);
            shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
            fixtured.shape = shape;
            body.createFixture(fixtured);
        }

        // recognize ground
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyd.type = BodyDef.BodyType.StaticBody;
            bodyd.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);
            body = world.createBody(bodyd);
            shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
            fixtured.shape = shape;
            body.createFixture(fixtured);
        }

        // recognize bricks
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyd.type = BodyDef.BodyType.StaticBody;
            bodyd.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);
            body = world.createBody(bodyd);
            shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
            fixtured.shape = shape;
            body.createFixture(fixtured);
        }

        // recognize pipes
        for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyd.type = BodyDef.BodyType.StaticBody;
            bodyd.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);
            body = world.createBody(bodyd);
            shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
            fixtured.shape = shape;
            body.createFixture(fixtured);
        }

        // recognize stairs
        for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyd.type = BodyDef.BodyType.StaticBody;
            bodyd.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);
            body = world.createBody(bodyd);
            shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
            fixtured.shape = shape;
            body.createFixture(fixtured);
        }

        // recognize pipe level
        for (MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyd.type = BodyDef.BodyType.StaticBody;
            bodyd.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);
            body = world.createBody(bodyd);
            shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
            fixtured.shape = shape;
            body.createFixture(fixtured);
        }

        // recognize question mark block
        for (MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyd.type = BodyDef.BodyType.StaticBody;
            bodyd.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);
            body = world.createBody(bodyd);
            shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
            fixtured.shape = shape;
            body.createFixture(fixtured);
        }

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

        // render b2dr
        b2dr.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);
        hud.stage.draw();


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
        //hud.dispose();

    }

    public void handleInput(float fl)
    {
        if(Gdx.input.isTouched()) {
            if (Gdx.input.getX() > 0) {
                gameCam.position.x += 100 * fl;
            }

            if (Gdx.input.getX() < 0) {
                gameCam.position.x -= 100 * fl;
            }
        }
    }

    public void update (float fl)
    {
        handleInput(fl);
        gameCam.update();
        renderer.setView(gameCam);
    }
}
