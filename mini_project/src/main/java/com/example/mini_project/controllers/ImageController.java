package com.example.mini_project.controllers;

import com.example.mini_project.Repository.ImageRepository;
import com.example.mini_project.Services.ImageService;
import com.example.mini_project.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping(path = "image")
public class ImageController {
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ImageService imageService;
    @PostMapping("/upload")
    public ResponseEntity.BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {

        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        Image img = new Image(file.getOriginalFilename(), file.getContentType(),
                imageService.compressBytes(file.getBytes()));
        imageRepository.save(img);
        return ResponseEntity.status(HttpStatus.OK);
    }

    @GetMapping(path = { "/get/{imageId}" })
    public Image getImage(@PathVariable("imageId") long imageId) throws IOException {

        final Optional<Image> retrievedImage = imageRepository.findById(imageId);
        Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(),
                imageService.decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }


    // compress the image bytes before storing it in the database

}
