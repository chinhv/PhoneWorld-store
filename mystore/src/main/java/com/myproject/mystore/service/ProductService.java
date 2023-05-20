package com.myproject.mystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myproject.mystore.entities.QProduct;
import com.myproject.mystore.dto.ProductDto;
import com.myproject.mystore.dto.SearchObject;
import com.myproject.mystore.entities.Product;
import com.myproject.mystore.repository.CategoryRepository;
import com.myproject.mystore.repository.ManufacturerRepository;
import com.myproject.mystore.repository.ProductRepository;
import com.querydsl.core.BooleanBuilder;



@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public Product convertProductDtoToProduct(ProductDto dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setRam(dto.getRam());
        product.setCpu(dto.getCpu());
        product.setBattery(dto.getBattery());
        product.setCategory(categoryRepository.findById(dto.getCategoryId()).get());
        product.setManufacturer(manufacturerRepository.findById(dto.getManufacturerId()).get());
        product.setImgPath(dto.getImgPath());
        return product;
    }

    public Page<Product> getAllProducts(int page, int pageSize) {
        return productRepo.findAll(PageRequest.of(page, pageSize));
    }

    public Product getProductById(int id){
        return productRepo.findById(id).orElse(null);
    }

    public Page<Product> getProductsByKeywords(SearchObject obj, int page, int pageSize){
        BooleanBuilder builder = new BooleanBuilder();
        String[] keywords = obj.getKeywords();
        String price = obj.getPrice();
        String sort = obj.getSort();
        String categoryName = obj.getCategoryName();
        String manufacturerName = obj.getManufacturerName();
        builder.and(QProduct.product.name.like("%" + keywords[0] + "%"));
        for(int i = 1; i < keywords.length; i++){
            builder.and(QProduct.product.name.like("%" + keywords[i] + "%"));
        }

        switch(price){
            case "Dưới 2 triệu":
                builder.and(QProduct.product.price.lt(2000000));
            case "Từ 2 đến 4 triệu":
                builder.and(QProduct.product.price.between(2000000, 4000000));
            case "Từ 4 đến 6 triệu":
                builder.and(QProduct.product.price.between(4000000, 6000000));
            case "Từ 6 đến 8 triệu":
                builder.and(QProduct.product.price.between(6000000, 8000000));
            case "Từ 8 đến 10 triệu":
                builder.and(QProduct.product.price.between(8000000,10000000));
            case "Từ 10 đến 20 triệu":
                builder.and(QProduct.product.price.between(10000000, 20000000));
            case "Trên 20 triệu":
                builder.and(QProduct.product.price.gt(20000000));
            default:
                break;
        }

        if(!categoryName.equals("")){
            builder.and(QProduct.product.category.CategoryName.eq(categoryName));
        }
        if(!manufacturerName.equals("")){
            builder.and(QProduct.product.manufacturer.manufacturerName.eq(manufacturerName));
        }

        if(sort.equals("Mới nhất")){
           return productRepo.findAll(builder, PageRequest.of(page, pageSize, Sort.Direction.DESC, "id")); 
        }else if(sort.equals("Giá tăng dần")){
            return productRepo.findAll(builder, PageRequest.of(page, pageSize, Sort.Direction.ASC, "price"));
        }else if(sort.equals("Giá giảm dần")){
            return productRepo.findAll(builder, PageRequest.of(page, pageSize, Sort.Direction.DESC, "price"));
        }
        return productRepo.findAll(builder, PageRequest.of(page, pageSize));
    }

    public Product saveProduct(ProductDto productDto){
        return productRepo.save(convertProductDtoToProduct(productDto));
    }

    

    public Page<Product> getProductsByManufacturerNameAndCategory(SearchObject obj, int page, int pageSize){
        String categoryName = obj.getCategoryName();
        String manufacturerName = obj.getManufacturerName();
        BooleanBuilder builder = new BooleanBuilder();
        if(!categoryName.equals("")){
            builder.and(QProduct.product.category.CategoryName.eq(categoryName));
        }
        if(!manufacturerName.equals("")){
            builder.and(QProduct.product.manufacturer.manufacturerName.eq(manufacturerName));
        }
        return productRepo.findAll(builder, PageRequest.of(page, pageSize));
    }

    
    

    public String deleteProduct(int id){
        productRepo.deleteById(id);
        return "delete done !";
    }

    public Product updateProduct(Product product){
        Product newProduct = productRepo.findById(product.getId()).orElse(null);
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setCpu(product.getCpu());
        newProduct.setBattery(product.getBattery());
        newProduct.setRam(product.getRam());
        return productRepo.save(newProduct);
    }
}
