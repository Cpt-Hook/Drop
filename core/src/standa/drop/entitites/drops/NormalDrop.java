package standa.drop.entitites.drops;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import standa.drop.screens.gamescreen.GameScreen;

public class NormalDrop extends Drop {

    private Sound dropSound, gameOverSound;

    public NormalDrop(Texture texture, GameScreen game, Sound dropSound, Sound gameOverSound) {
        super(texture.getWidth(), texture.getHeight(), 6, texture, game);
        this.dropSound = dropSound;
        this.gameOverSound = gameOverSound;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void bucketHit() {
        toDie = true;
        dropSound.play();
        game.addScore();
    }

    @Override
    protected void goneOffScreen() {
        super.goneOffScreen();
        gameOverSound.play();
        game.removeHealth();
    }

    @Override
    public void dispose() {
        super.dispose();
        dropSound.dispose();
        gameOverSound.dispose();
    }
}
