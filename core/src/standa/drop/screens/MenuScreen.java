package standa.drop.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import standa.drop.BackgroundRainManager;
import standa.drop.DropGame;
import standa.drop.screens.gamescreen.GameScreen;

public class MenuScreen implements Screen {

    private Skin skin;
    private Stage stage;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private DropGame drop;
    private BackgroundRainManager rain;
    private ChangeListener listener;


    private Texture dropTexture;
    private Sound clickSound;

    public MenuScreen(DropGame drop){
        this.drop=drop;
        this.batch=drop.batch;

        int padding = drop.width / 75;
        int buttonWidth = drop.width / 10;

        clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/click.wav"));
        rain = new BackgroundRainManager(new Texture(Gdx.files.internal("sprites/raindrop.png")), drop);

        stage = new Stage(new StretchViewport(drop.width, drop.height), batch);
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        Label menuLabel = new Label("Drop game!", skin);
        dropTexture = new Texture(Gdx.files.internal("sprites/bucket.png"));
        Image dropImage = new Image(dropTexture);
        TextButton playButton = new TextButton("PLAY", skin);
        TextButton resetButton = new TextButton("RESET HIGHSCORE", skin);
        TextButton exitButton = new TextButton("EXIT", skin);

        CheckBox debugCheckBox = new CheckBox("Debug", skin);


        Dialog dialog = new Dialog("Warning", skin);
        TextButton yesButton = new TextButton("Yes", skin);
        TextButton noButton = new TextButton("No", skin);
        dialog.button(yesButton)
                .button(noButton)
                .text("Do you really want to reset your highscore?");


        listener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(actor == playButton){
                    drop.setScreen(new GameScreen(drop));
                }else if(actor == resetButton){
                    dialog.show(stage);
                }else if(actor == exitButton){
                    Gdx.app.exit();
                }else if(actor == debugCheckBox){
                    drop.debug = debugCheckBox.isChecked();
                }else if(actor == yesButton){
                    drop.pref.putInteger("highScore", 0);
                    drop.pref.flush();
                }
                clickSound.play();
            }
        };

        exitButton.addListener(listener);
        resetButton.addListener(listener);
        playButton.addListener(listener);
        yesButton.addListener(listener);
        noButton.addListener(listener);
        debugCheckBox.addListener(listener);

        Table table = new Table();
        table.defaults().pad(padding);
        table.setDebug(true);
        table.add(menuLabel);
        table.row();
        table.add(dropImage);
        table.row();
        table.add(playButton).minWidth(buttonWidth);
        table.row();
        table.add(resetButton).minWidth(buttonWidth);
        table.row();
        table.add(exitButton).minWidth(drop.width/10);
        table.row();
        table.add(debugCheckBox);
        table.setFillParent(true);
        stage.addActor(table);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        rain.tick();
        batch.begin();
        rain.draw(batch, shapeRenderer);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
        skin.dispose();
        dropTexture.dispose();
    }
}
