package application;
import javafx.application.Application;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;

public class Start extends Application
{
	final int screenWidth = 800;
	final int screenHeight = 600;
	int numberOfRects = 20;
	final int rectWidth = screenWidth/numberOfRects;
	static float seconds = 1.0f;
	boolean animStarted = false;

	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	@Override
	public void start(Stage stage) throws Exception 
	{
		Pane canvas = new Pane();
		canvas.setStyle("-fx-background-color: black;");
		Scene scene = new Scene(canvas, screenWidth, screenHeight);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Bar bars[] = new Bar[numberOfRects];
		for(int i=0; i< numberOfRects; i++)
		{
			int randomNum = getRandomNumber(2,45);
			bars[i] = new Bar(new Rectangle(), randomNum, new Text(), i*rectWidth, screenHeight - randomNum * 10, rectWidth, randomNum*10);
		}
		Rectangle navBar = new Rectangle(0,0);
		navBar.setWidth(screenWidth);
		navBar.setHeight(120);
		navBar.setFill(Color.DARKSLATEGRAY);
		final ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.getItems().addAll(
				"Bubble Sort",
				"Insertion Sort",
				"Quick Sort"
				);
		comboBox.setLayoutX(30);
		comboBox.setLayoutY(20);
		comboBox.getSelectionModel().selectFirst();
		Text title = new Text(screenWidth/2 - 175, 40, "Sorting Algorithm Visualizer");
		title.setFill(Color.WHITE);
		title.setFont(new Font(20));
		title.setStyle("-fx-font-family: Arial;"
				+ "-fx-font-weight: bolder;" 
				+ "-fx-font-size: 26px;");
		Button start = new Button("Start Visualization");
		start.setLayoutX(screenWidth - 160);
		start.setLayoutY(15);
		
		
		for(int i=0; i<bars.length; i++)
		{
			canvas.getChildren().add(bars[i].getRect());
		}
		
		start.setOnAction(e -> {
			if(!animStarted)
			{
				animStarted = true;
			}
			else
			{
				animStarted = false;
			}
			if(comboBox.getValue().equals("Bubble Sort"))
			{
				if(animStarted)
				{
					Algorithms.doBubbleSort(bars);
				}
			}
		});
		
		for(int i=0; i<bars.length; i++)
		{
			canvas.getChildren().add(bars[i].getText());
		}
		
		canvas.getChildren().add(navBar);
		canvas.getChildren().add(comboBox);
		canvas.getChildren().add(start);
		canvas.getChildren().add(title);
		stage.setTitle("Sorting Algorithm Visualizer");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}

}