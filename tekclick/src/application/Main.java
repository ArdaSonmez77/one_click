package application;



import java.util.ArrayList;

import java.util.Random;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;



public class Main extends Application{
	int tur=0;
	int bfr=0;
	int dropNum=0;
	int fill_Box=0;
	double pstX, pstY;
	double en,boy, wayX, wayY;
	double offsetX, offsetY, orgSceneX, orgSceneY, prspointX, prspointY;
	double newTranslateX, newTranslateY, orgTranslateX, orgTranslateY;
	double x, y, width, height;
	double msf, shortest=999999;
	Rectangle rectangle;
	//Stones
	Text label;
	//Middle line
	Line line;
	//Holding numbers
	ArrayList<Text> text;
	//Players Woods
	ArrayList<Text> killer=new ArrayList<>();
	ArrayList<Text> p1;
	ArrayList<Text> p1throw= new ArrayList<>();
	ArrayList<Text> p2;
	ArrayList<Text> p2throw= new ArrayList<>();
	ArrayList<Text> p3;
	ArrayList<Text> p3throw= new ArrayList<>();
	ArrayList<Text> p4;
	ArrayList<Text> p4throw= new ArrayList<>();

	//Dropped numbers
	ArrayList<Rectangle> FinDrp =new ArrayList<>();
	ArrayList<Rectangle> P1_Drop = new ArrayList<>();
	ArrayList<Rectangle> P2_Drop = new ArrayList<>();
	ArrayList<Rectangle> P3_Drop = new ArrayList<>();
	ArrayList<Rectangle> P4_Drop = new ArrayList<>();
	Random rnd = new Random();
	//Dropped place


	ArrayList<Line> liner=new ArrayList<>();




	Paint black=Color.BLACK;
	Paint red=Color.RED;
	Paint gray=Color.DARKGRAY;
	Paint white=Color.WHITE;
	Button btn;
	Button ctr;
	Group root;
	Button nextPlayer;
	Button nxtply;
	Button Finisher;
	Button getSide;
	Button getNew;

	String soundFile_1 = "click_2.mp3";
	String soundFile_2 = "click_3.mp3";
	String soundFile_3 = "turn_1.mp3";
	String soundFile_4 = "start.mp3";
	String soundFile_5 = "Wrong_Answer.wav";
	String soundFile_6 = "Yumruk.wav";
	Media sound_1 = new Media(new File(soundFile_1).toURI().toString());
	Media sound_2 = new Media(new File(soundFile_2).toURI().toString());
	Media sound_3 = new Media(new File(soundFile_3).toURI().toString());
	Media sound_4 = new Media(new File(soundFile_4).toURI().toString());
	Media sound_5 = new Media(new File(soundFile_5).toURI().toString());
	Media sound_6 = new Media(new File(soundFile_6).toURI().toString());
	MediaPlayer mediaPlayer_1 = new MediaPlayer(sound_1);
	MediaPlayer mediaPlayer_2 = new MediaPlayer(sound_2);
	MediaPlayer mediaPlayer_3 = new MediaPlayer(sound_3);
	MediaPlayer mediaPlayer_4 = new MediaPlayer(sound_4);
	MediaPlayer mediaPlayer_5 = new MediaPlayer(sound_5);
	MediaPlayer mediaPlayer_6 = new MediaPlayer(sound_6);

