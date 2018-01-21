package standa.drop.screens.gamescreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

class InputHandler extends InputAdapter {

    private GameScreen game;

    public InputHandler(GameScreen game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.P){
            game.switchGameState();
            return true;
        }
        return super.keyUp(keycode);
    }
}
