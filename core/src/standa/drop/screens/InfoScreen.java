package standa.drop.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import standa.drop.DropGame;

public class InfoScreen implements Screen {

    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private GlyphLayout layout;
    private DropGame game;
    private Screen savedInstance;
    private String text;


    public InfoScreen(DropGame game) {
        this.batch = game.batch;
        this.font = game.font;
        this.game = game;
        this.text = "Drop game";

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.width, game.height);
        layout = new GlyphLayout();
    }

    public InfoScreen(DropGame game, String text){
        this(game);
        this.text = text;
    }

    public InfoScreen(DropGame game, String text, Screen savedInstance){
        this(game,text);
        this.savedInstance = savedInstance;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        layout.setText(font, text, Color.WHITE, 0, Align.left, true);
        font.draw(batch, layout, game.width/2 - layout.width/2, game.height/2 - layout.height/2 + layout.height);
        layout.setText(font, "Tap anywhere to continue", Color.WHITE, 0, Align.left, true);
        font.draw(batch, layout, game.width/2 - layout.width/2, game.height/2 - layout.height/2 - layout.height);
        batch.end();

        if(Gdx.input.isTouched()) {
            game.setScreen(savedInstance == null?new MenuScreen(game):savedInstance);
        }
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
