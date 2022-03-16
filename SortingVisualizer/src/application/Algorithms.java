package application;

import java.util.ArrayList;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Algorithms 
{
	private static boolean isSorted = false;
	
	private static void swap(Bar[] bars, int index1, int index2)
	{
		Bar temp = bars[index1];
		bars[index1] = bars[index2];
		bars[index2] = temp;
	}
	
	public static void doBubbleSort(Bar[] bars)
	{
		ArrayList<TranslateTransition> animations = new ArrayList<TranslateTransition>();
		
		for(int i=0; i<bars.length; i++)
		{
			for(int j=1; j<bars.length; j++)
			{
				if(bars[j].getNum() < bars[j-1].getNum())
				{
					swap(bars, j , j-1);
					TranslateTransition anim1 = new TranslateTransition(Duration.seconds(0.4f), bars[j].getRect());
					TranslateTransition anim2 = new TranslateTransition(Duration.seconds(0.05f), bars[j].getText());
					TranslateTransition anim3 = new TranslateTransition(Duration.seconds(0.4f), bars[j-1].getRect());
					TranslateTransition anim4 = new TranslateTransition(Duration.seconds(0.05f), bars[j-1].getText());
					
					double anim1dx = +Bar.width;
					double anim2dx;
					if(bars[j].getNum()>10)
					{
						anim2dx = +Bar.width;
					}
					else
					{
						anim2dx = +Bar.width;
					}
					double anim3dx = -Bar.width;
					double anim4dx;
					if(bars[j-1].getNum()>10)
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
		isSorted = true;
	}
	
	public static void doInsertionSort(Bar[] bars)
	{
		ArrayList<TranslateTransition> animations = new ArrayList<TranslateTransition>();
		
		for(int i=1; i<bars.length; i++)
		{
			Bar value = bars[i];
			int hole  = i;
			while(hole>0 && bars[hole-1].getNum() > value.getNum())
			{
				TranslateTransition anim = new TranslateTransition(Duration.seconds(0.4f), bars[hole].getRect());
				anim.setByX(Bar.width);
				animations.add(anim);
				bars[hole] = bars[hole-1];
				hole -= 1;
			}
			bars[hole] = value;
		}
		
		SequentialTransition seq = new SequentialTransition();
		seq.getChildren().addAll(animations);
		seq.play();
		isSorted = true;
	}
}
