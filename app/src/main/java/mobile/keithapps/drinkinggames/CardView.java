package mobile.keithapps.drinkinggames;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class CardView extends FrameLayout {
    private ImageView imageView;
    private boolean frontShowing = false;
    private boolean flipOnClick;

    public CardView(Context context) {
        this(context, null);
    }

    public CardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardView(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
        imageView = new ImageView(context, attrs, defaultStyle);
        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cardback));
        int width = getWidth() - 20, height = getHeight() - 20;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setMinimumWidth(width);
        imageView.setMinimumHeight(height);
        imageView.setMaxHeight(width);
        imageView.setMaxWidth(height);
        this.addView(imageView, params);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (frontShowing) return;
                ObjectAnimator animation = ObjectAnimator.ofFloat(v, "rotationY", 0.0f, 90f);
                animation.setDuration(500);
                animation.setRepeatCount(0);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                final ObjectAnimator a2 = ObjectAnimator.ofFloat(v, "rotationY", 270f, 360);
                a2.setDuration(500);
                a2.setRepeatCount(0);
                a2.setInterpolator(new AccelerateDecelerateInterpolator());
                animation.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        imageView.setImageResource(R.drawable.hk);
                        a2.start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                a2.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        frontShowing = true;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animation.start();

            }
        });
        setClickable(true);
        setFocusable(true);
        setBackgroundResource(R.color.transparent);
        if (imageView.getDrawable() != null) imageView.setVisibility(View.VISIBLE);
        else imageView.setVisibility(View.GONE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setImageResource(int resId) {
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(resId);
    }

    public void setImageDrawable(Drawable drawable) {
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageDrawable(drawable);
    }
}
