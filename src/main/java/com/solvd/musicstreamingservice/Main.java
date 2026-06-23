package com.solvd.musicstreamingservice;

import com.solvd.musicstreamingservice.decorator.LoggingUserRepository;
import com.solvd.musicstreamingservice.facade.MusicServiceFacade;
import com.solvd.musicstreamingservice.factory.MyBatisRepositoryFactory;
import com.solvd.musicstreamingservice.factory.RepositoryFactory;
import com.solvd.musicstreamingservice.listener.StreamHistoryListener;
import com.solvd.musicstreamingservice.listener.WelcomeEmailListener;
import com.solvd.musicstreamingservice.model.*;
import com.solvd.musicstreamingservice.persistence.UserRepository;
import com.solvd.musicstreamingservice.persistence.impl.UserMapperImpl;
import com.solvd.musicstreamingservice.service.ArtistService;
import com.solvd.musicstreamingservice.service.UserService;
import com.solvd.musicstreamingservice.strategy.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.ServiceLoader;

public class Main {

    public static void main(String[] args) {


    }
}