	@Override
	public void start(Stage primaryStage) {

		text=new ArrayList<>();

		root = new Group();

		double xcor=30.0f;
		double ycor=10.0f;
		double slide=71.0f;
		double Width= 40.0f;
		double Height= 50.0f;
		for(int a=1; a<107; a++) {

			if(a%13==0) {
				label =new Text(Integer.toString(13));
			}else if(a/13==8 && a%13!=0) {
				label =new Text(Integer.toString(0));
				label.setFill(black);
			}else {
				label =new Text(Integer.toString(a%13));
			}

			if(a>=1 && a<=26){
				label.setFill(Color.RED);
			}else if(a>=27 && a<=52){
				label.setFill(Color.GOLD);
			}else if(a>=53 && a<=79){
				label.setFill(Color.GREEN);
			}else if(a>=80 && a<=104){
				label.setFill(Color.BLUE);
			}

			label.setId(Integer.toString(a));
			label.setX(xcor+ Width/2);
			label.setY(ycor+ Height/2);


			label.setFont(Font.font("Verdana", 30));
			label.setOnMousePressed(labelpress);
			label.setOnMouseDragged(labeldragg);
			label.setOnMouseReleased(labelout);
			text.add(label);

			if(a%13==0 && a/13>0) {
				xcor=30.0f;
				ycor+=50;
			}else {
				xcor+=slide;
			}
		}
		double recx=230;
		double recy=700;
		en=1200;
		boy=200;
		//wooden circle
		Rectangle Limited1= new Rectangle(200, 700, 1260, 260);
		Limited1.setStroke(black);
		Limited1.setFill(Color.CHARTREUSE);
		//Big Wooden
		Rectangle rectangle1= new Rectangle(recx, recy+30, en, boy);
		rectangle1.setFill(Color.BURLYWOOD);
		rectangle1.setStroke(black);
		Rectangle rectangle2= new Rectangle(recx+1250, recy-610, boy-50, en-500);
		rectangle2.setFill(Color.BURLYWOOD);
		rectangle2.setStroke(black);
		Rectangle rectangle3= new Rectangle(recx, recy-650, en, boy);
		rectangle3.setFill(Color.BURLYWOOD);
		rectangle3.setStroke(black);
		Rectangle rectangle4= new Rectangle(recx-200, recy-610, boy-50, en-500);
		rectangle4.setFill(Color.BURLYWOOD);
		rectangle4.setStroke(black);
		//p3 number dropped
		for(int c=1; c<=3; c++ ) {
			Rectangle P3_Stones =new Rectangle(recx, 260,80, 100);
			P3_Stones.setFill(white);
			P3_Drop.add(P3_Stones);
			recx+=90;
			root.getChildren().add(P3_Stones);
		}
		recx=230;
		//p4 number dropped
		for(int c=1; c<=3; c++ ) {
			Rectangle P4_Stones =new Rectangle(recx, 560,80, 100);
			P4_Stones.setFill(white);
			P4_Drop.add(P4_Stones);

			recx+=90;
			root.getChildren().add(P4_Stones);
		}

		Rectangle Limited3= new Rectangle(recx+160, 540, 300, 140);
		Limited3.setFill(Color.CHARTREUSE);
		Limited3.setStroke(black);
		root.getChildren().add(Limited3);
		for(int c=1; c<=3; c++) {
			Rectangle Fin_Stones =new Rectangle(recx+180, 560,80, 100);
			Fin_Stones.setFill(white);
			FinDrp.add(Fin_Stones);

			recx+=90;
			root.getChildren().add(Fin_Stones);
		}

		recx=230;
		//p1 number dropped
		Rectangle Limited2= new Rectangle(recx+880, 540, 300, 140);
		Limited2.setFill(Color.CHARTREUSE);
		Limited2.setStroke(black);
		root.getChildren().add(Limited2);
		for(int c=1; c<=3; c++ ) {
			Rectangle P1_Stones =new Rectangle(recx+900, 560,80, 100);
			P1_Stones.setFill(white);
			P1_Drop.add(P1_Stones);
			recx+=90;
			root.getChildren().add(P1_Stones);
		}
		recx=230;
		//p2 number dropped
		for(int c=1; c<=3; c++ ) {
			Rectangle P2_Stones =new Rectangle(recx+900, 260,80, 100);
			P2_Stones.setFill(white);
			P2_Drop.add(P2_Stones);
			recx+=90;

			root.getChildren().add(P2_Stones);
		}

		recx=230;
		line= new Line(recx,(recy+boy/2)+30,recx+en,(recy+boy/2)+30);

		root.getChildren().addAll(Limited1,rectangle1,rectangle2,rectangle3,rectangle4, line);
		for(int l=1; l<=30; l++) {

			line= new Line();
			if(l!=1 && l%3==1) {
				line.setFill(Color.CORAL);
			}
			//1.line
			if(l%16==0 && l/16!=0) {
				recx=230;
				recy+=100;
				line.setStartX(recx);
				line.setStartY(recy+30);
				line.setEndX(recx);
				line.setEndY(recy+130);

			}else {
				line.setStartX(recx);
				line.setStartY(recy+30);
				line.setEndX(recx);
				line.setEndY(recy+130);
			}
			int id=l-1;
			line.setId(Integer.toString(id));
			liner.add(line);
			root.getChildren().add(line);
			recx+=80;
		}

		btn = new Button("Let's Start");
		btn.setOnAction(event);
		btn.setLayoutX(750);
		btn.setLayoutY(350);
		btn.setPrefWidth(100);
		btn.setPrefHeight(100);
		root.getChildren().add(btn);




		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(root, 1700,1000, Color.CHARTREUSE));

