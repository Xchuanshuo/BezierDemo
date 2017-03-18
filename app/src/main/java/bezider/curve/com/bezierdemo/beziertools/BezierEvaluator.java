package bezider.curve.com.bezierdemo.beziertools;
import android.animation.TypeEvaluator;
import android.graphics.PointF;
import bezider.curve.com.bezierdemo.util.BezierUtil;
public class BezierEvaluator implements TypeEvaluator<PointF>
{

	@Override
	public PointF evaluate(float f, PointF startValue, PointF endValue)
	{
		// TODO: Implement this method
		return BezierUtil.CalculateBezierPointForQuadratic(f,startValue,mControlPoint,endValue);
	}
	private PointF mControlPoint;
	public BezierEvaluator(PointF mControlPonit){
		this.mControlPoint=mControlPonit;
	}
	
}
