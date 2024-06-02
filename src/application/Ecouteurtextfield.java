package application;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;


public class Ecouteurtextfield implements EventHandler<MouseEvent> {
Main ma;
String mot;
Tooltip nomTooltip;
	public Ecouteurtextfield(Main main,String mot) {
		// TODO Auto-generated constructor stub
		this.ma=main; 
		
		nomTooltip = new Tooltip(mot);
	}
	
	
	@Override
	public void handle(MouseEvent e) {
		// TODO Auto-generated method stub
		TextField tf_source=(TextField)e.getSource();
		Point2D point = tf_source.localToScreen(0,0);
		if (e.getEventType()==MouseEvent.MOUSE_ENTERED)
		{	
	    

			        nomTooltip.show(tf_source,point.getX()+10,point.getY()+20);
			    
	    
		}}
		
	
		public void hide( ) {
			
				        nomTooltip.hide();
				    
		    
			
		
	}
	

	
}