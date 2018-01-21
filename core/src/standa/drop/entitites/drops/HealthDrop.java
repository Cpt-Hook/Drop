package standa.drop.entitites.drops;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import standa.drop.screens.gamescreen.GameScreen;

public class HealthDrop  extends Drop{
    private Sound bonusSound;

    public HealthDrop(Texture texture, GameScreen game, Sound bonusSound) {
        super(texture.getWidth()/2, texture.getHeight()/2, 6, texture, game);
        this.bonusSound = bonusSound;
    }

    @Override
    protected void bucketHit() {
        toDie=true;
        game.addHealth();
        bonusSound.play();
    }

    @Override
    public void dispose() {
        super.dispose();
        bonusSound.dispose();
    }
}
