package application;

import java.util.ArrayList;

import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Algorithms 
{
	private float speed;
	private boolean isSorted;
	
	public Algorithms(float speed)
	{
		this.speed = speed;
		this.speed /= 10;
	}
	
	private void swap(Bar[] bars, int index1, int index2)
	{
		Bar temp = bars[index1];
		bars[index1] = bars[index2];
		bars[index2] = temp;
	}
	
	public void doBubbleSort(Bar[] bars)
	{
		ArrayList<TranslateTransition> animations = new ArrayList<TranslateTransition>();
		
		for(int i=0; i<bars.length; i++)
		{
			for(int j=1; j<bars.length; j++)
			{
				if(bars[j].getNum() < bars[j-1].getNum())
				{
					swap(bars, j , j-1);
					TranslateTransition anim1 = new TranslateTransition(Duration.seconds(speed), bars[j].getRect());
					TranslateTransition anim2 = new TranslateTransition(Duration.seconds(speed/10), bars[j].getText());
					TranslateTransition anim3 = new TranslateTransition(Duration.seconds(speed), bars[j-1].getRect());
					TranslateTransition anim4 = new TranslateTransition(Duration.seconds(speed/10), bars[j-1].getText());
					
					double anim1dx = +Bar.width;
					double anim2dx = +Bar.width;
					
					double anim3dx = -Bar.width;
					double anim4dx = -Bar.width;
					
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
		seq.setOnFinished(e->{
			changeBarColor(bars);
		});
	}
	
	public void doInsertionSort(Bar[] bars)
	{
		ArrayList<TranslateTransition> animations = new ArrayList<TranslateTransition>();
		
		for(int i=1; i<bars.length; i++)
		{
			Bar value = bars[i];
			int hole  = i;
			int ctr = 0;
			while(hole>0 && bars[hole-1].getNum() > value.getNum())
			{
				TranslateTransition anim1 = new TranslateTransition(Duration.seconds(speed), bars[hole-1].getRect());
				TranslateTransition anim2 = new TranslateTransition(Duration.seconds(speed/10), bars[hole-1].getText());
				anim1.setByX(+Bar.width);
				anim2.setByX(+Bar.width);
				animations.add(anim1);
				animations.add(anim2);
				bars[hole] = bars[hole-1];
				hole -= 1;
				ctr+=1;
			}
			bars[hole] = value;
			for(int j=ctr; j>0; j--)
			{
				TranslateTransition anim3 = new TranslateTransition(Duration.seconds(speed), bars[hole].getRect());
				TranslateTransition anim4 = new TranslateTransition(Duration.seconds(speed/10), bars[hole].getText());
				anim3.setByX(-Bar.width);
				anim4.setByX(-Bar.width);
				animations.add(anim3);
				animations.add(anim4);
			}
		}
		
		SequentialTransition seq = new SequentialTransition();
		seq.getChildren().addAll(animations);
		seq.play();
		seq.setOnFinished(e->{
			changeBarColor(bars);
		});
	}
	
	public void doSelectionSort(Bar[] bars)
	{
		ArrayList<TranslateTransition> animations = new ArrayList<TranslateTransition>();
		int dx1;
		int dx2;
		for(int i=0; i<bars.length-1; i++)
		{
			int min = i;
			for(int j=i+1; j<bars.length; j++)
			{
				if(bars[j].getNum() < bars[min].getNum())
				{
					min = j;
				}
				swap(bars, i, min);
				dx1 = (min * Bar.width) - (i * Bar.width);
				dx2 = (i * Bar.width) - (min * Bar.width);
				TranslateTransition anim1 = new TranslateTransition(Duration.seconds(speed), bars[min].getRect());
				TranslateTransition anim2 = new TranslateTransition(Duration.seconds(speed/10), bars[min].getText());
				TranslateTransition anim3 = new TranslateTransition(Duration.seconds(speed), bars[i].getRect());
				TranslateTransition anim4 = new TranslateTransition(Duration.seconds(speed/10), bars[i].getText());
				anim1.setByX(dx1);
				anim2.setByX(dx1);
				anim3.setByX(dx2);
				anim4.setByX(dx2);
				animations.add(anim1);
				animations.add(anim2);
				animations.add(anim3);
				animations.add(anim4);
			}
		}
		SequentialTransition seq = new SequentialTransition();
		seq.getChildren().addAll(animations);
		seq.play();
		seq.setOnFinished(e->{
			changeBarColor(bars);
		});
	}
	
	public void changeBarColor(Bar[] bars)
	{
		setSorted(true);
		ArrayList<FillTransition> animations = new ArrayList<FillTransition>();
		for(int i=0; i<bars.length; i++)
		{
			FillTransition anim = new FillTransition(Duration.seconds(speed), bars[i].getRect(), Color.RED, Color.GREEN);
			FillTransition anim2 = new FillTransition(Duration.seconds(speed/10), bars[i].getText(), Color.GREEN, Color.YELLOW);
			animations.add(anim);
			animations.add(anim2);
		}	
		SequentialTransition seq = new SequentialTransition();
		seq.getChildren().addAll(animations);
		seq.play();
	}
	
	public void increaseSpeed()
	{
		speed-=0.1f;
	}
	
	public void decreaseSpeed()
	{
		speed+=0.1f;
	}

	public boolean getIsSorted() 
	{
		return isSorted;
	}

	private void setSorted(boolean isSorted) 
	{
		this.isSorted = isSorted;
	}
}
