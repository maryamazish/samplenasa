package com.azish.nasa.service;

import com.azish.nasa.presentation.controller.BusinessException;
import com.azish.nasa.presentation.model.NasaModel;
import com.azish.nasa.presentation.model.PhotoList;
import org.springframework.stereotype.Service;


@Service
public interface NasaService {
    /**
     * get data af nasa api
     *
     * @param apiNasaURI
     * @param nasaModel
     * @return
     */
    PhotoList getData(String uri, NasaModel nasaModel);

    /**
     * Create Log in database
     *
     * @param createModel
     */
    void createLog(CreateModel createModel);
}
