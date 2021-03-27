package com.azish.nasa.presentation.controller;

import com.azish.nasa.presentation.model.NasaModel;
import com.azish.nasa.presentation.model.Photo;
import com.azish.nasa.presentation.model.PhotoList;
import com.azish.nasa.service.NasaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private String uri;

    private NasaService nasaService;

    @Autowired
    public NasaController(NasaService nasaService) {
        this.nasaService = nasaService;
    }

    /**
     * get full information json (Querying by Martian sol)
     *
     * @param sol     it use for call api
     * @param camera  it use for call api
     * @param page    it use for call api
     * @param api_key it use for call api
     * @return PhotoList
     */
    @GetMapping(value = "/data")
    public PhotoList getData(@RequestParam("sol") Integer sol, @RequestParam("camera") String camera
            , @RequestParam("page") Integer page, @RequestParam("api_key") String api_key) {

        NasaModel nasaModel = new NasaModel();
        nasaModel.setSol(sol);
        nasaModel.setCamera(camera);
        nasaModel.setPage(page);
        nasaModel.setApi_key(api_key);

        return nasaService.getData(uri, nasaModel);

    }

    /**
     * get Image's url(Querying by Martian sol)
     *
     * @param sol     it use for call api
     * @param camera  it use for call api
     * @param page    it use for call api
     * @param api_key it use for call api
     * @return List<URL>
     *
     *     sample of url
     *     http://localhost:8080/api/v1/nasa/photo/?sol=1000&page=2&api_key=DEMO_KEY&camera
     */
    @GetMapping(value = "/photo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<URL> getPhoto(@RequestParam("sol") Integer sol, @RequestParam("camera") String camera
            , @RequestParam("page") Integer page, @RequestParam("api_key") String api_key) {

        PhotoList photoList = this.getData(sol, camera, page, api_key);
        List<URL> allPhoto = Arrays.stream(photoList.getPhotos()).map(Photo::getImgSrc).collect(Collectors.toList());
        log.info("allPhoto : " + allPhoto);
        return allPhoto;
    }

}
