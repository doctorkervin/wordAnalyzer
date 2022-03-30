package com.lybank.controller.system;

import com.lybank.service.system.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload")
public class FileSystemStorageController {
    @Autowired
    private FileSystemStorageService  fileSystemStorageService;

    @PostMapping("/doc")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file){
       return fileSystemStorageService.store(file);
    }
    @GetMapping(value = "/view")
    public ModelAndView uploadView() {
        return new ModelAndView( "doc_upload");
    }

    @GetMapping(value = "/scanView")
    public ModelAndView uploadScanView() {
        return new ModelAndView( "doc_scan");
    }

    @PostMapping("/scan")
    @ResponseBody
    public String scan(@RequestParam("file") MultipartFile file){
        return fileSystemStorageService.scan2(file);
    }


}
