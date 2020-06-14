package net.skhu.firechat2.Room.Thread;

import android.content.Context;

import net.skhu.firechat2.ListenerInterface.RoomChatListener.OnAllRemoveListener;
import net.skhu.firechat2.ListenerInterface.RoomLocationListener.OnUpdateUserSelfListener;

public class CloseRoomThread implements Runnable {
    private volatile boolean shutdown = false;
    long sleepMillis;
    OnAllRemoveListener onAllRemoveListener;
    public CloseRoomThread(OnAllRemoveListener onAllRemoveListener) {
        this.onAllRemoveListener = onAllRemoveListener;
    }

    @Override
    public void run() {

        while(true) {
            try {
                Thread.sleep(sleepMillis); //0.5초 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized(this) {
                if (shutdown) {
                    onAllRemoveListener.onAllRemoveListener();
                    break;
                }
            }
        }

        //activity.firebaseDbServiceForRoomMemberLocationList.updateUserSelf();
    }

    public void cancel() {

        synchronized (this) {
            this.shutdown = true;
        }
    }
}