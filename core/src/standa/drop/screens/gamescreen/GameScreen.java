package standa.drop.screens.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import standa.drop.BackgroundRainManager;
import standa.drop.DropGame;
import standa.drop.entitites.Bucket;
import standa.drop.screens.InfoScreen;

public class GameScreen implements Screen {

    public final DropGame drop;
    public int width, height, tickCount;
    public GameState state;

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    public Bucket bucket;
    private DropManager dropManager;
    private standa.drop.BackgroundRainManager rainManager;
    private InfoPrinter printer;

    private int health = 3, score = 0, highScore;

    //TODO poolable managers (drops, raindrops)

    public GameScreen(DropGame drop) {
        this.drop = drop;
        this.state = GameState.RUNNING;
        width = drop.width;
        height = drop.height;

        Gdx.input.setInputProcessor(new InputHandler(this));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        batch = drop.batch;
        shapeRenderer = drop.shapeRenderer;

        Texture bucketTexture = new Texture(Gdx.files.internal("sprites/bucket.png"));
        Sound endOfBOnusSound = Gdx.audio.newSound(Gdx.files.internal("sounds/endofbonus.wav"));
        bucket = new Bucket(bucketTexture, endOfBOnusSound, this);

        dropManager = new DropManager(this);

        rainManager = new BackgroundRainManager(drop);

        printer = new InfoPrinter(this);

        highScore = drop.pref.getInteger("highScore");
    }

    public void switchGameState() {
        if (state == GameState.RUNNING) state = GameState.PAUSED;
        else state = GameState.RUNNING;
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if (state == GameState.RUNNING) {
            tick();
        }
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0, 0, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        draw();
        batch.end();
    }

    public void updateHighScore(int score) {
        if (score > highScore) {
            highScore = score;
            drop.pref.putInteger("highScore", highScore);
        }
    }

    private void draw() {
        rainManager.draw(batch, shapeRenderer);
        bucket.draw(batch, shapeRenderer);
        dropManager.draw(batch, shapeRenderer);
        printer.draw(batch, shapeRenderer);
    }

    private void tick() {
        bucket.tick();
        dropManager.tick();
        rainManager.tick();
        updateHighScore(score);
        printer.setText(score, health, highScore);
        if (health == 0) drop.setScreen(new InfoScreen(drop, "Game over"));
        tickCount++;
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
        drop.setScreen(new InfoScreen(drop, "Game paused", this));
    }

    @Override
    public void hide() {
        drop.pref.flush();
    }

    @Override
    public void dispose() {
        bucket.dispose();
        dropManager.dispose();
        rainManager.dispose();
    }

    public void removeHealth() {
        health--;
    }

    public void addHealth() {
        health++;
    }

    public void addScore() {
        score++;
    }

    enum GameState {
        PAUSED, RUNNING
    }
}
