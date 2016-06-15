package application;



import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class Fenster_Controller {
	
	@FXML
	Pane rootPane;
	@FXML
	Pane menuPane;
	@FXML
	Label Flags;
	@FXML
	Slider difficulty_slider;
	@FXML
	Label Timer;
	@FXML
	Pane gamePane;
	
	@FXML
	public void startGame(ActionEvent event)
	{
		int difficulty = (int) difficulty_slider.getValue();
		menuPane.managedProperty().bind(menuPane.visibleProperty());
		menuPane.setVisible(false);
		gamePane.setVisible(true);
		
		
		switch (difficulty)
		{
		case 0: Minesweeper msE = new Minesweeper(gamePane, 5, 2, Flags);
				ScoreTimer stE = new ScoreTimer(Timer, msE);
		break;
		case 1: Minesweeper msM = new Minesweeper(gamePane, 10, 15, Flags);
				ScoreTimer stM = new ScoreTimer(Timer, msM);
		break;
		case 2: Minesweeper msH = new Minesweeper(gamePane, 20, 90, Flags);
				ScoreTimer stH = new ScoreTimer(Timer, msH);
		break;
		}
		
		
	}

}
