package com.android.mvp2.data.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    public User() {
    }

    private String GID;

    private String name;

    private String loginName;

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public static User generateUserFromJSONObject(JSONObject dataObj) throws JSONException {

        User user = new User();

        if (dataObj.has("user")) {
            JSONObject userObj = dataObj.getJSONObject("user");
            user.setGID(userObj.getString("GID"));
            user.setName(userObj.getString("name"));
            user.setLoginName(userObj.getString("login_name"));
        }

        return user;
    }
}
