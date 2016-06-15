package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class ScoreTimer {
	Label toPrint;
	private Minesweeper ms;
	private int timer = 0;
	public ScoreTimer(Label time, Minesweeper ms){
		
		this.ms = ms;
		run(time);
	}
	public void run(Label toPrint)
	{
		
		Timeline t = new Timeline();
        t.setCycleCount(Timeline.INDEFINITE);
        t.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
        		new EventHandler<ActionEvent>()
        		{
        	@Override
            public void handle(ActionEvent event) {
                timer++;
                toPrint.setText(""+timer);
                if(ms.gameOver == true)t.stop(); 
        	}
            }));
        t.playFromStart();
        
        		
        

	}
	public int getScore()
	{
		return timer;
	}
 
                /*new KeyFrame(Duration.seconds(1),
                  new EventHandler() {
                    // KeyFrame event handler
                    public void handle(ActionEvent event) {
                        timeSeconds--;
                        // update timerLabel
                        timerLabel.setText(
                              timeSeconds.toString());
                        )*/
                
		
	

}
