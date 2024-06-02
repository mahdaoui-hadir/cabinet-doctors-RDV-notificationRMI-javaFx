package application;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class EcouteurLabel implements EventHandler<MouseEvent> {
Main ma;
	public EcouteurLabel(Main main) {
		// TODO Auto-generated constructor stub
		this.ma=main; 
	}

	@Override
	public void handle(MouseEvent e) {
		// TODO Auto-generated method stub
		Label l_source=(Label)e.getSource();

		if (e.getEventType()==MouseEvent.MOUSE_ENTERED)
		{
		l_source.setTextFill(Paint.valueOf("red"));
	}
		
		if (e.getEventType()==MouseEvent.MOUSE_EXITED)
		{
		l_source.setTextFill(Paint.valueOf("black"));
	}
	}
	

}
