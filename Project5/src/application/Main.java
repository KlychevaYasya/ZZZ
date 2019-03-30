package application;
	


import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;


public class Main extends Application {
	

	private static final int width = 800;
	private static final int height = 600;
	

	private static final int RACKET_WIDTH = 10;
	private static final int RACKET_HEIGHT = 90;
	

	private static final int BALL_RAD = 30;
	
	
	double playerX=0;
	double playerY = height/2;
	
	
	double compX = width - RACKET_WIDTH;
	double compY = height/2;
	
	
	double ballX = width/2;
	double ballY = height/2;
	

	GraphicsContext gc;
	

	double ballYSpeed = 3;
	double ballXSpeed = 3;
	

	boolean gameStarted;
	
	private void drawTable() {
		
		gc.setFill(Color.GREEN);
		gc.fillRect(0, 0, width, height);			
		
		gc.setFill(Color.YELLOW);
		gc.fillRect(width/2, 0, 2, height);		
		
		if(gameStarted) {
			ballX+=ballXSpeed;
			ballY+=ballYSpeed;
			
			if(ballX < width-width/4) {
				compY = ballY - RACKET_HEIGHT/2;
			}
			gc.fillOval(ballX, ballY, BALL_RAD, BALL_RAD);
		} else {
			gc.setStroke(Color.YELLOW);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.strokeText("Click to start", width/2, height/2);			
		}
	
		
		gc.fillRect(playerX, playerY, RACKET_WIDTH, RACKET_HEIGHT);
		gc.fillRect(compX, compY, RACKET_WIDTH, RACKET_HEIGHT);	
		 if (ballX + BALL_RAD/2 > compX - RACKET_WIDTH) {
			   ballXSpeed = ballXSpeed *(-1);
		 }
		 if (ballX < playerX + RACKET_WIDTH && (ballY + BALL_RAD/2 > playerY) && (ballY + BALL_RAD/2 < playerY + RACKET_HEIGHT)) {
			   ballXSpeed = ballXSpeed *(-1);
			  }
		 if (ballY > height - BALL_RAD) {
			   ballYSpeed = ballYSpeed *(-1);
			  }
		 if (ballY < 0) {
			   ballYSpeed = ballYSpeed *(-1);
			  }
		 }
	
	@Override
	public void start(Stage root) {
		Canvas canvas = new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
		drawTable();		
		Timeline t1 = new Timeline(new KeyFrame(Duration.millis(10), e->drawTable()));
		t1.setCycleCount(Timeline.INDEFINITE);
		
		canvas.setOnMouseClicked(e -> gameStarted = true);
		canvas.setOnMouseMoved(e -> playerY = e.getY());
		
		root.setScene(new Scene(new StackPane(canvas)));
		root.setTitle("Ping-pong");
		root.show();
		t1.play();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}