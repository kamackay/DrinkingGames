package mobile.keithapps.customviews;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import mobile.keithapps.drinkinggames.DrinkingGamesGlobal;
import mobile.keithapps.drinkinggames.R;
import mobile.keithapps.drinkinggames.ridethebus.RideTheBusMain;

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
        draw = ContextCompat.getDrawable(getContext(), R.drawable.cardback);
        setClickable(true);
    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);
        draw.setBounds(0, 0, getWidth(), getHeight());
        draw.draw(c);
    }

    public void reset() {
        draw = ContextCompat.getDrawable(getContext(), R.drawable.cardback);
        this.frontShowing = false;
        setVisibility(View.VISIBLE);
        this.invalidate();
    }

    public boolean flipAndShowDialog(final AlertDialog dialog, final Drawable card) {
        return flip(card, dialog, null, null);
    }

    public boolean flip(final Drawable card) {
        return flip(card, null, null, null);
    }

    public boolean flipAndMoveOn(final Drawable card, RideTheBusMain rtb, RideTheBusMain.State s) {
        return flip(card, null, s, rtb);
    }

    public synchronized boolean flip(final Drawable card, final AlertDialog dialog, final RideTheBusMain.State s, final RideTheBusMain rtb) {
        if (frontShowing) {
            setVisibility(View.GONE);
            setClickable(false);
            return false;
        }
        if (lock) return false;
        lock = true;
        AnimatorSet as = new AnimatorSet();
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
                draw = card;
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
                if (s != null && rtb != null) {
                    try {
                        rtb.setState(s);
                    } catch (Exception e) {
                        DrinkingGamesGlobal.logException(e);
                    }
                }
                if (dialog != null) {
                    try {
                        dialog.show();
                    } catch (Exception e) {
                        DrinkingGamesGlobal.logException(e);
                    }
                }
                reset();
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
}
