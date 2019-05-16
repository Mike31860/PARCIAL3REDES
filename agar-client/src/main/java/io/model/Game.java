package io.model;

import java.util.ArrayList;

import io.connection.Client;

/**
 * Game
 */
public class Game {

    private ArrayList<Cell> cells;
    private State state;
    private int score;


    public Game(){
        cells=new ArrayList<>();
       
    }

    
    /**
     * @return the cells
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }

    /**
     * @param cells the cells to set
     */
    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    /**
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }

	public State getState() {
		// TODO Auto-generated method stub
		return state;
	}


	public int getScore() {
		// TODO Auto-generated method stub
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}



	
}