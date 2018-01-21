package standa.drop.screens.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import standa.drop.entitites.drops.Drop;
import standa.drop.entitites.drops.HealthDrop;
import standa.drop.entitites.drops.NormalDrop;
import standa.drop.entitites.drops.SpeedDrop;

import java.util.Iterator;

class DropManager {

    private Array<Drop> drops;
    private final GameScreen game;
    private Texture normalDropTexture, healthDropTexture, speedDropTexture;
    private Sound dropSound, gameOverSound, bonusSound;

    public DropManager(GameScreen game){
        this.game=game;
        normalDropTexture = new Texture(Gdx.files.internal("sprites/normaldroplet.png"));
        healthDropTexture = new Texture(Gdx.files.internal("sprites/healthdroplet.png"));
        speedDropTexture = new Texture(Gdx.files.internal("sprites/speeddroplet.png"));
        dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/dropSound.wav"));
        bonusSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bonus.wav"));
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gameover.wav"));

        drops = new Array<>(20);
    }

    public void createNewDrop(){
        int random = MathUtils.random(100);
        if(random >= 0 && random < 10){
            drops.add(new HealthDrop(healthDropTexture, game, bonusSound));
        }else if(random >= 10 && random < 20){
            drops.add(new SpeedDrop(speedDropTexture, game, bonusSound));
        }else{
            drops.add(new NormalDrop(normalDropTexture, game, dropSound, gameOverSound));
        }
    }

    public void tick(){
        if (game.tickCount % 30 == 0) createNewDrop();
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
        normalDropTexture.dispose();
        gameOverSound.dispose();
        dropSound.dispose();
        bonusSound.dispose();
    }
}
