package net.skhu.firechat2.Item;

import java.io.Serializable;

public class RoomItem implements Serializable {//Serializable마킹 인터페이스, 이게 있어야, Activity끼리 객체를 전달할 수 있습니다.
    String roomName;
    String roomMemberLocationKey;//roomName과 이름이 같으면 안되기에, key로 두었습니다.
    String roomNameKey;
    //String password;//비밀번호 웬만하면 서버에서 확인할 것.

    public String getRoomNameKey() {
        return roomNameKey;
    }

    public void setRoomNameKey(String roomNameKey) {
        this.roomNameKey = roomNameKey;
    }

    public RoomItem(){

    }

    public String getRoomMemberLocationKey() {
        return roomMemberLocationKey;
    }

    public void setRoomMemberLocationKey(String roomMemberLocationKey) {
        this.roomMemberLocationKey = roomMemberLocationKey;
    }

    public RoomItem(String roomName){
        this.roomName = roomName;
    }

    public String getRoomName(){
        return roomName;
    }

    public void setRoomName(String roomName){
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return String.format("(%s)", roomName);
    }
}
