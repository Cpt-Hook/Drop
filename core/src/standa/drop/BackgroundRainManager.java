package standa.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import standa.drop.entitites.drops.RainDrop;

import java.util.Iterator;

public class BackgroundRainManager {

    private Texture raindropTexture;
    private DropGame game;
    private Array<RainDrop> raindrops;
    private double speedMultiplier;
    private int dropsPerTick = 3;

    public BackgroundRainManager(DropGame game) {
        this(game, 1);
    }

    public BackgroundRainManager(DropGame game, double speedMultiplier) {
        this.raindropTexture = new Texture(Gdx.files.internal("sprites/raindrop.png"));
        this.game=game;
        this.speedMultiplier=speedMultiplier;
        raindrops = new Array<>();
    }

    public int getHeight() {
        return game.height;
    }

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer){
        for(RainDrop drop:raindrops){
            drop.draw(batch, shapeRenderer);
        }
    }

    public int getWidth() {
        return game.width;
    }

    public void tick(){
        for (int i = 0; i < 3; i++) {
            createNewRaindrop();
        }
        for(Iterator<RainDrop> iterator = raindrops.iterator(); iterator.hasNext();){
            RainDrop drop = iterator.next();
            drop.tick();
            if(drop.toDie) iterator.remove();
        }
    }

    public void createNewRaindrop(){
        raindrops.add(new RainDrop(raindropTexture, this, speedMultiplier));
    }

    public void dispose(){
        raindropTexture.dispose();
        for(RainDrop drop:raindrops){
            drop.dispose();
        }
    }
}
