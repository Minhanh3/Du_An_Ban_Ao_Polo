package com.example.Xuong_duAn_L1.service;

import com.example.Xuong_duAn_L1.entity.*;
import com.example.Xuong_duAn_L1.entity.dto.ProductDto;
import com.example.Xuong_duAn_L1.repository.*;
import com.example.Xuong_duAn_L1.service.impl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private ProductDetailRepo productDetailRepo;

    @Override
    public List<Product> findAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Product findProductById(int id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public void addProduct(ProductDto productDto,
                           Integer idStyle,
                           Integer idImage,
                           Integer idMaterial,
                           Integer idBrand) {
        Brand brandOptional = brandRepo.findById(idStyle).orElse(null);
        Material materialOptional = materialRepo.findById(idImage).orElse(null);
        Style styleOptional = styleRepo.findById(idMaterial).orElse(null);
        Image imageOptional = imageRepo.findById(idBrand).orElse(null);
        Product product = new Product();
        if (brandOptional != null) {
            product.setIdBrand(productDto.getIdBrand());
        }
        if (imageOptional == null) {
            product.setIdImage(1);
        }
        MultipartFile image = productDto.getImageProduct();
        if (image != null && !image.isEmpty()) {
            Date uploadDate = new Date();

            String storageFileName = uploadDate.getTime() + "_" + image.getOriginalFilename();
            try {
                String uploadDir = "public/images/";
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Path filePath = uploadPath.resolve(storageFileName);
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                product.setBrand(brandOptional);
                product.setIdBrand(productDto.getIdBrand());
                product.setMaterial(materialOptional);
                product.setIdMaterial(productDto.getIdMaterial());
                product.setStyle(styleOptional);
                product.setIdStyle(productDto.getIdStyle());
                product.setImage(imageOptional);
                product.setIdImage(productDto.getIdImage());

                product.setCode(productDto.getCode());
                product.setName(productDto.getName());
                product.setImageProduct(storageFileName);
                product.setUploadDate(LocalDate.now());
                product.setStatus(productDto.getStatus());

                productRepo.save(product);
            } catch (IOException e) {
                e.printStackTrace();
                // Xử lý ngoại lệ tại đây (ví dụ: ghi log hoặc ném ngoại lệ tùy chỉnh)
            }
        }
    }

    @Override
    public Page<Product> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepo.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()) {
            productDetailRepo.deleteById(id);
            productRepo.deleteById(id);
        }
    }
}
