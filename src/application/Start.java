package application;
import javafx.application.Application;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;

public class Start extends Application
{
	final int screenWidth = 800;
	final int screenHeight = 600;
	int numberOfRects = 20;
	final int rectWidth = screenWidth/numberOfRects;
	static float seconds = 1.0f;

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
		Image icon = new Image(getClass().getResourceAsStream("/res/icon.png"));
		Bar bars[] = new Bar[numberOfRects];
		Algorithms algo = new Algorithms(4.0f);
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
				"Selection Sort"
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
		Button shuffle = new Button("Shuffle Numbers");
		shuffle.setLayoutX(screenWidth/2 - 80);
		shuffle.setLayoutY(70);
		start.setLayoutX(screenWidth - 160);
		start.setLayoutY(15);
		
		Button incSpeed = new Button("Increase speed");
		Button decSpeed = new Button("Decrease speed");
		incSpeed.setLayoutX(screenWidth/2 + 80);
		incSpeed.setLayoutY(70);
		decSpeed.setLayoutX(screenWidth/2 - 230);
		decSpeed.setLayoutY(70);
		
		incSpeed.setOnMouseClicked(e->{
			algo.increaseSpeed();
		});
		
		decSpeed.setOnMouseClicked(e->{
			algo.decreaseSpeed();
		});
		
		shuffle.setOnAction(e->{
			for(int i=0; i<bars.length; i++)
			{
				int randomNum = getRandomNumber(2,45);
				canvas.getChildren().remove(bars[i].getRect());
				canvas.getChildren().remove(bars[i].getText());
				bars[i].setNum(randomNum);
				bars[i].setRect(new Rectangle(), i*rectWidth, screenHeight - randomNum * 10);
				bars[i].setText(new Text(String.valueOf(randomNum)), screenHeight - randomNum * 10, randomNum * 10);
				canvas.getChildren().add(bars[i].getRect());
				canvas.getChildren().add(bars[i].getText());
			}
		});
		
		for(int i=0; i<bars.length; i++)
		{
			canvas.getChildren().add(bars[i].getRect());
		}
		
		start.setOnAction(e -> {
			if(comboBox.getValue().equals("Bubble Sort"))
			{
				algo.doBubbleSort(bars);
			}
			if(comboBox.getValue().equals("Insertion Sort"))
			{
				algo.doInsertionSort(bars);
			}
			if(comboBox.getValue().equals("Selection Sort"))
			{
				algo.doSelectionSort(bars);
			}
		});
		
		for(int i=0; i<bars.length; i++)
		{
			canvas.getChildren().add(bars[i].getText());
		}
		
		canvas.getChildren().add(navBar);
		canvas.getChildren().add(comboBox);
		canvas.getChildren().add(start);
		canvas.getChildren().add(shuffle);
		canvas.getChildren().add(title);
		canvas.getChildren().add(incSpeed);
		canvas.getChildren().add(decSpeed);
		stage.getIcons().add(icon);
		stage.setTitle("Sorting Algorithm Visualizer");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}

}