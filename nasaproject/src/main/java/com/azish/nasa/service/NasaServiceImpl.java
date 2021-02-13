package com.azish.nasa.service;

import com.azish.nasa.entity.Nasa;
import com.azish.nasa.aop.LogExecutionTime;
import com.azish.nasa.presentation.controller.BusinessException;
import com.azish.nasa.presentation.model.NasaModel;
import com.azish.nasa.presentation.model.PhotoList;
import com.azish.nasa.repository.NasaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;


@Service
@Qualifier("NasaServiceImpl")
public class NasaServiceImpl implements NasaService {

    private static final String BUSINESS_EXCEPTION_NASA_API = "BUSINESS_EXCEPTION.NASA_API";
    //private static final String BUSINESS_EXCEPTION_NATIONAL_CODE_DUPLICATE = "BUSINESS_EXCEPTION.NATIONAL_CODE_DUPLICATE";

    private final NasaRepository nasaRepository;

    public NasaServiceImpl(NasaRepository nasaRepository) {
        this.nasaRepository = nasaRepository;
    }


    @Override
    @LogExecutionTime
    public PhotoList getData(String uri, NasaModel nasaModel) {
        PhotoList photoList = new PhotoList();
        //اگر در زمان فراخوانی api خطایی رخ دهد پیغام خطای صادر شده لاگ میشود
        try {
            RestTemplate restTemplate = new RestTemplate();
            //String fianalUri = makeUrl(uri, nasaModel);
            ResponseEntity<PhotoList> photoListResponse = restTemplate.getForEntity(this.makeUrl(uri, nasaModel), PhotoList.class);
            photoList.setPhotos(photoListResponse.getBody().getPhotos());
        } catch (Exception ex) {
            throw new BusinessException(BUSINESS_EXCEPTION_NASA_API, ex.getMessage());
        }
        return photoList;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void createLog(CreateModel createModel) {
        Nasa nasa = new Nasa();
        nasa.setCreatedDate(new Date());
        nasa.setMethodName(createModel.getMethodName());
        nasa.setMilliseconds(createModel.getTotalTimeMillis());
        nasaRepository.save(nasa);
    }

    //make uri for call api
    private String makeUrl(String apiNasaURI, NasaModel nasaModel) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiNasaURI);

        if (!StringUtils.isEmpty(nasaModel.getSol())) {
            builder.queryParam("sol", nasaModel.getSol());
        }

        if (!StringUtils.isEmpty(nasaModel.getPage())) {
            builder.queryParam("page", nasaModel.getPage());
        }

        if (!StringUtils.isEmpty(nasaModel.getCamera())) {
            builder.queryParam("camera", nasaModel.getCamera());
        }

        if (!StringUtils.isEmpty(nasaModel.getEarth_date())) {
            builder.queryParam("earth_date", nasaModel.getEarth_date());
        }

        if (!StringUtils.isEmpty(nasaModel.getApi_key())) {
            builder.queryParam("api_key", nasaModel.getApi_key());
        }
        return builder.toUriString();
    }
}
