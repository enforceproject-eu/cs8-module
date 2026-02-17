package org.n52.project.enforce.cs8.api.impl.data;

import java.io.IOException;

import org.n52.project.enforce.cs8.api.Cs8Api;
import org.n52.project.enforce.cs8.utils.Cs8Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-08T13:43:38.513185600+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
@Controller
@RequestMapping("${openapi.eNFORCECS5DataAccess.base-path:}")
public class Cs8ApiController implements Cs8Api {

    Cs8Utils cs8Utils;

    Cs8DataRepository cs5DataRepository;
    

    @Autowired
    public Cs8ApiController(Cs8Utils cs8Utils, Cs8DataRepository cs5DataRepository) {
        this.cs8Utils = cs8Utils;
        this.cs5DataRepository = cs5DataRepository;
    }

    @Override
    public ResponseEntity<?> addCs8DataAsBody(@Valid Resource requestBody) {
        try {
            cs8Utils.readExcelFile(requestBody.getInputStream());
        } catch (IOException e) {
           return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> getCs8Data() {
        return ResponseEntity.ok().body(cs5DataRepository.getGeoJson());
    }
}
