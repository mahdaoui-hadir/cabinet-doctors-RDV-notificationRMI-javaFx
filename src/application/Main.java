package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;


public class Main extends Application {
	HBox hbtop,hbLogin;
	VBox vbError,vbLogin;
	SplitPane sp;
	ObservableList<Profil> liste_pseudo; 
	ListView lv; 
	TabPane tp;
	Label nom,prenom,pseudo,Reqlabel,help,lmtp;
	TextField n,p,psd,mtp;
	Button v1,v2,btn_login;
	Popup pop = new Popup();

	
	public void start(Stage primaryStage) {
		try {
			//////////top////////////
			hbtop=new HBox(10);
			hbtop.setAlignment(Pos.CENTER);
			nom = new Label("Nom"); 
			prenom = new Label("Prenom");
			pseudo= new Label("Pseudo");
			v1=new Button();
			v1.setText("valider");
			n=new TextField();
			p=new TextField();
			psd=new TextField();
			n.setPromptText("Tapper votre nom");
			p.setPromptText("Tapper votre prenom");
			psd.setPromptText("Tapper votre pseudo");
			v1.setOnAction(new EventHandler<ActionEvent>() {
		
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (n.getText()!="" && p.getText()!="" && psd.getText()!="") {
					Profil pr = new Profil (n.getText(),p.getText(),psd.getText());
					liste_pseudo.add(pr);
					n.setText("");
					p.setText("");
					psd.setText("");
					}
					else {
						Reqlabel = new Label("Vous devez remplir tous les champs !!");
						Reqlabel.setFont(new Font("Roboto",12));
						Reqlabel.setTextFill(Paint.valueOf("red"));
						vbError = new VBox();
						vbError.setAlignment(Pos.CENTER);
						vbError.getChildren().add(Reqlabel);
						Scene Error = new Scene(vbError,250,100);
						Stage At= new Stage();
						At.setScene(Error);
						At.setTitle("Attention");
						At.show();
					}
				}
			});
			hbtop.getChildren().addAll(nom,n,prenom,p,pseudo,psd,v1);
			//////////End top////////////
			
			/////////Center/////////////
			liste_pseudo= FXCollections.observableArrayList();
			lv=new ListView<>(liste_pseudo);
			tp=new TabPane();
			//tp.getTabs().add(new Tab("title",new VBox()));
			sp=new SplitPane(lv,tp);
			/////////End Center/////////////
			
			
			//////////Bottom////////////
				help=new Label("Help:");
			//////////End Bottom////////////
				
				
			//////////Main////////////
		    lmtp = new Label("mot de passe :");
			mtp = new TextField();
			mtp.setPromptText("Tapper votre mot de passe");
			btn_login=new Button("valider");
			hbLogin=new HBox(10);
			hbLogin.setAlignment(Pos.CENTER);
			hbLogin.getChildren().addAll(lmtp,mtp,btn_login);
			vbLogin = new VBox();
			vbLogin.getChildren().add(hbLogin);
			Reqlabel = new Label("mot de passe incorrect!");
			Reqlabel.setFont(new Font("Roboto",12));
			Reqlabel.setTextFill(Paint.valueOf("red"));
			vbLogin.getChildren().add(Reqlabel);
			vbLogin.setAlignment(Pos.CENTER); 
			Reqlabel.setVisible(false);
			Scene Login = new Scene(vbLogin,350,100);
			primaryStage.setScene(Login);	
			primaryStage.setTitle("Login");
			primaryStage.show();
			btn_login.setOnAction(new EventHandler<ActionEvent>() {
		
				@Override 
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub 
					if (mtp.getText().compareTo("issatso")==0) {	
						primaryStage.close();
						BorderPane bp = new BorderPane();
						bp.setTop(hbtop);
						bp.setCenter(sp);
						bp.setBottom(help);
						Scene scene = new Scene(bp,700,600);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						Stage mainStage = new Stage();
						mainStage.setScene(scene);
						mainStage.show();
						
						
					}
					else {
						
						Reqlabel.setVisible(true);
					}
				}
			});
		
			//////////End Main////////////
			
			
			//partie event Labels
			
			nom.setOnMouseEntered(new EcouteurLabel(this));			
			prenom.setOnMouseEntered(new EcouteurLabel(this));			
			pseudo.setOnMouseEntered(new EcouteurLabel(this));			
			nom.setOnMouseExited(new EcouteurLabel(this));			
			prenom.setOnMouseExited(new EcouteurLabel(this));			
			pseudo.setOnMouseExited(new EcouteurLabel(this));
			EcouteurListeView lve = new EcouteurListeView();
			lv.setOnMouseClicked(lve);
			//ListView<Object> s=(ListView<Object>) lv.getSelectionModel().getSelectedItem();
			//s.setOnMouseEntered(lve);
			
		
			//partie event textFields
			Ecouteurtextfield ne=new Ecouteurtextfield(this,"saisir votre nom"); 
			n.setOnMouseEntered(ne);
			Ecouteurtextfield pe=new Ecouteurtextfield(this,"saisir votre prenom");
			p.setOnMouseEntered(pe);
			Ecouteurtextfield psde=new Ecouteurtextfield(this,"saisir votre pseudo");
			psd.setOnMouseEntered(psde);
			n.setOnMouseExited(event -> ne.hide());
			p.setOnMouseExited(event -> pe.hide());
			psd.setOnMouseExited(event -> psde.hide());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	class EcouteurListeView implements EventHandler<MouseEvent>{
		Tooltip nomTooltip;
		@Override
		public void handle(MouseEvent e) {
			Point2D point = lv.localToScreen(0,0);
			// TODO Auto-generated method stub
			if (e.getClickCount()==2 && e.getButton()==MouseButton.PRIMARY)
			{
				if (lv.getSelectionModel().getSelectedItem() != null) {
					Profil l = (Profil)lv.getSelectionModel().getSelectedItem();
				tp.getTabs().add(new Tab (l.pseudo,new MyVbox(l.nom,l.prenom,l.pseudo)));
			}}
			if (e.getButton()==MouseButton.SECONDARY)
			{	
				pop.setAutoHide(true);
				Button btn_sup = new Button("supprimer"); 
				Button btn_sup_tt = new Button("supprimer tous"); 
				Button btn_reno = new Button("renommer"); 
				VBox vb_popup = new  VBox();
				
				btn_sup.setOnAction(event->{
					if (lv.getSelectionModel().getSelectedItem() != null) {
						liste_pseudo.remove(lv.getSelectionModel().getSelectedItem());
				}
				});
				
				btn_sup_tt.setOnAction(event->{
						liste_pseudo.clear();
				});
				
				btn_reno.setOnAction(event->{
					if (lv.getSelectionModel().getSelectedItem() != null) {
						Profil l = (Profil)lv.getSelectionModel().getSelectedItem();
						n.setText(l.nom);
						p.setText(l.prenom);
						psd.setText(l.pseudo);
						//Profil l2 = new Profil("sqdsq", "sd", "sqd");
						liste_pseudo.remove(lv.getSelectionModel().getSelectedItem());
				}
				});
				
				vb_popup.getChildren().addAll(btn_sup,btn_sup_tt,btn_reno);
				pop.getContent().add(vb_popup);
				if(!liste_pseudo.isEmpty()) {
				pop.show(lv,point.getX()+e.getX(),point.getY()+e.getY());
				}
			}
			if (e.getClickCount()==1 && e.getButton()==MouseButton.PRIMARY)
			{
				pop.hide();
			}
			}
			
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
