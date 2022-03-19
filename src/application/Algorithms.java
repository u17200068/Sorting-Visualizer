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
		ArrayList<TranslateTransition> translateanimations = new ArrayList<TranslateTransition>();
		ArrayList<FillTransition> fillanimations = new ArrayList<FillTransition>();
		
		for(int i=0; i<bars.length; i++)
		{
			for(int j=1; j<bars.length; j++)
			{
				if(bars[j].getNum() < bars[j-1].getNum())
				{
					swap(bars, j , j-1);
					TranslateTransition anim1 = new TranslateTransition(Duration.seconds(speed), bars[j].getRect());
					TranslateTransition anim2 = new TranslateTransition(Duration.seconds(speed/10), bars[j].getText());
					
					FillTransition rectFill1 = new FillTransition(Duration.seconds(speed), bars[j].getRect(), Color.RED, Color.NAVY);
					FillTransition rectFill2 = new FillTransition(Duration.seconds(speed), bars[j-1].getRect(), Color.RED, Color.NAVY);
					FillTransition textFill1 = new FillTransition(Duration.seconds(speed/10), bars[j].getText(), Color.GREEN, Color.YELLOW);
					FillTransition textFill2 = new FillTransition(Duration.seconds(speed/10), bars[j-1].getText(), Color.GREEN, Color.YELLOW);
					
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
					translateanimations.add(anim1);
					translateanimations.add(anim2);
					translateanimations.add(anim3);
					translateanimations.add(anim4);
					
					rectFill1.setOnFinished(e->{
						rectFill1.getShape().setFill(Color.RED);
					});
					
					rectFill2.setOnFinished(e->{
						rectFill2.getShape().setFill(Color.RED);
					});
					
					textFill1.setOnFinished(e->{
						textFill1.getShape().setFill(Color.GREEN);
					});
					
					textFill2.setOnFinished(e->{
						textFill2.getShape().setFill(Color.GREEN);
					});
					
					fillanimations.add(rectFill1);
					fillanimations.add(textFill1);
					fillanimations.add(rectFill2);
					fillanimations.add(textFill2);
				}
			}
		}
		
		SequentialTransition seq2 = new SequentialTransition();
		seq2.getChildren().addAll(fillanimations);
		seq2.play();
		SequentialTransition seq = new SequentialTransition();
		seq.getChildren().addAll(translateanimations);
		seq.play();
		seq.setOnFinished(e->{
			changeBarColor(bars);
		});
	}
	
	public void doInsertionSort(Bar[] bars)
	{
		ArrayList<TranslateTransition> translateanimations = new ArrayList<TranslateTransition>();
		ArrayList<FillTransition> fillanimations = new ArrayList<FillTransition>();
		
		for(int i=1; i<bars.length; i++)
		{
			Bar value = bars[i];
			int hole  = i;
			int ctr = 0;
			while(hole>0 && bars[hole-1].getNum() > value.getNum())
			{
				TranslateTransition anim1 = new TranslateTransition(Duration.seconds(speed), bars[hole-1].getRect());
				TranslateTransition anim2 = new TranslateTransition(Duration.seconds(speed/10), bars[hole-1].getText());
				FillTransition rectFill = new FillTransition(Duration.seconds(speed), bars[hole-1].getRect(), Color.RED, Color.WHITESMOKE);
				FillTransition textFill = new FillTransition(Duration.seconds(speed/10), bars[hole-1].getText(), Color.GREEN, Color.YELLOW);
				anim1.setByX(+Bar.width);
				anim2.setByX(+Bar.width);
				translateanimations.add(anim1);
				translateanimations.add(anim2);
				bars[hole] = bars[hole-1];
				hole -= 1;
				ctr+=1;
				
				rectFill.setOnFinished(e->{
					rectFill.getShape().setFill(Color.RED);
				});
				
				textFill.setOnFinished(e->{
					textFill.getShape().setFill(Color.GREEN);
				});
				
				fillanimations.add(textFill);
				fillanimations.add(rectFill);
			}
			bars[hole] = value;
			for(int j=ctr; j>0; j--)
			{
				TranslateTransition anim3 = new TranslateTransition(Duration.seconds(speed), bars[hole].getRect());
				TranslateTransition anim4 = new TranslateTransition(Duration.seconds(speed/10), bars[hole].getText());
				FillTransition rectFill = new FillTransition(Duration.seconds(speed), bars[hole].getRect(), Color.RED, Color.WHITESMOKE);
				FillTransition textFill = new FillTransition(Duration.seconds(speed/10), bars[hole].getText(), Color.GREEN, Color.YELLOW);
				anim3.setByX(-Bar.width);
				anim4.setByX(-Bar.width);
				translateanimations.add(anim3);
				translateanimations.add(anim4);
				
				rectFill.setOnFinished(e->{
					rectFill.getShape().setFill(Color.RED);
				});
				
				textFill.setOnFinished(e->{
					textFill.getShape().setFill(Color.GREEN);
				});
				
				fillanimations.add(textFill);
				fillanimations.add(rectFill);
			}
		}
		
		SequentialTransition seq2 = new SequentialTransition();
		seq2.getChildren().addAll(fillanimations);
		seq2.play();
		SequentialTransition seq = new SequentialTransition();
		seq.getChildren().addAll(translateanimations);
		seq.play();
		seq.setOnFinished(e->{
			changeBarColor(bars);
		});
	}
	
	public void doSelectionSort(Bar[] bars)
	{
		ArrayList<TranslateTransition> translateanimations = new ArrayList<TranslateTransition>();
		ArrayList<FillTransition> fillanimations = new ArrayList<FillTransition>();
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
			}
			FillTransition rectFill1 =  new FillTransition(Duration.seconds(speed), bars[i].getRect(), Color.RED, Color.WHITESMOKE);
			rectFill1.setOnFinished(e->{
				rectFill1.getShape().setFill(Color.RED);
			});
			fillanimations.add(rectFill1);
			
			FillTransition rectFill2 =  new FillTransition(Duration.seconds(speed), bars[min].getRect(), Color.RED, Color.WHITESMOKE);
			rectFill2.setOnFinished(e->{
				rectFill2.getShape().setFill(Color.RED);
			});
			fillanimations.add(rectFill2);
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
			translateanimations.add(anim1);
			translateanimations.add(anim2);
			translateanimations.add(anim3);
			translateanimations.add(anim4);
		}
		SequentialTransition seq1 = new SequentialTransition();
		seq1.getChildren().addAll(fillanimations);
		seq1.play();
		SequentialTransition seq = new SequentialTransition();
		seq.getChildren().addAll(translateanimations);
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
