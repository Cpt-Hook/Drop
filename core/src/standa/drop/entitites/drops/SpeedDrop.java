package standa.drop.entitites.drops;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import standa.drop.screens.gamescreen.GameScreen;

public class SpeedDrop extends Drop {

    private Sound bonusSound;

    public SpeedDrop(Texture texture, GameScreen game, Sound bonusSound) {
        super((int) (texture.getWidth() * (3 / 4d)), (int) (texture.getHeight() * (3 / 4d)), 10, texture, game);
        this.bonusSound = bonusSound;
    }

    @Override
    protected void bucketHit() {
        game.bucket.doubleSpeed(5000);
        bonusSound.play();
        toDie=true;
    }

    @Override
    public void dispose() {
        super.dispose();
        bonusSound.dispose();
    }
}
