package com.mygame.mario.Scenes;

/*import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygame.mario.MainClass;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.awt.Color;
import java.awt.Label;*/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygame.mario.MainClass;

public class BaseStage {
    public Stage stage;
    private Viewport viewport;

    private Integer score;
    private Integer timer;
    private float countTime;

    Label lcount;
    Label lscore;
    Label ltime;
    Label llevel;
    Label lworld;
    Label lmario;

    public BaseStage(SpriteBatch sbatch) {
        timer = 300;
        countTime = 0;
        score = 0;

        viewport = new FitViewport(MainClass.V_WIDTH, MainClass.V_HEIGTH, new OrthographicCamera());
        stage = new Stage(viewport, sbatch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        lcount = new Label(String.format("%03d", timer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //lscore = new Label(String.format("%03d", timer),new Label.)
        Label ltime;
        Label llevel;
        Label lworld;
        Label lmario;


    }
}
