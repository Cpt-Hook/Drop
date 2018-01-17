package standa.drop.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private DropGame game;
    private BackgroundRainManager rain;
    private ChangeListener listener;

    private int padding;
    private int buttonWidth;


    private Texture dropTexture;
    private Sound clickSound;

    public MenuScreen(DropGame game){
        this.game=game;
        this.batch=game.batch;
        padding = game.width/75;
        buttonWidth = game.width/10;

        clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/click.wav"));
        rain = new BackgroundRainManager(new Texture(Gdx.files.internal("sprites/raindrop.png")), game);

        stage = new Stage(new StretchViewport(game.width, game.height), batch);
        Gdx.input.setInputProcessor(stage);


        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        Label menuLabel = new Label("Drop game!", skin);
        dropTexture = new Texture(Gdx.files.internal("sprites/bucket.png"));
        Image dropImage = new Image(dropTexture);
        TextButton playButton = new TextButton("PLAY", skin);
        TextButton resetButton = new TextButton("RESET HIGHSCORE", skin);
        TextButton exitButton = new TextButton("EXIT", skin);

        listener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(actor == playButton){
                    game.setScreen(new GameScreen(game));
                }else if(actor == resetButton){
                    game.pref.putInteger("highScore", 0);
                }else if(actor == exitButton){
                    Gdx.app.exit();
                }
                clickSound.play();
            }
        };
        exitButton.addListener(listener);
        resetButton.addListener(listener);
        playButton.addListener(listener);

        Table table = new Table();
        table.setDebug(false);
        table.add(menuLabel).pad(padding);
        table.row();
        table.add(dropImage).pad(padding);
        table.row();
        table.add(playButton).minWidth(buttonWidth).pad(padding);
        table.row();
        table.add(resetButton).minWidth(buttonWidth).pad(padding);
        table.row();
        table.add(exitButton).minWidth(game.width/10).pad(padding);
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
        rain.draw(batch);
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
