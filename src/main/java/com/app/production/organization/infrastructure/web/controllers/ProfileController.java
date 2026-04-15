package com.app.production.organization.infrastructure.web.controllers;

import com.app.production.organization.application.services.ProfileService;
import com.app.production.organization.infrastructure.web.dtos.profile.ProfileDto;
import com.app.production.organization.infrastructure.web.dtos.profile.ProfileResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/")
    public ResponseEntity<ProfileResponseDto> create(@RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.create(profileDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDto> update(@PathVariable UUID profileId, @RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.update(profileId, profileDto), HttpStatus.OK);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDto> getById(@PathVariable UUID profileId) {
        return new ResponseEntity<>(profileService.getById(profileId), HttpStatus.OK);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> delete(@PathVariable UUID profileId) {
        profileService.delete(profileId);
        return ResponseEntity.noContent().build();
    }
}
