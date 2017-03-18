package bezider.curve.com.bezierdemo;
import android.view.View;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.graphics.*;
import android.view.*;
/*
 *三阶贝塞尔曲线
 *
 */
public class ThirdOrderBezier extends View
{
	private Paint mPaintBezier;
	private Paint mPaintAuxiliary;
	private Paint mPaintAuxiliaryText;
	
	private float mAuxiliaryOneX;
	private float mAuxiliaryOneY;
	private float mAuxiliaryTwoX;
	private float mAuxiliaryTwoY;
	
	private float mStartPointX;
	private float mStartPointY;
	private float mEndPointX;
	private float mEndPointY;
	
	private Path mPath=new Path();
	
	private boolean isSecondPoint=false;
	public ThirdOrderBezier(Context context){
		super(context);
	}
	public ThirdOrderBezier(Context context,AttributeSet attr){
		super(context,attr);
		mPaintBezier=new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintBezier.setStyle(Paint.Style.STROKE);
		mPaintBezier.setStrokeWidth(8);
		mPaintAuxiliary=new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintAuxiliary.setStyle(Paint.Style.STROKE);
		mPaintAuxiliary.setStrokeWidth(2);
		mPaintAuxiliaryText=new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintAuxiliaryText.setStyle(Paint.Style.STROKE);
		mPaintAuxiliaryText.setTextSize(20);
	}
	public ThirdOrderBezier(Context context,AttributeSet attr,int deStylrAttr){
		super(context,attr,deStylrAttr);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		// TODO: Implement this method
		super.onSizeChanged(w, h, oldw, oldh);
		mStartPointX=w/6;
		mStartPointY=h/2-200;
		mEndPointX=w/6*5;
		mEndPointY=h/2-200;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		// TODO: Implement this method
		mPath.reset();
		mPath.moveTo(mStartPointX,mStartPointY);
		//辅助点
		canvas.drawPoint(mAuxiliaryOneX,mAuxiliaryOneY,mPaintAuxiliary);
		canvas.drawText("控制点1",mAuxiliaryOneX,mAuxiliaryOneY,mPaintAuxiliaryText);
		canvas.drawText("控制点2",mAuxiliaryTwoX,mAuxiliaryTwoY,mPaintAuxiliaryText);
		canvas.drawText("启动点",mStartPointX,mStartPointY,mPaintAuxiliaryText);
		canvas.drawText("终止点",mEndPointX,mEndPointY,mPaintAuxiliaryText);
		//初始位置
		canvas.drawLine(mStartPointX,mStartPointY,mAuxiliaryOneX,mAuxiliaryOneY,mPaintAuxiliary);
	//	canvas.drawLine(mStartPointX,mStartPointY,mAuxiliaryTwoX,mAuxiliaryTwoY,mPaintAuxiliary);
		//最终位置
	//	canvas.drawLine(mEndPointX,mEndPointY,mAuxiliaryOneX,mAuxiliaryOneY,mPaintAuxiliary);
		canvas.drawLine(mEndPointX,mEndPointY,mAuxiliaryTwoX,mAuxiliaryTwoY,mPaintAuxiliary);
		canvas.drawLine(mAuxiliaryOneX,mAuxiliaryOneY,mAuxiliaryTwoX,mAuxiliaryTwoY,mPaintAuxiliary);
		//三阶贝塞尔曲线
		mPath.cubicTo(mAuxiliaryOneX,mAuxiliaryOneY,mAuxiliaryTwoX,mAuxiliaryTwoY,mEndPointX,mEndPointY);
		canvas.drawPath(mPath,mPaintBezier);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		switch(event.getAction()&MotionEvent.ACTION_MASK){
			case MotionEvent.ACTION_POINTER_DOWN:
				isSecondPoint=true;
				break;
			case MotionEvent.ACTION_MOVE:
				mAuxiliaryOneX=event.getX(0);
				mAuxiliaryOneY=event.getY(0);
				if(isSecondPoint){
				mAuxiliaryTwoX=event.getX(1);
				mAuxiliaryTwoY=event.getY(1);
				}
				invalidate();
				break;
			case MotionEvent.ACTION_UP:
				isSecondPoint=false;
				break;
		}
		// TODO: Implement this method
		return true;
	}

   
}
