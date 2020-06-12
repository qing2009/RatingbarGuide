package com.xys.ratingbarguide;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class RatingBarView extends LinearLayout {

    public interface OnRatingListener {
        void onRating(Object bindObject, int RatingScore);
    }

    private boolean mClickable = true;
    private OnRatingListener onRatingListener;
    private Object bindObject;
    private float starImageSize;
    private float marginRight;
    private int starCount;
    private float rating;
    private Drawable starEmptyDrawable;
    private Drawable starHalfDrawable;
    private Drawable starFillDrawable;
    private int mStarCount;

    public void setClickable(boolean clickable) {
        this.mClickable = clickable;
    }

    public RatingBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RatingBarView);
        starImageSize = ta.getDimension(R.styleable.RatingBarView_starImageSize, 20);
        marginRight = ta.getDimension(R.styleable.RatingBarView_marginRight, 10);
        starCount = ta.getInteger(R.styleable.RatingBarView_starCount, 5);
        rating = ta.getFloat(R.styleable.RatingBarView_rating,0);
        starEmptyDrawable = ta.getDrawable(R.styleable.RatingBarView_starEmpty);
        starHalfDrawable = ta.getDrawable(R.styleable.RatingBarView_starHalf);
        starFillDrawable = ta.getDrawable(R.styleable.RatingBarView_starFill);
        ta.recycle();
        for (int i = 0; i < starCount; i++) {

            ImageView imageView = new ImageView(context);
            ViewGroup.LayoutParams para = new ViewGroup.LayoutParams(Math.round(starImageSize), Math.round(starImageSize));
            imageView.setLayoutParams(para);
            // TODO:you can change gap between two stars use the padding
            imageView.setPadding(0, 0, (int)marginRight, 0);

            imageView.setMaxWidth(10);
            imageView.setMaxHeight(10);
            // 绘制选中的星星
            if (i < rating) {
                // 比当前选中的数量小
                if (i < (int)rating) {
                    imageView.setImageDrawable(starFillDrawable);
                }else if (i+0.5 == rating)  {
                    imageView.setImageDrawable(starHalfDrawable);
                }else {
                    imageView.setImageDrawable(starEmptyDrawable);
                }
            } else {
                // 绘制正常的星星
                imageView.setImageDrawable(starEmptyDrawable);
            }

            addView(imageView);
        }

        }



    public int getStarCount() {
        return mStarCount;
    }

    public void setStarFillDrawable(Drawable starFillDrawable) {
        this.starFillDrawable = starFillDrawable;
    }

    public void setStarEmptyDrawable(Drawable starEmptyDrawable) {
        this.starEmptyDrawable = starEmptyDrawable;
    }

    public void setStarCount(int startCount) {
        this.starCount = starCount;
    }

    public void setStarImageSize(float starImageSize) {
        this.starImageSize = starImageSize;
    }

    public void setBindObject(Object bindObject) {
        this.bindObject = bindObject;
    }

    public void setOnRatingListener(OnRatingListener onRatingListener) {
        this.onRatingListener = onRatingListener;
    }
}
