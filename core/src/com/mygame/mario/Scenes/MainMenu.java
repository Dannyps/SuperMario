package com.mygame.mario.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygame.mario.Logic.BaseScreen;
import com.mygame.mario.MainClass;

public class MainMenu implements Screen {

    private MainClass menu;
    //private Label Play;
    private Stage stage;
    private TextureRegion background;
    private TextButton playGame;


    public MainMenu(MainClass menu) {
        this.menu = menu;
        this.stage = new Stage();
        this.background = new TextureRegion(new Texture("background.jpg"), 0, 0, 768, 432);
        playGame = new TextButton("Play Game", new Skin(Gdx.files.internal("Skins/flat-earth/skin/flat-earth-ui.json")));
        loadButton(playGame);
    }

    private void loadButton(TextButton playGame) {
        //buttonUp.getLabel().setFontScale(3.0f,3.0f);
        //buttonUp.getLabel().setSize(50.0f,50.0f);
        int playsizex = 400;
        int playsizey = 120;
        playGame.setSize(playsizex, playsizey);
        playGame.setPosition(Gdx.graphics.getWidth()/2 - (playsizex/2), Gdx.graphics.getHeight()/2 - (Gdx.graphics.getHeight() / 5));
        //playGame.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight() - 400);

        Gdx.input.setInputProcessor(this.stage);
        playGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                menu.setScreen(new BaseScreen(menu));
            }
        });
        this.stage.addActor(playGame);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        menu.batch.begin();
        menu.batch.end();

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();

        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

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
