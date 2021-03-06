package standa.drop.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import standa.drop.BackButtonHandler;
import standa.drop.BackgroundRainManager;
import standa.drop.DropGame;
import standa.drop.screens.gamescreen.GameScreen;

public class MenuScreen implements Screen {

    private Skin skin;
    private Stage stage;
    private Table table;
    private Dialog dialog;

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private DropGame drop;
    private BackgroundRainManager rain;

    private Texture dropTexture, onSoundTexture, offSoundTexture;
    private Sound clickSound;
    private InputMultiplexer multiplexer;

    public MenuScreen(DropGame drop){
        this.drop=drop;
        this.batch=drop.batch;
        this.shapeRenderer=drop.shapeRenderer;

        int padding = drop.width / 75;
        int buttonWidth = drop.width / 10;

        stage = new Stage(new StretchViewport(drop.width, drop.height), batch);

        multiplexer = new InputMultiplexer(new BackButtonHandler(), stage);

        clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/click.wav"));
        rain = new BackgroundRainManager(drop);

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        Label menuLabel = new Label("Drop game!", skin);
        dropTexture = new Texture(Gdx.files.internal("sprites/bucket.png"));
        Image dropImage = new Image(dropTexture);
        TextButton playButton = new TextButton("PLAY", skin);
        TextButton resetButton = new TextButton("RESET HIGHSCORE", skin);
        TextButton exitButton = new TextButton("EXIT", skin);

        CheckBox debugCheckBox = new CheckBox("Debug", skin);

        dialog = new Dialog("Warning", skin);
        TextButton yesButton = new TextButton("Yes", skin);
        TextButton noButton = new TextButton("No", skin);
        dialog.button(yesButton)
                .button(noButton)
                .text("Do you really want to reset your highscore?");

        onSoundTexture = new Texture(Gdx.files.internal("skin/onSound.png"));
        offSoundTexture = new Texture(Gdx.files.internal("skin/offSound.png"));
        TextureRegionDrawable onTexture = new TextureRegionDrawable(new TextureRegion(onSoundTexture));
        TextureRegionDrawable offTexture = new TextureRegionDrawable(new TextureRegion(offSoundTexture));
        ImageButton soundBtn = new ImageButton(onTexture, onTexture, offTexture);
        soundBtn.setHeight(64);
        soundBtn.setWidth(64);
        soundBtn.setPosition(drop.width - soundBtn.getWidth(), drop.height - soundBtn.getHeight());
        soundBtn.setChecked(!drop.pref.getBoolean("backgroundMusic"));

        ChangeListener listener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (actor == playButton) {
                    drop.setScreen(new GameScreen(drop));
                } else if (actor == resetButton) {
                    dialog.show(stage);
                } else if (actor == exitButton) {
                    Gdx.app.exit();
                } else if (actor == debugCheckBox) {
                    drop.debug = debugCheckBox.isChecked();
                    table.setDebug(drop.debug);
                } else if (actor == yesButton) {
                    drop.pref.putInteger("highScore", 0);
                } else if (actor == soundBtn) {
                    if (soundBtn.isChecked()) drop.stopMusic();
                    else drop.playMusic();
                    drop.pref.putBoolean("backgroundMusic", !soundBtn.isChecked());
                }
                clickSound.play();
            }
        };

        soundBtn.addListener(listener);
        exitButton.addListener(listener);
        resetButton.addListener(listener);
        playButton.addListener(listener);
        yesButton.addListener(listener);
        noButton.addListener(listener);
        debugCheckBox.addListener(listener);

        table = new Table();
        table.defaults().pad(padding);
        table.setDebug(drop.debug);
        table.add(menuLabel);
        table.row();
        table.add(dropImage);
        table.row();
        table.add(playButton).minWidth(buttonWidth);
        table.row();
        table.add(resetButton).minWidth(buttonWidth);
        table.row();
        table.add(exitButton).minWidth(buttonWidth);
        table.row();
        table.add(debugCheckBox);
        table.setFillParent(true);

        stage.addActor(table);
        stage.addActor(soundBtn);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        rain.tick();
        stage.act(Gdx.graphics.getDeltaTime());
        batch.begin();
        rain.draw(batch, shapeRenderer);
        batch.end();
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
        drop.pref.flush();
    }

    @Override
    public void dispose() {
        stage.dispose();
        rain.dispose();
        skin.dispose();
        dropTexture.dispose();
        onSoundTexture.dispose();
        offSoundTexture.dispose();
    }
}
