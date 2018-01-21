package standa.drop.entitites.drops;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import standa.drop.entitites.GameEntity;
import standa.drop.screens.gamescreen.GameScreen;

public abstract class Drop extends GameEntity{
    public boolean toDie = false;
    private int speed;

    protected Drop(int width, int height, int speed, Texture texture, GameScreen game) {
        super(MathUtils.random(0, game.width - width), game.height, width, height, texture, game);
        this.speed=speed;
    }

    @Override
    public void tick() {
        rect.y-=speed;
        super.tick();
        if(hitBox.y<-rect.height){
            goneOffScreen();
        }
        else if(game.bucket.hitBox.overlaps(rect)){
            bucketHit();
        }
    }

    protected abstract void bucketHit();
    protected void goneOffScreen(){
        toDie = true;
    }
}
