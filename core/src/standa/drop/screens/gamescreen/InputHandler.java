package standa.drop.screens.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

class InputHandler extends InputAdapter {

    private GameScreen game;

    public InputHandler(GameScreen game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            if(game.state == GameScreen.GameState.PAUSED){
                Gdx.app.exit();
            }else{
                game.state = GameScreen.GameState.PAUSED;
            }
            return true;
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.P && game.state == GameScreen.GameState.RUNNING){
            game.switchGameState();
            return true;
        }
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(game.state == GameScreen.GameState.PAUSED){
            game.switchGameState();
            return true;
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
