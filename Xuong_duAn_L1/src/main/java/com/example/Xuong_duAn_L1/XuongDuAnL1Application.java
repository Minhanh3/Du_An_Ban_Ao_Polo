package com.example.Xuong_duAn_L1;

import com.example.Xuong_duAn_L1.entity.Color;
import com.example.Xuong_duAn_L1.entity.InitializationFlag;
import com.example.Xuong_duAn_L1.entity.Size;
import com.example.Xuong_duAn_L1.repository.ColorRepo;
import com.example.Xuong_duAn_L1.repository.InitializationFlagRepo;
import com.example.Xuong_duAn_L1.repository.SizeRepo;
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
    SizeRepo sizeRepo;

    public static void main(String[] args) {
        SpringApplication.run(XuongDuAnL1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.printf("Hello Word");
        // chạy xong cmt lại all dòng dưới
        /*

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

         */
    }
}
