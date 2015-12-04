package mobile.keithapps.drinkinggames;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class CardView extends ImageView {
    private Drawable draw;
    private boolean frontShowing = false;

    public CardView(Context context) {
        this(context, null);
    }

    public CardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardView(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
        draw = ContextCompat.getDrawable(getContext(), R.drawable.cardback);
        setClickable(true);
    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);
        draw.setBounds(20, 20, getWidth() - 20, getHeight() - 20);
        draw.draw(c);
    }

    public void reset() {
        draw = ContextCompat.getDrawable(getContext(), R.drawable.cardback);
        setVisibility(View.VISIBLE);
        invalidate();
    }

    public synchronized void flip() {
        if (frontShowing) {
            setVisibility(View.GONE);
            setClickable(false);
            return;
        }
        if (lock) return;
        lock = true;
        AnimatorSet as = new AnimatorSet();
        final Drawable d = ContextCompat.getDrawable(getContext(), R.drawable.hk);
        ObjectAnimator a1 = ObjectAnimator.ofFloat(this, "rotationY", 0.0f, 90f);
        a1.setDuration(2000);
        a1.setRepeatCount(0);
        a1.setInterpolator(new AccelerateInterpolator());
        ObjectAnimator a2 = ObjectAnimator.ofFloat(this, "rotationY", 270f, 360f);
        a2.setDuration(2000);
        a2.setRepeatCount(0);
        a2.setInterpolator(new DecelerateInterpolator());
        a1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                draw = d;
                invalidate();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        as.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                frontShowing = true;
                lock = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        as.playSequentially(a1, a2);
        as.start();
    }

    private static boolean lock = false;
}
