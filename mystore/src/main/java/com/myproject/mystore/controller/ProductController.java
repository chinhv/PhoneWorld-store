package com.myproject.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myproject.mystore.dto.ProductDto;
import com.myproject.mystore.dto.SearchObject;
import com.myproject.mystore.entities.Product;
import com.myproject.mystore.service.FilesStorageService;
import com.myproject.mystore.service.ProductService;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private FilesStorageService filesStorageService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllProducts(
        @RequestParam(defaultValue = "0", value = "page") String page,
        @RequestParam(defaultValue = "10", value = "size") String pageSize
    ){
        List<Product> products = new ArrayList<>();
        Page<Product> pageTuts = service.getAllProducts(Integer.parseInt(page), Integer.parseInt(pageSize));
        products = pageTuts.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalItems", pageTuts.getTotalElements());
        response.put("totalPage", pageTuts.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable int id){
        return service.getProductById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>>findProductsByKeyword(
        @RequestParam(defaultValue = "") String keywords,
        @RequestParam(defaultValue = "") String categoryName,
        @RequestParam(defaultValue = "") String manufacturerName,
        @RequestParam(defaultValue = "") String sort,
        @RequestParam(defaultValue = "") String range,
        @RequestParam(defaultValue = "0") String page,
        @RequestParam(defaultValue = "10") String pageSize
    ){
        SearchObject obj = new SearchObject();
        obj.setKeywords(keywords.split(" "));
        obj.setCategoryName(categoryName);
        obj.setManufacturerName(manufacturerName);
        obj.setSort(sort);
        obj.setPrice(range);
        List<Product> products = new ArrayList<>();
        Page<Product> pageTuts;
        pageTuts = service.getProductsByKeywords(obj, Integer.parseInt(page), Integer.parseInt(pageSize));
        products = pageTuts.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalItems", pageTuts.getTotalElements());
        response.put("totalPage", pageTuts.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/options")
    public ResponseEntity<Map<String, Object>> findProductsByManufacturerAndCategory(
        @RequestParam(defaultValue = "") String categoryName,
        @RequestParam(defaultValue = "") String manufacturerName,
        @RequestParam(defaultValue = "0") String page, 
        @RequestParam(defaultValue = "10") String pageSize
    ){
        SearchObject obj = new SearchObject();
        obj.setCategoryName(categoryName);
        obj.setManufacturerName(manufacturerName);
        List<Product> products = new ArrayList<>();
        Page<Product> pageTuts;
        pageTuts = service.getProductsByManufacturerNameAndCategory(obj, Integer.parseInt(page), Integer.parseInt(pageSize));
        products = pageTuts.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalItems", pageTuts.getTotalElements());
        response.put("totalPage", pageTuts.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@ModelAttribute ProductDto dto){
        MultipartFile productImage = dto.getImageFile();
        String fileName = filesStorageService.save(productImage);
        String loadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/product")
                .path("/loadFile/")
                .path(fileName)
                .toUriString();
        dto.setImgPath(loadUri);
        service.saveProduct(dto);
        return new ResponseEntity<>("Saved !", HttpStatus.OK);
    }

    @GetMapping("/loadFile/{fileName}")
    public ResponseEntity<Resource> loadFile(@PathVariable String fileName, HttpServletRequest request){
        Resource resource = filesStorageService.load(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        return service.deleteProduct(id); 
    }

}
