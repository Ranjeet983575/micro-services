package com.arg.user.user.services;

import com.arg.user.user.entities.ProfileEntity;

import java.util.UUID;

public interface ProfileService {

    ProfileEntity getUserProfile(UUID profileId);
}
