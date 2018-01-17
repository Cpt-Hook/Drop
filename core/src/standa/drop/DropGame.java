package standa.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import standa.drop.screens.MenuScreen;


public class DropGame extends Game {

    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;
    public Preferences pref;

    private Music rainMusic;
    private MenuScreen menu;

    public int width=900, height=450;
    public boolean debug = false;

    @Override
    public void create() {
        pref = Gdx.app.getPreferences("DropPreferences");
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/raining.mp3"));
        rainMusic.setLooping(true);
        rainMusic.setVolume(0.25f);
        rainMusic.play();
        menu = new MenuScreen(this);
        changeScreenToMenu();
    }

    public void changeScreenToMenu(){
        setScreen(menu);
    }

    @Override
    public void pause() {
        super.pause();
        rainMusic.stop();
    }

    @Override
    public void resume() {
        super.resume();
        rainMusic.play();
    }

    @Override
    public void dispose () {
        if (screen != null) screen.dispose();
        font.dispose();
        batch.dispose();
        pref.flush();
        rainMusic.dispose();
    }
}
