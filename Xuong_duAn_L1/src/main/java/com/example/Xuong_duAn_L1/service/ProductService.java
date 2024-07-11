package com.example.Xuong_duAn_L1.service;

import com.example.Xuong_duAn_L1.entity.*;
import com.example.Xuong_duAn_L1.repository.*;
import com.example.Xuong_duAn_L1.service.impl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private BrandRepo brandRepo;
    @Autowired
    private MaterialRepo materialRepo;
    @Autowired
    private StyleRepo styleRepo;
    @Autowired
    private ImageRepo imageRepo;

    @Override
    public List<Product> findAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Product findProductById(int id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public Product addProduct(Product product,
                              Integer idStyle,
                              Integer idImage,
                              Integer idMaterial,
                              Integer idBrand) {
        Brand brandOptional = brandRepo.findById(idStyle).orElse(null);
        Material materialOptional = materialRepo.findById(idImage).orElse(null);
        Style styleOptional = styleRepo.findById(idMaterial).orElse(null);
        Image imageOptional = imageRepo.findById(idBrand).orElse(null);
        boolean flag = false;
        if (brandOptional != null) {
            product.setIdBrand(1);
            flag = true;
        }
        if (!flag) {
            product.setBrand(brandOptional);
        }
        product.setMaterial(materialOptional);
        product.setStyle(styleOptional);
        product.setImage(imageOptional);
        product.setUploadDate(LocalDate.now());

        return productRepo.save(product);
    }

    @Override
    public Page<Product> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepo.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {

    }

}
