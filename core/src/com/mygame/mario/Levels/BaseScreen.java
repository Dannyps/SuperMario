package com.mygame.mario.Levels;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygame.mario.MainClass;

public class BaseScreen implements Screen {

    private MainClass game;
    //Texture texture;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    public BaseScreen(MainClass game)
    {
        this.game = game;
        gameCam = new OrthographicCamera();
        //gamePort = new StretchViewport(800, 400, gameCam);
        //gamePort = new ScreenViewport(gameCam);
        gamePort = new FitViewport(MainClass.V_WIDTH, MainClass.V_HEIGTH, gameCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        game.batch.end();


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
}
