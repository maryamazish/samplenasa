package com.azish.nasa.presentation.controller;

import com.azish.nasa.presentation.model.NasaModel;
import com.azish.nasa.presentation.model.PhotoList;
import com.azish.nasa.service.NasaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/nasa")
@Slf4j
public class NasaController {

    @Value("${api.nasa.URI}")
    private String api_nasa_URI;

    @Autowired
    @Qualifier("NasaServiceImpl")
    NasaService nasaService;

    /**
     * get full information json (Querying by Martian sol)
     *
     * @param sol
     * @param camera
     * @param page
     * @param api_key
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/data")
    public PhotoList getData(@RequestParam("sol") Integer sol, @RequestParam("camera") String camera
            , @RequestParam("page") Integer page, @RequestParam("api_key") String api_key) throws Exception {

        NasaModel nasaModel = new NasaModel();
        nasaModel.setSol(sol);
        nasaModel.setCamera(camera);
        nasaModel.setPage(page);
        nasaModel.setApi_key(api_key);

        PhotoList photoList = nasaService.getData(api_nasa_URI, nasaModel);

        return photoList;
    }

    /**
     * get Image's url(Querying by Martian sol)
     *
     * @param sol
     * @param camera
     * @param page
     * @param api_key
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/photo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<URL> getPhoto(@RequestParam("sol") Integer sol, @RequestParam("camera") String camera
            , @RequestParam("page") Integer page, @RequestParam("api_key") String api_key) throws Exception {

        PhotoList photoList = this.getData(sol, camera, page, api_key);
        List<URL> allPhoto = Arrays.stream(photoList.getPhotos()).map(photo -> photo.getImgSrc()).collect(Collectors.toList());
        log.info("allPhoto : " + allPhoto);
        return allPhoto;
    }

}