		primaryStage.setTitle("This Game real Name is= 183 game");
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
	EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e)
		{

			mediaPlayer_4.play();

			root.getChildren().remove(btn);
			p1= new ArrayList<>();
			p2= new ArrayList<>();
			p3= new ArrayList<>();
			p4= new ArrayList<>();


			for(int b=0; b<=70; b++) {
				int stone= rnd.nextInt(text.size());
				if(b<=19) {
					text.get(stone).setX(liner.get(b).getStartX()+30 );
					text.get(stone).setY(liner.get(b).getStartY()+40 );


					liner.get(b).setStroke(red);

					p1.add(text.get(stone));
					root.getChildren().add(p1.get(b));



				}
				else if(20<=b && b<=36) {
					p2.add(text.get(stone));
				}else if(37<=b && b<=53) {
					p3.add(text.get(stone));
				} else if(54<=b) {
					p4.add(text.get(stone));
				}

				text.remove(stone);
			}
			System.out.println("p1");
			for(Text sorun: p1) {
				System.out.println(sorun.getText());
			}
			System.out.println("p2=");
			for(Text sorun: p2) {
				System.out.println(sorun.getText());
			}
			System.out.println("p3");
			for(Text sorun: p3) {
				System.out.println(sorun.getText());
			}
			System.out.println("p4");
			for(Text sorun: p4) {
				System.out.println(sorun.getText());
			}


		}
	};
	EventHandler<MouseEvent> labelpress =
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {

			mediaPlayer_1.stop();
			mediaPlayer_1.play();

			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			orgTranslateX = ((Text)(t.getSource())).getTranslateX();
			orgTranslateY = ((Text)(t.getSource())).getTranslateY();
			//System.out.println(orgSceneX+" "+orgSceneY);
			for(int ln=0; ln<liner.size(); ln++) {

				wayX=orgSceneX-liner.get(ln).getStartX();
				wayY=orgSceneY-liner.get(ln).getStartY();
				msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2));

				if((wayX>=0 && wayX<=80) && (wayY>=0 && wayY<=100)) {

					bfr=ln;

					break;

				}else if(msf<=shortest) {
					shortest=msf;
					bfr=ln;
				}
			}
		}
	};

	EventHandler<MouseEvent> labeldragg =
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {

			offsetX = t.getSceneX() - orgSceneX;
			offsetY = t.getSceneY() - orgSceneY;
			newTranslateX = orgTranslateX + offsetX;
			newTranslateY = orgTranslateY + offsetY;

			((Text)(t.getSource())).setTranslateX(newTranslateX);
			((Text)(t.getSource())).setTranslateY(newTranslateY);


		}
	};

	EventHandler<MouseEvent> labelout =
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {



			 prspointX=t.getSceneX();
			 prspointY=t.getSceneY();
			double ne=5;

			int shrt = 0;

			double shortest=999999;
			msf=0;
			MouseButton button = t.getButton();
			if(button==MouseButton.PRIMARY){
			
				System.out.println(prspointX+" "+prspointY);
				//Istaka alani
				if((prspointX>200 && prspointX<1460)&& (prspointY>700 && prspointY<960)) {
					System.out.println("Istaka ici");
					for(int ln=0; ln<liner.size(); ln++) {

						wayX=prspointX-liner.get(ln).getStartX();
						wayY=prspointY-liner.get(ln).getStartY();
						msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2));

						if((wayX>=0 && wayX<=80) && (wayY>=0 && wayY<=100)) {
							pstX=liner.get(ln).getStartX()-newTranslateX;
							pstY=liner.get(ln).getStartY()-newTranslateY;
							if(liner.get(ln).getStroke().equals(red)) {
								pstX=liner.get(bfr).getStartX()-newTranslateX;
								pstY=liner.get(bfr).getStartY()-newTranslateY;
								ne=0;

								break;
							}else {
								//System.out.println("here");
								((Text)(t.getSource())).setX((pstX +30));
								((Text)(t.getSource())).setY((pstY+ 50));

								ne=1;
								shrt=ln;

								break;
							}
						}else if(msf<=shortest) {
							shortest=msf;
							pstX=liner.get(ln).getStartX()-newTranslateX;
							pstY=liner.get(ln).getStartY()-newTranslateY;

							if(liner.get(ln).getStroke().equals(red)) {
								//System.out.println("some problems");
								pstX=liner.get(bfr).getStartX()-newTranslateX;
								pstY=liner.get(bfr).getStartY()-newTranslateY;
								ne=0;
							}else {
								shrt=ln;
								ne=-1;
							}
						}
					}
					((Text)(t.getSource())).setX((pstX+30));
					((Text)(t.getSource())).setY((pstY+50));
			
					if(ne==1) {
						System.out.println("Go to new box");
						liner.get(shrt).setStroke(red);
						liner.get(bfr).setStroke(black);
						mediaPlayer_2.stop();
						mediaPlayer_2.play();
					}else if(ne==-1) {
						System.out.println("came to long way");
						liner.get(shrt).setStroke(red);
						liner.get(bfr).setStroke(black);
						mediaPlayer_2.stop();
						mediaPlayer_2.play();

					}else if(ne==0) {
						System.out.println("turn same box");
						mediaPlayer_5.stop();
						mediaPlayer_5.play();
					}else {
						System.out.println("big problem");
					}
					//Tas droplama alani
				}else if((prspointX>1110 && prspointX<1410) && (prspointY>540 && prspointY<680)) {
					System.out.println("Tas droplama");
					mediaPlayer_2.stop();
					mediaPlayer_2.play();
					for(int ln=0; ln<P1_Drop.size(); ln++) {
						wayX=prspointX-P1_Drop.get(ln).getX();
						wayY=prspointY-P1_Drop.get(ln).getY();
						msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2));
						if((wayX>=0 && wayX<=80) && (wayY>=0 && wayY<=100)) {
							pstX=P1_Drop.get(ln).getX()-newTranslateX;
							pstY=P1_Drop.get(ln).getY()-newTranslateY;
							if(P1_Drop.get(ln).getFill().equals(gray)) {
								pstX=liner.get(bfr).getStartX()-newTranslateX;
								pstY=liner.get(bfr).getStartY()-newTranslateY;
							}else {
								//System.out.println("here");
								((Text)(t.getSource())).setX((pstX +30));
								((Text)(t.getSource())).setY((pstY+ 40));

								ne=1;
								shrt=ln;

								break;
							}
						}else if(msf<=shortest) {
							shortest=msf;
							pstX=P1_Drop.get(ln).getX()-newTranslateX;
							pstY=P1_Drop.get(ln).getY()-newTranslateY;

							if(P1_Drop.get(ln).getFill().equals(gray)) {
								pstX=liner.get(bfr).getStartX()-newTranslateX;
								pstY=liner.get(bfr).getStartY()-newTranslateY;
								ne=0;
							}else {
								shrt=ln;
								ne=-1;
							}
						}
					}

					((Text)(t.getSource())).setX((pstX+30));
					((Text)(t.getSource())).setY((pstY+40));
					P1_Drop.get(shrt).setFill(gray);
					liner.get(bfr).setStroke(black);
					fill_Box++;

					if(fill_Box==3) {
						nextPlayer=new Button("Next player");
						nextPlayer.setOnAction(destro);
						nextPlayer.setLayoutX(730);
						nextPlayer.setLayoutY(300);
						nextPlayer.setPrefWidth(200);
						nextPlayer.setPrefHeight(100);
						root.getChildren().add(nextPlayer);
					}
					//tur control
					p1throw.add(((Text)(t.getSource())));
					p1.remove(((Text)(t.getSource())));

				}else if((prspointX>650 && prspointX<990) && (prspointY>540 && prspointY<680)) {
					System.out.println("Final Drop");

					//System.out.println("MIDDLE button clicked");
					mediaPlayer_2.stop();
					mediaPlayer_2.play();
					for(int ln=0; ln<FinDrp.size(); ln++) {
						wayX=prspointX-FinDrp.get(ln).getX();
						wayY=prspointY-FinDrp.get(ln).getY();
						msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2));
						if((wayX>=0 && wayX<=80) && (wayY>=0 && wayY<=100)) {
							pstX=FinDrp.get(ln).getX()-newTranslateX;
							pstY=FinDrp.get(ln).getY()-newTranslateY;
							if(P1_Drop.get(ln).getFill().equals(gray)) {
								pstX=liner.get(bfr).getStartX()-newTranslateX;
								pstY=liner.get(bfr).getStartY()-newTranslateY;
							}else {
								//System.out.println("here");
								((Text)(t.getSource())).setX((pstX +30));
								((Text)(t.getSource())).setY((pstY+ 40));
								

								ne=1;
								shrt=ln;

								break;
							}
						}else if(msf<=shortest) {
							shortest=msf;
							pstX=FinDrp.get(ln).getX()-newTranslateX;
							pstY=FinDrp.get(ln).getY()-newTranslateY;

							if(FinDrp.get(ln).getFill().equals(gray)) {
								pstX=liner.get(bfr).getStartX()-newTranslateX;
								pstY=liner.get(bfr).getStartY()-newTranslateY;
								ne=0;
							}else {
								shrt=ln;
								ne=-1;
							}
						}
					}

					((Text)(t.getSource())).setX((pstX+30));
					((Text)(t.getSource())).setY((pstY+40));
					
					FinDrp.get(shrt).setFill(gray);
					liner.get(bfr).setStroke(black);
					fill_Box++;

					if(fill_Box==3) {
						Finisher=new Button("Finisher");
						Finisher.setOnAction(bitirin);
						Finisher.setLayoutX(730);
						Finisher.setLayoutY(300);
						Finisher.setPrefWidth(200);
						Finisher.setPrefHeight(100);
						root.getChildren().add(Finisher);
					}
					//tur control
					//p1throw.add(((Text)(t.getSource())));
					p1.remove(((Text)(t.getSource())));
				}
			}
		}
	};
	EventHandler<ActionEvent> destro = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e)
		{
			mediaPlayer_3.stop();
			mediaPlayer_3.play();
			root.getChildren().remove(nextPlayer);
			System.out.println("P2 Turn");
			/*	LocalTime str1=LocalTime.now();
	    	   while(77==77) {
	    		   LocalTime fin1=LocalTime.now();
	    		   Long diff=ChronoUnit.SECONDS.between(str1,fin1);
	    		   if(diff>=3) {
	    			   break;
	    		   }
	    	   }*/

			p3throw.clear();
			cotacter(p1throw, p2, p2throw, P2_Drop, P1_Drop);
			p4throw.clear();
			StoneNums(text);
			System.out.println("P3 Turn");
			cotacter(p2throw, p3, p3throw, P3_Drop, P2_Drop);
			p1throw.clear();
			StoneNums(text);
			System.out.println("P4 Turn");
			cotacter(p3throw, p4, p4throw, P4_Drop, P3_Drop);
			p2throw.clear();
			StoneNums(text);
			fill_Box=0;
			tur++;
			if(tur>0) {
				getSide= new Button("get Dropped one");
				getSide.setOnAction(getter);
				getSide.setLayoutX(600);
				getSide.setLayoutY(300);
				getSide.setPrefWidth(200);
				getSide.setPrefHeight(100);
				root.getChildren().add(getSide);
				getNew= new Button("get New ones");
				getNew.setOnAction(newer);
				getNew.setLayoutX(830);
				getNew.setLayoutY(300);
				getNew.setPrefWidth(200);
				getNew.setPrefHeight(100);
				root.getChildren().add(getNew);
				mediaPlayer_6.stop();
				mediaPlayer_6.play();
			}
		}
		private void StoneNums(ArrayList<Text> text) {
			System.out.println("Last= "+text.size()+" Number");
			if(text.size()<3) {
				Label war= new Label("Last 2 number can't get new");
				war.setLayoutX(530);
				war.setLayoutY(350);
				war.setFont(Font.font("Verdana", 30));
				root.getChildren().add(war);

			}
		}
	};
	EventHandler<ActionEvent> bitirin = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e)
		{
			System.out.println("Oyun biter");
			//for(int a=0; a<liner.size(); a++) {
				//System.out.println(liner.get(a).getStartX()+" "+liner.get(a).getStartY());
			//}
			//kontrol yap her 3 cigzgide 2li veya 3 lu ye bak
			//0 var ise x10 otekileri topla
			//x=230 y=720 1.satir 80 sayi arttir 3 lu gruplar
			//x=230 y=820 2.satir 
			for(Text ply: p1) {
				System.out.println(ply);
				//System.out.println(ply.getX()+" "+ply.getY());
				for(Line dist: liner) {
					double msfX=ply.getX() - dist.getStartX();
					double msfY=ply.getY() - dist.getStartY();
					if((msfX<80 && msfX>0) && (msfY<100 && msfY>0)) {
						System.out.println(ply.getText()+" stone staying "+dist.getId()+". line");
					}
					//System.out.println(dist);
				}
			}
			
		}
	};
	private void cotacter(ArrayList<Text> taker, ArrayList<Text> player, ArrayList<Text> throwes,
			ArrayList<Rectangle> dropped, ArrayList<Rectangle> takerdropped) {
		int choice2= rnd.nextInt(2)+1;
	//	System.out.println("answer="+choice2);
		if(choice2==1) {
		//	System.out.println("Get dropped");
			player.addAll(taker);
			root.getChildren().removeAll(taker);
		}else if(choice2==2) {
		//	System.out.println("Get new numbers");
			for(int a=0; a<3; a++) {
				player.add(text.get(0));
				text.remove(0);
			}}
		for(int m=0; m<3; m++) {
			int sayi=rnd.nextInt(player.size()-3);
			throwes.add(player.get(sayi));
			player.remove(sayi);}
		//System.out.println("Throws are=");
		//for(Text fire: throwes) {
			//System.out.println(fire.getText());}
		for(int h=0; h<throwes.size(); h++) {
			throwes.get(h).setX(dropped.get(h).getX()+30);
			throwes.get(h).setY(dropped.get(h).getY()+40);
			dropped.get(h).setFill(gray);}
		//System.out.println("throwes size="+throwes.size());
		for(int j=0; j<throwes.size(); j++) {
			if(taker.contains(throwes.get(j))) {
				System.out.println("some problem here");
				throwes.get(j).setX(dropped.get(j).getX()+30);
				throwes.get(j).setY(dropped.get(j).getY()+40);}}
		root.getChildren().addAll(throwes);
		for(int l=0; l<takerdropped.size(); l++) {
			takerdropped.get(l).setFill(white);}}
	EventHandler<ActionEvent> getter = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e)
		{

			root.getChildren().removeAll(getNew, getSide);

			for(int o=0; o<p4throw.size(); o++) {
				p4throw.get(o).setX(liner.get(liner.size()-1-o).getStartX()+30);
				p4throw.get(o).setY(liner.get(liner.size()-1-o).getStartY()+40);
				liner.get(liner.size()-1-o).setStroke(red);
				p1.add(p4throw.get(o));

				P4_Drop.get(o).setFill(white);
				//    P1_Drop.get(dropNum).setTranslateX(-2);
				//  P1_Drop.get(dropNum).setTranslateY(-2);
				//P1_Drop.get(dropNum).setFill(white);
				//root.getChildren().add(P1_Drop.get(dropNum));

			}
			//root.getChildren().add(P1_);

		}
	};
	EventHandler<ActionEvent> newer = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e)
		{
			root.getChildren().removeAll(getNew, getSide);
			for(int o=0; o<3; o++) {
				p1.add(text.get(0));
				text.get(0).setX(liner.get(liner.size()-1-o).getStartX()+30);
				text.get(0).setY(liner.get(liner.size()-1-o).getStartY()+40);
				liner.get(liner.size()-1-o).setStroke(red);
				P4_Drop.get(o).setFill(white);
				root.getChildren().add(text.get(0));
				text.remove(0);
			}
		}
	};
}