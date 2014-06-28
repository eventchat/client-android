package com.eventchat.observer;

import java.util.HashMap;
import java.util.HashSet;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public final class ObserverMap {

    private static ObserverMap sObserverMap = new ObserverMap();

    private HashMap<ISubject, HashSet<IObserver>> mMap = new HashMap<ISubject, HashSet<IObserver>>();

    private ObserverHandler mHandler = new ObserverHandler(
            Looper.getMainLooper());

    private ObserverMap() {

    }

    public static ObserverMap getInstance() {
        return sObserverMap;
    }

    public boolean register(ISubject inSubject, IObserver inObserver) {
        if (mMap != null) {
            HashSet<IObserver> hashSet = mMap.get(inSubject);
            if (hashSet == null) {
                hashSet = new HashSet<IObserver>();
            }
            hashSet.add(inObserver);
            mMap.put(inSubject, hashSet);
        } else {
            return false;
        }
        return true;
    }

    public boolean unregister(ISubject inSubject, IObserver inObserver) {
        if (mMap != null) {
            HashSet<IObserver> hashSet = mMap.get(inSubject);
            if (hashSet == null) {
                return false;
            }
            return hashSet.remove(inObserver);
        } else {
            return false;
        }
    }

    public void notify(ISubject inSubject, Message inMessage) {
        mHandler.setSubject(inSubject);
        mHandler.sendMessage(inMessage);
    }

    private void doNotify(ISubject inSubject, Message inMessage) {
        if (inSubject != null && inMessage != null && mMap != null) {
            HashSet<IObserver> hashSet = mMap.get(inSubject);
            for (IObserver observer : hashSet) {
                observer.update(inMessage);
            }
        }
    }

    private class ObserverHandler extends Handler {
        private ISubject mSubject = null;

        public ObserverHandler(Looper loop) {
            super(loop);
        }

        public void setSubject(ISubject inSubject) {
            mSubject = inSubject;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            doNotify(mSubject, msg);
        }
    }
}
