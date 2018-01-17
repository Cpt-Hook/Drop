package standa.drop.entitites;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import standa.drop.Utils;
import standa.drop.screens.gamescreen.GameScreen;

public class Bucket extends GameEntity {

    public Rectangle collisionRect;
    private float maxSpeed = game.width/60;

    public Bucket(int x, int y, int width, int height, Texture texture, GameScreen game) {
        super(x, y, width, height, texture, game);
        collisionRect = new Rectangle(rect);
        collisionRect.height = rect.height*(3/4f);
    }

    @Override
    public void tick() {
        setColRect();
        if(Gdx.app.getType() == Application.ApplicationType.Android && Gdx.input.isPeripheralAvailable(Input.Peripheral.Compass)){
            float speed = (float) Utils.map(Gdx.input.getPitch(), 25,-25, -maxSpeed, maxSpeed, true);
            rect.x+= speed;
        }else{
            if(Gdx.input.isKeyPressed(Input.Keys.A)) rect.x-= maxSpeed;
            if(Gdx.input.isKeyPressed(Input.Keys.D)) rect.x+= maxSpeed;
        }
        if(rect.x < -rect.width) rect.x = game.width;
        if(rect.x > game.width) rect.x = -rect.width;
    }

    private void setColRect(){
        collisionRect.x = rect.x;
        collisionRect.y = rect.y;
    }
}