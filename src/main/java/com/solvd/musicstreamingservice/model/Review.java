package com.solvd.musicstreamingservice.model;

import java.time.LocalDateTime;

public class Review {

    private User user;
    private Song song;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}
