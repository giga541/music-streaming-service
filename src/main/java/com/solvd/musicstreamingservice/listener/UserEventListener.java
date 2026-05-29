package com.solvd.musicstreamingservice.listener;

import com.solvd.musicstreamingservice.model.User;

public interface UserEventListener {

    void onUserRegistered(User user);
}