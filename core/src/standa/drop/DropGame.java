package standa.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import standa.drop.screens.MenuScreen;


public class DropGame extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public Preferences pref;

    private Music rainMusic;

    public int width=800, height=480;

    @Override
    public void create() {
        pref = Gdx.app.getPreferences("DropPreferences");
        batch = new SpriteBatch();
        font = new BitmapFont();
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/raining.mp3"));
        rainMusic.setLooping(true);
        rainMusic.setVolume(0.25f);
        rainMusic.play();

        setScreen(new MenuScreen(this));
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
