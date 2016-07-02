package mobile.keithapps.customviews;

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

import mobile.keithapps.drinkinggames.R;

public class CardView extends ImageView {
    private static boolean lock = false;
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
        draw = ContextCompat.getDrawable(getContext(), R.drawable.cardback_nologo);
        setClickable(true);
    }


    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);
        float h = getHeight() * .72f, w = getWidth() * .72f;
        if ((h * (2f / 3f)) > w) h = w * 3f / 2f;
        else w = h * 2f / 3f;
        draw.setBounds((int) ((getWidth() - w) / 2),
                (int) ((getHeight() - h) / 2),
                (int) (getWidth() - (getWidth() - w) / 2),
                (int) (getHeight() - (getHeight() - h) / 2));
        draw.draw(c);
    }

    public void reset() {
        draw = ContextCompat.getDrawable(getContext(), R.drawable.cardback);
        this.frontShowing = false;
        setVisibility(View.VISIBLE);
        this.invalidate();
    }

    public boolean flipThen(final Drawable card, final Runnable runnable) {
        if (frontShowing) {
            setVisibility(View.GONE);
            setClickable(false);
            return false;
        }
        AnimatorSet as = new AnimatorSet();
        ObjectAnimator a1 = ObjectAnimator.ofFloat(this, "rotationY", 0.0f, 90f);
        a1.setDuration(1000);
        a1.setRepeatCount(0);
        a1.setInterpolator(new AccelerateInterpolator());
        ObjectAnimator a2 = ObjectAnimator.ofFloat(this, "rotationY", 270f, 360f);
        a2.setDuration(1000);
        a2.setRepeatCount(0);
        a2.setInterpolator(new DecelerateInterpolator());
        a1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setImageDrawable(card);
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
                runnable.run();
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
        return true;
    }

    /**
     * Sets a drawable as the content of this ImageView.
     *
     * @param drawable the Drawable to set, or {@code null} to clear the
     *                 content
     */
    @Override
    public void setImageDrawable(Drawable drawable) {
        draw = drawable;
        invalidate();
    }
}
