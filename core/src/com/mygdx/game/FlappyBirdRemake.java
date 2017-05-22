package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.States.EasyState;
import com.mygdx.game.States.HardState;
import com.mygdx.game.States.ImpossState;
import com.mygdx.game.States.InstructState;
import com.mygdx.game.States.MainMenuState;
import com.mygdx.game.States.MediumState;
import com.mygdx.game.States.PlayState;

public class FlappyBirdRemake extends Game {
	MainMenuState mainMenuState; //Instances of the other classes to call.
	PlayState playState;
	InstructState instructState;
	EasyState easyState;
	MediumState mediumState;
	HardState hardState;
	ImpossState impossState;

	public HUD hud; //Instance of the hud.

	public SpriteBatch sbBatch;

	public enum GameState{           //http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
		MENU, PLAY , INSTRUCT,       //http://www.kilobolt.com/day-10-gamestates-and-high-score.html
		EASY, MEDIUM, HARD,          //http://ics3ui.sgrondin.ca/ss23/LibGDX.html
		IMPOSSIBLE                   //Different states.
	}

	public GameState currentState; //Current state.

	public void updateState(){ //Updates to different states based on what the current one is.
		if(currentState==GameState.MENU){
			setScreen(mainMenuState);
		}else if(currentState==GameState.PLAY){
			setScreen(playState);
		}else if(currentState==GameState.INSTRUCT){
			setScreen(instructState);
		}else if(currentState==GameState.EASY){
			setScreen(easyState);
		}else if(currentState==GameState.MEDIUM){
			setScreen(mediumState);
		}else if(currentState==GameState.HARD){
			setScreen(hardState);
		}else if(currentState==GameState.IMPOSSIBLE){
			setScreen(impossState);
		}
	}

	@Override
	public void create () {
		sbBatch = new SpriteBatch();

		mainMenuState = new MainMenuState(this);
		playState = new PlayState(this);
		instructState = new InstructState(this);
		easyState = new EasyState(this);
		mediumState = new MediumState(this);
		hardState = new HardState(this);
		impossState = new ImpossState(this);

		SoundFiles.load(); //Loads the music file in the BackgroundMusic class, and then plays it based on the criteria there.

		currentState = GameState.MENU; //Set the current state to the main menu, and update it.
		updateState();
	}

	//If you include this render, it will display whatever is here, and not the render in the other classes.
	//@Override
	//public void render () {
	//Gdx.gl.glClearColor(1, 0, 0, 1);
	//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	//}
}
