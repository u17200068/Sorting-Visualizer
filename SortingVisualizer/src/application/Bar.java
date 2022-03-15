package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Bar 
{
	private Rectangle rect;
	private int num;
	private Text text;
	public static int width;
	public static int height;
	
	public Bar(Rectangle rect, int num, Text text, int posX, int posY, int width, int height)
	{
		this.rect = rect;
		this.num = num;
		this.text = text;
		Bar.width = width;
		Bar.height = height;
		
		rect.setX(posX);
		rect.setY(posY);
		rect.setWidth(width);
		rect.setHeight(num * 10);
		rect.setFill(Color.RED);
		rect.setStroke(Color.AQUA);
		rect.setOpacity(0.5f);
		if(num>10)
		{
			text.setX(posX + 12);
		}
		else
		{
			text.setX(posX + 15);
		}
		text.setStyle("-fx-font-weight: bolder");
		text.setY(posY+height - 5);
		text.setText(String.valueOf(num));
		text.setFill(Color.GREEN);
	}
	
	public Bar(int num, Text text, int posX, int posY)
	{
		this.num = num;
		rect = new Rectangle(posX, posY, width, this.num*10);
		this.text = text;
		rect.setFill(Color.RED);
		rect.setStroke(Color.AQUA);
		rect.setOpacity(0.5f);
		text.setText(String.valueOf(num));
	}
	
	public Rectangle getRect()
	{
		return rect;
	}
	
	public int getNum()
	{
		return num;
	}
	
	public Text getText()
	{
		return text;
	}

}
