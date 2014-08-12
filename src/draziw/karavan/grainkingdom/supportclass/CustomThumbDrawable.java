package draziw.karavan.grainkingdom.supportclass;

import draziw.karavan.grainkingdom.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class CustomThumbDrawable extends Drawable {
	Bitmap mBmp;
	Paint mPaint;
	MyCustomSeekBar myCustomSeekBar;
	public int textSize;
	
	
	public CustomThumbDrawable(Context cc) {
		// TODO Auto-generated constructor stub
		mBmp=BitmapFactory.decodeResource(cc.getResources(), R.drawable.cornthumb);		
		mBmp=resize(50,mBmp);
		mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.YELLOW);
		mPaint.setTextSize(22);
		invalidateSelf();
	}

	@Override
	protected final boolean onStateChange(int[] state) {
		invalidateSelf();
		return false;
	}

	@Override
	public final boolean isStateful() {
		return true;
	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		int top = this.getBounds().top;
		int left = this.getBounds().left;
		int width = this.getBounds().width();
		int bottom = this.getBounds().bottom;
		int height=this.getBounds().height();
		int textLeftPos=left;
		
		canvas.drawBitmap(mBmp, left, top, mPaint);	
		int progress=myCustomSeekBar.getAbsoluteProgress();
		String tmp=Integer.toString(progress);
		canvas.drawText(tmp, textLeftPos, bottom-height/4, mPaint);
		textSize=(int) mPaint.measureText(tmp);
		
	}

	@Override
	public int getOpacity() {		
		return PixelFormat.TRANSLUCENT;
	}

	@Override
	public void setAlpha(int arg0) {		

	}
	


	@Override
	public void setColorFilter(ColorFilter arg0) {
		// TODO Auto-generated method stub

	}
		
	 
	 
		@Override
		public int getIntrinsicHeight() {
			return (int) (mBmp.getHeight());
		}

		@Override
		public int getIntrinsicWidth() {
			return Math.max((int) (mBmp.getWidth()),textSize);
		}
		
		
		 public Bitmap resize(int newx, int newy, Bitmap bmp) {
				Bitmap tmp = Bitmap.createScaledBitmap(bmp, newx, newy, false);
				return tmp;		
			}
		 
		 public Bitmap resize(int newx, Bitmap bmp) {
			 int oldx=bmp.getWidth();
			 int oldy=bmp.getHeight();
			 int newy=(int) (oldy*((float)newx/oldx));
			 
				Bitmap tmp = Bitmap.createScaledBitmap(bmp, newx, newy, false);
				return tmp;		
			}

		public void setParrent(MyCustomSeekBar mCSB) {
			myCustomSeekBar=mCSB;			
		}

}
