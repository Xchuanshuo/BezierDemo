package bezider.curve.com.bezierdemo;

import android.graphics.*;
import android.view.*;
import android.content.Context;
import android.util.*;
/*
 *
 *太极图案
 */
public class DrawTaiJi extends View
{
	//旋转角度
	private float degrees;
	private Paint mWhitePaint;
	private Paint mBlackPaint;
	public DrawTaiJi(Context content){
		super(content);
		initPaint();
	}
	
  
	//在Layout文件中使用这个View时会被调用
	public DrawTaiJi(Context context,AttributeSet attr){
		super(context,attr);
		initPaint();
	}
	public void setRotate(float degrees){
		this.degrees=degrees;
		invalidate();//重绘界面
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO: Implement this method
		super.onDraw(canvas);
		int width=canvas.getWidth();
		int height=canvas.getHeight();
		Point mCenter=new Point(width/2,height/2);
		//将画布移动到屏幕中间
		canvas.translate(mCenter.x,mCenter.y);
		canvas.drawColor(Color.GRAY);
		canvas.rotate(degrees);
		//绘制2个大圆
		int radius=Math.min(width,height)/2-100;
		RectF mRectf=new RectF(-radius,-radius,radius,radius);
		canvas.drawArc(mRectf,90,180,true,mWhitePaint);
		canvas.drawArc(mRectf,-90,180,true,mBlackPaint);
		//绘制2个小圆
		int smallradius=radius/2;
		canvas.drawCircle(0,-smallradius,smallradius,mWhitePaint);
		canvas.drawCircle(0,smallradius,smallradius,mBlackPaint);
		
		canvas.drawCircle(0,-smallradius,smallradius/4,mBlackPaint);
		canvas.drawCircle(0,smallradius,smallradius/4,mWhitePaint);
	}

	private void initPaint(){
		mWhitePaint=new Paint();
		mWhitePaint.setAntiAlias(true);
		mWhitePaint.setColor(Color.WHITE);
		mBlackPaint=new Paint();
		mBlackPaint.setAntiAlias(true);
		mBlackPaint.setColor(Color.BLACK);
	}
}
