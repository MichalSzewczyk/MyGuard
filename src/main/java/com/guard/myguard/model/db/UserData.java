package com.guard.myguard.model.db;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "nick",
        "ice_phone",
        "user_phone",
        "password"
})
public class UserData {
    @JsonProperty("nick")
    private String nick;
    @JsonProperty("ice_phone")
    private String icePhone;
    @JsonProperty("user_phone")
    private String userPhone;

    @JsonProperty("nick")
    public String getNick() {
        return nick;
    }

    @JsonProperty("nick")
    public void setNick(String nick) {
        this.nick = nick;
    }

    @JsonProperty("ice_phone")
    public String getIcePhone() {
        return icePhone;
    }

    @JsonProperty("ice_phone")
    public void setIcePhone(String icePhone) {
        this.icePhone = icePhone;
    }

    @JsonProperty("user_phone")
    public String getUserPhone() {
        return userPhone;
    }

    @JsonProperty("user_phone")
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
