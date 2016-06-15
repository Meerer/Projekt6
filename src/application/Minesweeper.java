package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Minesweeper {
	private Pane rootPane;
	private Random randomizer = new Random();
	private ArrayList<MineButton> g = new ArrayList<MineButton>();
	private int mines;
	private int flags;
	Label Flags;
	Boolean gameOver = false;
	public Minesweeper(Pane rootPane, int n, int mines, Label Flags)
	{
		this.rootPane = rootPane;
		this.mines = mines;
		flags = mines;
		this.Flags = Flags;
		Flags.setText(""+flags);
		createField(n);
		setNeighbors();
		rootPane.getChildren().addAll(g);
		//waitForStart();
		start(n);
		
	}
	private void waitForStart()
	{
		while(checkEachButton() == false)
		{
			
		}
		
	}
	private void start(int n)
	{
		int toCheckX = 0;
		int toCheckY = 0;
		for(int i=0; i<mines ; i++)
		{
			 toCheckX = randomizer.nextInt(n)+0;
			 toCheckY = randomizer.nextInt(n)+0;
			for(MineButton b : g)
			{
				if(b.getX() == toCheckX && b.getY() == toCheckY)
				{
					if(b.isBomb() == false)b.bombCreate(true);
					else{
					//	i--;
					}
				}
				
			}
		}
	}
	private Boolean checkEachButton()
	{
		for(MineButton b: g)
		{
			if(b.isStarted()== true)return true;
		}
		return false;
	}

	private void createField(int n)
	{
		for(int j=0; j<n; j++)
		{
			for(int i=0; i<n; i++)
			{
				
				g.add(new MineButton(j,i));

			}
		}
		setOnAction();
	}
	private void setNeighbors()
	{
		for(MineButton c : g)
		{
			
			addNeighborsFor(c);
		}
	}
	private void addNeighborsFor(MineButton b)
	{
		for(MineButton m:g)
		{
			if(b.getX()== m.getX() || b.getX()+1 == m.getX()
					|| b.getX()-1 == m.getX() )
			{
				if(b.getY()== m.getY() || b.getY()+1 == m.getY()
						|| b.getY()-1 == m.getY() )
				{
					b.addNeighbor(m);
				}
			}
		}
	}
	private void setOnAction()
	{
		for(MineButton b: g)
		{
			/*b.setOnAction(event -> {
				b.onClick();
			});*/
		    b.setOnMousePressed(new EventHandler<MouseEvent>() {
		        @Override
		        public void handle(MouseEvent t) {
		            if (t.isPrimaryButtonDown()) {
		                b.onClick();
		                if(checkWinCondition ()== true)
		                	{
		                	System.out.println("YOU WON");
		                	gameOver = true;
		                	
		                	}
		            }
		            if (t.isSecondaryButtonDown()) {
		            	Boolean flagAlreadySet = false;
		               //if(b.onRightClick() == false)flags++;
		               if(flags > 0)
		               {
		            	   if(b.onRightClick() == true )
		            		   {
		            		   		flags--;
		            		   		Flags.setText(""+flags);
		            		   		flagAlreadySet = true;
		            		   }
		            	   else
		            		   {
		            		   		flags++;
		            		   		Flags.setText(""+flags);
		            		   }
		               }
		               
		               if(flags == 0)
		               {
		            	   if(checkWinCondition() == true)
		            	   {
		            		   System.out.println("YOU WON");
		            	   }
		            	   if(flagAlreadySet == false)
		            	   {
			            	   if(b.getColorChanged() == true)
			            		   {
			            		   		
			            		   		b.onRightClick();
			            		   		flags++;
			            		   		Flags.setText(""+flags);
			            		   }
		            	   }
		               }
		               flagAlreadySet = false;
		            }
		        }
		    });
		}
	}
	private Boolean checkWinCondition()
	{
		for(MineButton b : g)
		{
			if(b.isUncovered() == false)return false;
		}
		return true;
	}
}
