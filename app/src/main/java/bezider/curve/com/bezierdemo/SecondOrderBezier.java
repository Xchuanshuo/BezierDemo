package bezider.curve.com.bezierdemo;
import android.view.View;
import android.graphics.Paint;
import android.graphics.Path;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.view.MotionEvent;

/*
 *二阶贝塞尔曲线
 *
 */
public class SecondOrderBezier extends View
{
	private Paint mPaintBezier;
	private Paint mPaintAuxiliary;
	private Paint mPaintAuxiliaryText;
	
	private float AuxiliaryPonitX;
	private float AuxiliaryPointY;
	
	private float mStartPointX;
	private float mStartPointY;
	
	private float mEndPointX;
	private float mEndPonitY;
	
	private Path mPath=new Path();
	public SecondOrderBezier(Context context){
		super(context);
	}
	public SecondOrderBezier(Context context,AttributeSet attr){
		super(context,attr);
	mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);
	mPaintBezier.setStyle(Paint.Style.STROKE);
	mPaintBezier.setStrokeWidth(8);
	mPaintAuxiliary = new Paint(Paint.ANTI_ALIAS_FLAG);
	mPaintAuxiliary.setStyle(Paint.Style.STROKE);
	mPaintAuxiliary.setStrokeWidth(2);
	mPaintAuxiliaryText = new Paint(Paint.ANTI_ALIAS_FLAG);
	mPaintAuxiliaryText.setStyle(Paint.Style.STROKE);
	mPaintAuxiliaryText.setTextSize(20);
	}
	public SecondOrderBezier(Context context,AttributeSet attr,int defStyleAttr){
		super(context,attr,defStyleAttr);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		// TODO: Implement this method
		super.onSizeChanged(w, h, oldw, oldh);
		mStartPointX=w/6;
		mStartPointY=h-200;
		
		mEndPointX=w/6*5;
		mEndPonitY=h-200;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO: Implement this method
		super.onDraw(canvas);
		mPath.reset();
		mPath.moveTo(mStartPointX,mStartPointY);
		//绘制辅助点
		canvas.drawPoint(AuxiliaryPonitX,AuxiliaryPointY,mPaintAuxiliary);
		canvas.drawText("控制点",AuxiliaryPonitX,AuxiliaryPointY,mPaintAuxiliaryText);
		canvas.drawText("起始点",mStartPointX,mStartPointY,mPaintAuxiliaryText);
		canvas.drawText("终止点",mEndPointX,mEndPonitY,mPaintAuxiliaryText);
		//绘制辅助线
		canvas.drawLine(mStartPointX,mStartPointY,AuxiliaryPonitX,AuxiliaryPointY,
		           mPaintAuxiliary);
		canvas.drawLine(mEndPointX,mEndPonitY,AuxiliaryPonitX,AuxiliaryPointY,
		           mPaintAuxiliary);
		//二阶贝塞尔曲线
		mPath.quadTo(AuxiliaryPonitX,AuxiliaryPointY,mEndPointX,mEndPonitY);
		canvas.drawPath(mPath,mPaintBezier);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		switch(event.getAction()){
			case MotionEvent.ACTION_MOVE:
				AuxiliaryPonitX=event.getX();
				AuxiliaryPointY=event.getY();
			    invalidate();
		}
		// TODO: Implement this method
		return true;
		
	}
    
}
