package org.weka.web.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.weka.web.application.service.FileService;
import org.weka.web.application.service.SMOService;
import org.weka.web.application.service.impl.FileServiceImpl;
import org.weka.web.application.service.impl.SMOServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suad on 9/10/2014.
 */
@RestController
@RequestMapping(value = "/files")
public class FilesController {
    @Autowired
    FileService fileService;

    @Autowired
    SMOService smoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllFiles(){
        List<String> tempList=fileService.getAllFileNames();
        return new ResponseEntity<List<String>>(tempList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{fileName}",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getListOfAttributes(@PathVariable("fileName") String fileName){

        try {

            List<String> tempList = smoService.GetAttributes(fileName);
            return new ResponseEntity<List<String>>(tempList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.BAD_REQUEST);
        }

    }
}
