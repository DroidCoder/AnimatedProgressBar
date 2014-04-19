/*
    Copyright (c) 2014, Athanasios Karpouzis (http://droid-coder.com)  All rights reserved.
    Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
    1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
    2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
    3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.droidcoder.androidlibs.animatedprogressbar.animatedprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by athanasioskarpouzis on 4/19/14.
 */
public class AnimatedProgressBar extends ProgressBar{

    private static final String INSTANCE_STATE = "com.droidcoder.androidlibs.animatedprogressbar.INSTANCE_STATE";
    private static final String ANIMATE_ON_ATTACHED = "com.droidcoder.androidlibs.animatedprogressbar.ANIMATE_ON_ATTACHED";
    private static final String ANIMATION_LOOP = "com.droidcoder.androidlibs.animatedprogressbar.ANIMATION_LOOP";
    private static final String ANIMATE_SECONDARY = "com.droidcoder.androidlibs.animatedprogressbar.ANIMATE_SECONDARY";
    private static final String ANIMATE_SECONDARY_STEP = "com.droidcoder.androidlibs.animatedprogressbar.ANIMATE_SECONDARY_STEP";
    private static final String IS_ANIMATING = "com.droidcoder.androidlibs.animatedprogressbar.IS_ANIMATING";


    private boolean animateOnAttached;
    private boolean animationLoop;
    private boolean animateSecondary;
    private int animateSecondaryStep;

    private boolean animating = false;
    private boolean restored = false;

    private TimerTickListener myListener;


    public AnimatedProgressBar(Context context, AttributeSet attrs){
        super(context, attrs, android.R.style.Widget_ProgressBar_Horizontal);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.animatedprogressbar, 0, 0);

        try {
            animateOnAttached = a.getBoolean(R.styleable.animatedprogressbar_animate_when_first_show, true);
            animationLoop = a.getBoolean(R.styleable.animatedprogressbar_animation_loop, false);
            animateSecondary = a.getBoolean(R.styleable.animatedprogressbar_animate_secondary_process, true);
            animateSecondaryStep = a.getInt(R.styleable.animatedprogressbar_animate_secondary_process_step, 1);
        }catch (Exception e){

        }finally {
            a.recycle();
        }
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(animateOnAttached || (animating && restored)){
            startDefaultAnimation();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopDefaultAnimation();
    }

    public void startDefaultAnimation(){
        if((animating && (!restored))){
            restored = false;
            return;
        }
        animating = true;
        myListener = new TimerTickListener() {
            @Override
            public void onTimerTick() {
                setProgress(getProgress()+1);
                if(animateSecondary) {
                    if(getSecondaryProgress() <= getProgress()) {
                        setSecondaryProgress(getProgress() + animateSecondaryStep);
                    }
                }
                if(getProgress() >= getMax()){
                    if(animationLoop){
                        setProgress(0);
                        if(animateSecondary){
                            setSecondaryProgress(animateSecondaryStep);
                        }
                    }else {
                        stopDefaultAnimation();
                    }
                }
            }
        };
        AnimationTimer.getInstance().addListener(myListener);
    }

    public void stopDefaultAnimation(){
        if(!animating){
            return;
        }
        animating = false;
        AnimationTimer.getInstance().removeListener(myListener);
    }


    // save state
    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putBoolean(ANIMATE_ON_ATTACHED, animateOnAttached);
        bundle.putBoolean(ANIMATION_LOOP, animationLoop);
        bundle.putBoolean(ANIMATE_SECONDARY, animateSecondary);
        bundle.putInt(ANIMATE_SECONDARY_STEP, animateSecondaryStep);
        bundle.putBoolean(IS_ANIMATING, isAnimating());
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            state = bundle.getParcelable(INSTANCE_STATE);
            animateOnAttached = bundle.getBoolean(ANIMATE_ON_ATTACHED, true);
            animationLoop = bundle.getBoolean(ANIMATION_LOOP, false);
            animateSecondary = bundle.getBoolean(ANIMATE_SECONDARY, true);
            animateSecondaryStep = bundle.getInt(ANIMATE_SECONDARY_STEP, 1);
            animating = bundle.getBoolean(IS_ANIMATING, false);
            restored = true;
        }
        super.onRestoreInstanceState(state);

    }



    // getter setters
    public boolean isAnimating() {
        return animating;
    }
}
