package standa.drop.entitites.drops;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import standa.drop.entitites.GameEntity;
import standa.drop.screens.gamescreen.GameScreen;

public class Drop extends GameEntity {

    private int speed;
    public boolean toDie = false;
    private Sound dropSound, gameOverSound;

    public Drop(int x, int y, int width, int height, Texture texture, GameScreen game, Sound dropSound, Sound gameOverSound) {
        super(x, y, width, height, texture, game);
        this.dropSound = dropSound;
        this.gameOverSound = gameOverSound;
        speed = 6;
    }

    @Override
    public void tick() {
        rect.y-=speed;
        if(hitBox.y<-rect.height){
            toDie = true;
            gameOverSound.play();
            game.removeHealth();
        }
        else if(game.bucket.hitBox.overlaps(rect)){
            toDie = true;
            dropSound.play();
            game.addScore();
        }
        super.tick();
    }

    @Override
    public void dispose() {
        super.dispose();
        dropSound.dispose();
    }
}