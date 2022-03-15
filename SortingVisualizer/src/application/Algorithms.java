package application;

import java.util.ArrayList;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Algorithms 
{
	private static void swap(int[] numArr, int index1, int index2)
	{
		int temp = numArr[index1];
		numArr[index1] = numArr[index2];
		numArr[index2] = temp;
	}
	
	private static void swap(Rectangle[] rectArr, int index1, int index2)
	{
		Rectangle temp = rectArr[index1];
		rectArr[index1] = rectArr[index2];
		rectArr[index2] = temp;
	}
	
	private static void swap(Text[] textArr, int index1, int index2)
	{
		Text temp = textArr[index1];
		textArr[index1] = textArr[index2];
		textArr[index2] = temp;
	}
	
	public static void doBubbleSort(Bar[] bars)
	{
		ArrayList<TranslateTransition> animations = new ArrayList<TranslateTransition>();
		
		int numArr[] = new int[bars.length];
		Rectangle rectArr[] = new Rectangle[bars.length];
		Text textArr[] = new Text[bars.length];
		
		for(int i=0; i<bars.length; i++)
		{
			numArr[i] = bars[i].getNum();
			rectArr[i] = bars[i].getRect();
			textArr[i] = bars[i].getText();
		}
		
		for(int i=0; i<bars.length; i++)
		{
			for(int j=1; j<bars.length; j++)
			{
				if(numArr[j] < numArr[j-1])
				{
					swap(numArr, j , j-1);
					swap(rectArr, j , j-1);
					swap(textArr, j , j-1);
					TranslateTransition anim1 = new TranslateTransition(Duration.seconds(0.4f), rectArr[j]);
					TranslateTransition anim2 = new TranslateTransition(Duration.seconds(0.05f), textArr[j]);
					TranslateTransition anim3 = new TranslateTransition(Duration.seconds(0.4f), rectArr[j-1]);
					TranslateTransition anim4 = new TranslateTransition(Duration.seconds(0.05f), textArr[j-1]);
					
					double anim1dx = +Bar.width;
					double anim2dx;
					if(numArr[j]>10)
					{
						anim2dx = +Bar.width;
					}
					else
					{
						anim2dx = +Bar.width;
					}
					double anim3dx = -Bar.width;
					double anim4dx;
					if(numArr[j-1]>10)
					{
						anim4dx = -Bar.width;
					}
					else
					{
						anim4dx = -Bar.width;
					}
					
					anim1.setByX(anim1dx);
					anim2.setByX(anim2dx);
					anim3.setByX(anim3dx);
					anim4.setByX(anim4dx);
					animations.add(anim1);
					animations.add(anim2);
					animations.add(anim3);
					animations.add(anim4);
				}
			}
		}
		
		SequentialTransition seq = new SequentialTransition();
		seq.getChildren().addAll(animations);
		seq.play();
	}
}
