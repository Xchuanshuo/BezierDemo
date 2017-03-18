package bezider.curve.com.bezierdemo;
import android.view.View;
import android.view.View.OnClickListener;
import android.graphics.Paint;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Path;
import android.graphics.Canvas;
import bezider.curve.com.bezierdemo.beziertools.*;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.AccelerateInterpolator;
import android.graphics.Color;


/*
 *
 *贝塞尔路径动画
 */

public class PathBezier extends View implements View.OnClickListener
{
	private Path mPath;
	private Paint mPathPaint;
    private Paint mCicrlePaint;
	private int mStartPointX;
	private int mStartPointY;
	private int mEndPointX;
	private int mEndPointY;
	private int mMovePointX;
	private int mMovePointY;
	private int mControlPointX;
	private int mControlPointY;
	public PathBezier(Context context){
		super(context);
	}
	public PathBezier(Context context,AttributeSet attr){
		super(context,attr);
		mPathPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
		mPathPaint.setStyle(Paint.Style.STROKE);
		mPathPaint.setStrokeWidth(5);
		mCicrlePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
		mCicrlePaint.setColor(Color.GREEN);
		mStartPointX=100;
		mStartPointY=100;
		mEndPointX=600;
		mEndPointY=600;
		mMovePointX=mStartPointX;
		mMovePointY=mStartPointY;
		mControlPointX=500;
		mControlPointY=0;
		mPath=new Path();
		setOnClickListener(this);
	}
	public PathBezier(Context context,AttributeSet attr,int defStyleAttr){
		super(context,attr,defStyleAttr);

	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO: Implement this method
		super.onDraw(canvas);
		mPath.reset();
		canvas.drawCircle(mStartPointX,mStartPointY,30,mCicrlePaint);
		canvas.drawCircle(mEndPointX,mEndPointY,30,mCicrlePaint);
		mPath.moveTo(mStartPointX,mStartPointY);
		mPath.quadTo(mControlPointX,mControlPointY,mEndPointX,mEndPointY);
		canvas.drawPath(mPath,mPathPaint);
		canvas.drawCircle(mMovePointX,mMovePointY,30,mCicrlePaint);
	}
	
	@Override
	public void onClick(View view)
	{
		BezierEvaluator be=new BezierEvaluator(
		     new PointF(mControlPointX,mControlPointY));
		ValueAnimator animator=ValueAnimator.ofObject(
		   be,new PointF(mStartPointX,mStartPointY),
		      new PointF(mEndPointX,mEndPointY));
		animator.setDuration(600);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

				@Override
				public void onAnimationUpdate(ValueAnimator p1)
				{
		PointF point=(PointF)p1.getAnimatedValue();
		mMovePointY=(int)point.y;
		mMovePointX=(int)point.x;
		invalidate();
					// TODO: Implement this method
				}
		});
	   animator.setInterpolator(new AccelerateInterpolator());
	   animator.start();
		// TODO: Implement this method
	}

}
