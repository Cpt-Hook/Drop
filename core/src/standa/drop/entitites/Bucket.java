package standa.drop.entitites;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import standa.drop.Utils;
import standa.drop.screens.gamescreen.GameScreen;

public class Bucket extends GameEntity {

    private final Sound endOfBonusSound;
    private float maxSpeed = game.width/60;
    private float speedMultiplier = 1;
    private long doubleSpeedTimer;
    private boolean endOfBonusPlayed = true;

    public Bucket(Texture texture, Sound endOfBonusSound, GameScreen game) {
        super(game.width / 2, game.height / 20, texture.getWidth(), texture.getHeight(), texture, game);
        this.endOfBonusSound = endOfBonusSound;
        hitBox.height = rect.height*(3/4f);
    }

    @Override
    public void tick() {
        if(TimeUtils.timeSinceMillis(doubleSpeedTimer) > 0 && !endOfBonusPlayed) {
            System.out.println(TimeUtils.millis());
            speedMultiplier = 1;
            endOfBonusSound.play();
            endOfBonusPlayed=true;
        }
        if(Gdx.app.getType() == Application.ApplicationType.Android && Gdx.input.isPeripheralAvailable(Input.Peripheral.Compass)){
            float speed = (float) Utils.map(Gdx.input.getPitch(), 25,-25, -maxSpeed, maxSpeed, true);
            rect.x+= speed * speedMultiplier;
        }else{
            if(Gdx.input.isKeyPressed(Input.Keys.A)) rect.x-= maxSpeed * speedMultiplier;
            if(Gdx.input.isKeyPressed(Input.Keys.D)) rect.x+= maxSpeed * speedMultiplier;
        }
        if(rect.x < -rect.width) rect.x = game.width;
        if(rect.x > game.width) rect.x = -rect.width;
        super.tick();
    }

    public void doubleSpeed(long timeMillis){
        if(speedMultiplier <= 4){
            doubleSpeedTimer = TimeUtils.millis() + timeMillis;
            speedMultiplier += 1;
            endOfBonusPlayed=false;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        endOfBonusSound.dispose();
    }
}