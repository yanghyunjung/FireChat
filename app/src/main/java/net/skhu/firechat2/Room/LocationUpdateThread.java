package net.skhu.firechat2.Room;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import net.skhu.firechat2.Item.RoomMemberLocationItem;
import net.skhu.firechat2.Room.MemberLocation.RoomMemberLocationListActivity;

public class LocationUpdateThread implements Runnable {
    //View view;
    Context context;
    public LocationUpdateThread(Context context) {
        //this.view = view;
        this.context = context;
    }

    public void run() {
        RoomActivity activity = (RoomActivity)context;

        try {
            Thread.sleep(2000); //0.5초 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        activity.firebaseDbServiceForRoomMemberLocationList.updateUserSelf();
    }
}