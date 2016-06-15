package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class MineButton extends Button{
	private Boolean isClicked = false;
	private Boolean isBomb = false;
	private Boolean bombWasSet = false;
	private ArrayList<MineButton> Neighbors = new ArrayList<MineButton>();
	private int j;
	private int i;
	private Boolean uncovered = false;
	private Boolean colorChanged = false;
	private Boolean lost = false;
	private Background defaultBackground;
	
	
	
	public MineButton(int j,int i)
	{	
		
		super();
		
		this.j = j;
		this.i = i;
		this.textProperty().set("");
		this.setLayoutX(j*17+240);
		this.setLayoutY(i*21+140);
		defaultBackground = this.getBackground();
	}
	public void bombCreate(Boolean setter)
	{
		
		isBomb = setter;
		bombWasSet = true;
	}
	public Boolean isBomb()
	{
		return isBomb;
		
	}
	public void addNeighbor(MineButton b)
	{
		Neighbors.add(b);
	}
	public int getX()
	{
		return j;
	}
	public int getY()
	{
		return i;
	}
	public void onClick()
	{
		
		if(bombWasSet == false)
		{
			isBomb = false;
			bombWasSet = true;
			isClicked = true;
		}
		else if(isBomb == true && uncovered == false)
		{
			lost = true;
		}
		uncoverNeighbors();
	}
	public Boolean isStarted()
	{
		if(isClicked == true)return true;
		return false;
	}
	private void uncoverNeighbors()
	{
		if(uncovered == false)
		{
			if(countNeighborBombs() > 0 ){
				setLabel(countNeighborBombs());
				uncovered = true;
			}
			else{
				setLabel(0);
				uncovered = true;
				for(MineButton b : Neighbors)
				{
				b.uncoverNeighbors();
				}
			}
		}
	}
	public Boolean isUncovered()
	{
		return uncovered;
	}
	public Boolean getColorChanged()
	{
		return colorChanged;
	}
	private void setLabel(int c)
	{
		switch(c){
		case 0: this.textProperty().set("0");
		break;
		case 1: this.textProperty().set("1");
				break;
		case 2: this.textProperty().set("2");
		break;
		case 3: this.textProperty().set("3");
		break;
		case 4: this.textProperty().set("4");
		break;
		case 5: this.textProperty().set("5");
		break;
		case 6: this.textProperty().set("6");
		break;
		case 7: this.textProperty().set("7");
		break;
		case 8: this.textProperty().set("8");
		break;
		
		}
	}
	private int countNeighborBombs()
	{
		int counter = 0;
		for(MineButton b: Neighbors)
		{
			if(b.isBomb())
			{
				counter++;
			}
		}
		return counter;
	}
	public Boolean onRightClick()
	{
		if(colorChanged == false)
		{
			this.setText("F");
			//this.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, CornerRadii.EMPTY, Insets.EMPTY)));
			colorChanged = true;
			uncovered = true;
			return true;
		}
		else{
			this.setText(null);
			//this.setBackground(defaultBackground);
			colorChanged = false;
			uncovered = false;
			return false;
		}
	}
	public void setOnActiont(EventHandler<MouseEvent> eventHandler) {
		// TODO Auto-generated method stub
		
	}
	
}
