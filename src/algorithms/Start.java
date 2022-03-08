package algorithms;
import javafx.application.Application;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import java.util.Random;

public class Start extends Application
{
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		Pane canvas = new Pane();
		canvas.setStyle("-fx-background-color: black;");
		Scene scene = new Scene(canvas, 800,600);
		Random randomNum = new Random();
		int arr[] = {getRandomNumber(1,30), getRandomNumber(1,30), getRandomNumber(1,30), getRandomNumber(1,30), getRandomNumber(1,10),
					 getRandomNumber(1,30), getRandomNumber(1,30), getRandomNumber(1,30), getRandomNumber(1,30), getRandomNumber(1,10),
					 getRandomNumber(1,30), getRandomNumber(1,30), getRandomNumber(1,30), getRandomNumber(1,30), getRandomNumber(1,10)};
		Rectangle rect[] = {new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle(),
							new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle(),
							new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle()};
		Color colors[] = {Color.AQUA, Color.BLUEVIOLET, Color.FORESTGREEN, Color.CRIMSON, Color.YELLOW,
						  Color.DARKORCHID, Color.LIME, Color.DARKMAGENTA, Color.DARKORANGE, Color.WHITE,
						  Color.DARKOLIVEGREEN, Color.MEDIUMAQUAMARINE, Color.PLUM, Color.SIENNA, Color.DEEPPINK};
		int rectWidth = 35;
		
		for(int i=0; i<arr.length; i++)
		{
			rect[i].setX(i*rectWidth + 150);
			rect[i].setY(600 - arr[i] * 10);
			rect[i].setWidth(rectWidth);
			rect[i].setHeight(arr[i] * 10);
			rect[i].setFill(colors[i]);
			canvas.getChildren().add(rect[i]);
		}
		
		stage.setTitle("Sorting Algorithm Visualizer");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}

}
