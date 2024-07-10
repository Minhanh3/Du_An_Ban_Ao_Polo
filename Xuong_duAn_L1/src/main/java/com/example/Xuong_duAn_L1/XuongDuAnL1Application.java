package com.example.Xuong_duAn_L1;

import com.example.Xuong_duAn_L1.entity.*;
import com.example.Xuong_duAn_L1.repository.*;
import com.example.Xuong_duAn_L1.util.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class XuongDuAnL1Application implements CommandLineRunner {
    @Autowired
    private ColorRepo colorRepo;
    @Autowired
    private SizeRepo sizeRepo;
    @Autowired
    private ImageRepo imageRepo;
    @Autowired
    private StyleRepo styleRepo;

    public static void main(String[] args) {
        SpringApplication.run(XuongDuAnL1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.printf("Hello Word");
        // chạy 1 lần xong cmt lại all dòng dưới

        String[] style = {"Classic Fit" , "Slim Fit", "Modern Fit", "Relaxed Fit", "Performance Polo", "Long Sleeve Polo", "Rugby Polo", "Contrast Collar", "Striped Polo"};
        for (String style1 : style) {
            Style style2 = new Style();
            style2.setCode("S_"+CodeGenerator.generateRandomCode(3));
            style2.setName(style1);
            style2.setUploadDate(LocalDate.now());
            style2.setStatus(1);
            styleRepo.save(style2);
        }
        String[] names = {"white", "black", "wine", "yellow", "olive", "navy"};
        for (String name : names) {
            Color color = new Color();
            color.setCode("C_" + CodeGenerator.generateRandomCode(3));
            color.setName(name);
            color.setUploadDate(LocalDate.now());
            color.setStatus(1);
            colorRepo.save(color);
        }
        String[] sizeNae = {"XS", "S", "M", "XL", "XXL", "L"};
        for (String name : sizeNae) {
            Size size = new Size();
            size.setCode("S_" + CodeGenerator.generateRandomCode(3));
            size.setName(name);
            size.setUploadDate(LocalDate.now());
            size.setStatus(1);
            sizeRepo.save(size);
        }
        for (int i = 0; i < 1; i++) {
            Image image = new Image();
            image.setCode("I001");
            image.setName("Anh se");
            image.setUploadDate(LocalDate.now());
            image.setStatus(1);
            imageRepo.save(image);
        }


    }
}
