package com.arg.user.user.services.impl;

import com.arg.user.user.entities.ProfileEntity;
import com.arg.user.user.exception.ResourceNotFoundException;
import com.arg.user.user.respositories.ProfileRepository;
import com.arg.user.user.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Override
    public ProfileEntity getUserProfile(UUID profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile Not Found "));
    }
}
