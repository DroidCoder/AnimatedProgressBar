/*
    Copyright (c) 2014, Athanasios Karpouzis (http://droid-coder.com)  All rights reserved.
    Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
    1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
    2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
    3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.droidcoder.androidlibs.animatedprogressbar.animatedprogressbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by athanasioskarpouzis on 4/19/14.
 */
public class AnimationTimer{
    private static AnimationTimer ourInstance;

    private Timer timer;
    private ConcurrentHashMap<Integer, TimerTickListener> listeners;


    public static synchronized  AnimationTimer getInstance() {
        if(ourInstance == null){
            ourInstance = new AnimationTimer();
        }
        return ourInstance;
    }


    public boolean addListener(TimerTickListener timerTickListener){
        TimerTickListener v = listeners.put(timerTickListener.hashCode(), timerTickListener);
        if( (timer == null) || ((v == null) && (listeners.size() == 1))){
            timer = new Timer();
            timer.scheduleAtFixedRate(new AnimationTimerTask(), 0, 100);
        }
        return (v == null);
    }

    public boolean removeListener(TimerTickListener timerTickListener){
        TimerTickListener v =  listeners.remove(timerTickListener.hashCode());
        if((v != null) && (listeners.size() == 0)){
            timer.cancel();
            timer.purge();
            timer = null;
        }
        return (v != null);
    }


    private AnimationTimer() {
        listeners = new ConcurrentHashMap<Integer, TimerTickListener>();

    }


    private class AnimationTimerTask extends TimerTask{
        @Override
        public void run() {
               for(TimerTickListener listener : listeners.values()){
                    listener.onTimerTick();
               }
        }
    }

}
