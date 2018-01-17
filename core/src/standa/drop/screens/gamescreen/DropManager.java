package standa.drop.screens.gamescreen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import standa.drop.entitites.drops.Drop;

import java.util.Iterator;

public class DropManager {

    private Array<Drop> drops;
    private final GameScreen game;
    private Texture texture;
    private Sound dropSound, gameOverSound;

    public DropManager(GameScreen game, Texture texture, Sound dropSound, Sound gameOverSound){
        this.game=game;
        this.texture=texture;
        this.dropSound=dropSound;
        this.gameOverSound=gameOverSound;

        drops = new Array<>(20);
    }

    public void createNewDrop(){
        drops.add(new Drop(MathUtils.random(0, game.width - texture.getWidth()), game.height,
                texture.getWidth(), texture.getHeight(), texture, game, dropSound, gameOverSound));
    }

    public void tick(){
        if (game.tickCount % 45 == 0) createNewDrop();
        for(Iterator<Drop> iterator = drops.iterator(); iterator.hasNext();){
            Drop drop = iterator.next();
            drop.tick();
            if(drop.toDie) iterator.remove();
        }
    }

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer){
        for (Drop drop : drops) {
            drop.draw(batch, shapeRenderer);
        }
    }

    public void dispose(){
        for (Drop drop : drops) {
            drop.dispose();
        }
        texture.dispose();
        gameOverSound.dispose();
        dropSound.dispose();
    }
}
