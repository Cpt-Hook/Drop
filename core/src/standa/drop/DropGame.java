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

    private Music backgroundMusic;
    private MenuScreen menu;

    public int width=900, height=450;
    public boolean debug = false, playMusic;

    @Override
    public void create() {
        pref = Gdx.app.getPreferences("DropPreferences");
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/raining.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.25f);

        Gdx.input.setCatchBackKey(true);

        if(!pref.contains("backgroundMusic")) playMusic = true;
        playMusic = pref.getBoolean("backgroundMusic");
        if(playMusic) backgroundMusic.play();
        menu = new MenuScreen(this);
        changeScreenToMenu();
    }

    public void changeScreenToMenu(){
        setScreen(menu);
    }

    @Override
    public void pause() {
        super.pause();
        backgroundMusic.stop();
    }

    @Override
    public void resume() {
        super.resume();
        if(playMusic) backgroundMusic.play();
    }

    public void stopMusic(){
        backgroundMusic.stop();
        playMusic = false;
    }

    public void playMusic(){
        backgroundMusic.play();
        playMusic = true;
    }

    @Override
    public void dispose () {
        pref.flush();
        if (screen != null) screen.dispose();
        font.dispose();
        batch.dispose();
        backgroundMusic.dispose();
    }
}
