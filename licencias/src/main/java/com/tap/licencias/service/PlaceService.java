package com.tap.licencias.service;

import com.tap.licencias.repository.PlaceRepository;
import com.tap.licencias.exception.LoginUserException;
import com.tap.licencias.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    public Place getPlace(int id) throws LoginUserException {
        return placeRepository.findById(id).orElseThrow(LoginUserException::new);
    }

    public void createPlace(Place place){

        placeRepository.save(place);
    }
}
