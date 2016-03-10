package com.voodoo94.app.technieks2015;

/**
 * Created by Shreesha on 3/5/2015.
 */
public class RowItem {
    private String mEventName;
    private String mImgUrl;

    public RowItem(String event_name, String event_url) {
        this.mEventName = event_name;
        this.mImgUrl = event_url;
    }

    public String getImgUrl() {
        return mImgUrl;
    }

    public String getEventName() {
        return mEventName;
    }

    public void setEventDesc(String event_desc) {
        this.mEventName = event_desc;
    }

    public void setImgUrl(String img_url) {
        this.mImgUrl = img_url;
    }
}
