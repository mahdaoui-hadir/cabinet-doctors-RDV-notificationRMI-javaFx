package application;


import java.io.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MyVbox extends VBox{
	ComboBox<String> comb1;
	Label dif,opt,choix;
	CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11;
	HBox hb1,hb2;
	VBox v;
	Button btn_valider ;
	int nbop,a;
	String aa,bb;

	public MyVbox(String nom,String prenom,String pseudo){
		
		Label lb_titre = new Label("Bienvenue "+nom+" "+prenom);
		lb_titre.setFont(new Font("Arial",30));
		lb_titre.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
		dif=new Label("Difficulté:");
		choix=new Label("Choisir la/les catégorie(s):");
			comb1 = new ComboBox<String> ();
			comb1.getItems().add("Facile");
			comb1.getItems().add("Intermediaire");
			comb1.getItems().add("Difficile");
			comb1.getSelectionModel().select(1);
			v=new VBox();
			v.getChildren().add(comb1);
			v.setAlignment(Pos.CENTER);
			c1 = new CheckBox("1");
			c2 = new CheckBox("2");
			c3 = new CheckBox("3");
			c4 = new CheckBox("4");
			c5 = new CheckBox("5");
			c6 = new CheckBox("6");
			c7 = new CheckBox("7"); 
			hb1 = new HBox();
			hb1.getChildren().addAll(choix,c1,c2,c3,c4,c5,c6,c7);
			
			nbop = 0;
			opt = new Label("Option: "+nbop);
			c8 = new CheckBox("Emettre son ");
			c9 = new CheckBox("Afficher score");
			c10 = new CheckBox("Plein ecran");
			c11= new CheckBox("Ombre");
			hb2 = new HBox();
			hb2.getChildren().addAll(c8,c9,c10,c11);
			for (Node node : hb2.getChildren()) {
			    if (node instanceof CheckBox) {
			        CheckBox checkbox = (CheckBox) node;
			        checkbox.setOnMouseClicked(e->{
			        	if (checkbox.isSelected()) {
							nbop++; 
							opt.setText("Option:"+nbop);
						}else {
							nbop--;
							opt.setText("Option:"+nbop);
				}
				
				
			});
			        	
			        
	   }     
			    }
			  
			a =0;
			bb="";
			for (Node node : hb1.getChildren()) {
			    if (node instanceof CheckBox) {
			        CheckBox checkbox = (CheckBox) node;
			        checkbox.setOnMouseClicked(e->{
			        	if (checkbox.isSelected()) { 
							a++; 
							aa=" <dd>Categorie "+hb1.getChildren().indexOf(node)+"</dd>"; 
						
						
				}else{aa="";}
				
				
			});
			        	
			        
	   }  bb=bb+aa;   
			    }
			btn_valider = new Button("Valider");
			btn_valider.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent actionEvent) {
					File f = new File("C:\\Users\\mahda\\OneDrive\\Bureau\\1ere ing\\sem2\\java tp\\projet\\config.html");
					
					try { 
						FileWriter fw = new FileWriter(f,false);
						fw.write("<html> <title>config</title>"
								+ "<style>"
									+ "ol {font-size: 22px;}"
									+ "</style> <body> "
									+ "<dl>"
									+ "<ol>Information personnelle</ol>"
									+"<dd>Nom : "+nom+"</dd>"		
									+"<dd>Prenom : "+prenom+"</dd>"
									+"<dd>Pseudo : "+pseudo+"</dd>" 
									+"<ol> Configuration</li> </ol>"
									+"<dt> Dificulte : "+comb1.getValue()+"</dt>" 
									+bb
									
									+"<dt> Option : </dt>"
									+"<dd>Son : "+c8.isSelected()+"</dd>"
									+"<dd>Affichage score : "+c9.isSelected()+"</dd>"
									+"<dd>Plein ecran :"+c10.isSelected()+"</dd>"
									+"<dd>Ombre : "+c11.isSelected()+"</dd>"
									+ "</dl>"
									+ " </body> </html> ");
						
						fw.close();
					} catch ( IOException e) {
						throw new RuntimeException(e);
					}
					
				}
			});
		this.getChildren().addAll(lb_titre,dif,v,hb1,opt,hb2,btn_valider);
		
		
	}
}
