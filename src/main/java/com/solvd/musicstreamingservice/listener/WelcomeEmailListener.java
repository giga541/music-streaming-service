package com.solvd.musicstreamingservice.listener;

import com.solvd.musicstreamingservice.model.User;

public class WelcomeEmailListener implements UserEventListener {

    @Override
    public void onUserRegistered(User user) {
        System.out.println("Welcome email sent to: " + user.getEmail());
    }
}