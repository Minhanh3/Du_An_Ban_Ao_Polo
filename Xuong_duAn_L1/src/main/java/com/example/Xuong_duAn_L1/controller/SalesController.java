package com.example.Xuong_duAn_L1.controller;


import com.example.Xuong_duAn_L1.entity.*;
import com.example.Xuong_duAn_L1.repository.BillDetailRepo;
import com.example.Xuong_duAn_L1.repository.BillRepo;
import com.example.Xuong_duAn_L1.repository.ProductDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    ProductDetailRepo productDetailRepo;


    @Autowired
    BillRepo billRepo;

    @Autowired
    BillDetailRepo billDetailRepo;
    List<Cart> carts = new ArrayList<>();

    @ModelAttribute("listProduct")
    public Page<ProductDetail> listProduct() {
        Sort sort = Sort.by(Sort.Direction.DESC, "idProductDetail");
        System.out.println("Truy Xuất Danh Sách Sản Phẩm Chi Tiết.");
        Pageable page = PageRequest.of(0, 20, sort);
        return productDetailRepo.findAll(page);
    }

    @ModelAttribute("carts")
    public List<Cart> cart() {
        return carts;
    }

    @ModelAttribute("totalPrice")
    public int getTotalPrice() {
        int totalPrice = 0;
        for (Cart c : carts) {
            totalPrice += c.getQuantity() * c.getProductDetail().getPrice();
        }
        return totalPrice;
    }

    @GetMapping("")
    public String viewSales(Model model) {
        Customer customer=new Customer();
        model.addAttribute("customer", customer);
        return "sales/banhang";
    }

    @GetMapping("/addtocart/{id}")
    public String addToCart(@ModelAttribute("id") int id) {
        System.out.println("Thêm SP Vào Cart.");
        if (carts.size() == 0) {//Thêm vào cart khi carts ko có sp nào
            carts.add(new Cart(carts.size(), 1, productDetailRepo.findById(id).get()));
        } else {
            for (int i = 0; i < carts.size(); i++) {//nếu có hơn 0 sp thì check
                Cart c = carts.get(i);
                if (c.getProductDetail().getIdProductDetail() == id) {//sp trùng thì thêm số lượng,
                    c.setQuantity(c.getQuantity() + 1);
                    return "redirect:/sales";
                } else {
                    if (i == carts.size() - 1) {//ko trùng thì thêm sp vào
                        carts.add(new Cart(carts.size(), 1, productDetailRepo.findById(id).get()));
                        return "redirect:/sales";
                    }
                }
            }
        }
        return "redirect:/sales";
    }

    @GetMapping("/removecart/{id}")
    public String removeToCart(@PathVariable("id") int id) {
        System.out.println("Xóa SP Khỏi Cart.");
        carts.removeIf(cart -> cart.getId() == id); // Remove cart by id
        return "redirect:/sales";
    }

    @PostMapping("/updatecart/{id}")
    public String updateCart(@PathVariable("id") int id, @ModelAttribute("c") Cart cart) {
        System.out.println("Cập Nhật Cart");
        for (Cart c : carts) {
            if (c.getId() == id) {
                c.setQuantity(cart.getQuantity());
                System.out.println("Cập Nhật số lượng của SP " + c.getId());
            }
        }
        return "redirect:/sales";
    }

    @GetMapping("/payNoAbout")
    public String payNoAbout() {
        System.out.println("Đang Thanh Toán Hóa Đơn Khách Lẻ");
        Bill bill = new Bill(0L,
                null,
                null,
                getTotalPrice(),
                null,
                "Tiền Mặt",
                null,
                null,
                "HaNoi",
                null,
                new Date(),
                null,
                null,
                null);
        bill.setId(billRepo.save(bill).getId());
        for (Cart c : carts) {
            BillDetail billDetail = new BillDetail(0, bill, c.getProductDetail(),c.getQuantity(), null, new Date(), null, null);
            billDetailRepo.save(billDetail);
        }
        carts.clear();
        return "redirect:/sales";
    }

    @PostMapping("/pay")
    public String pay(@ModelAttribute("customer") Customer customer) {
        System.out.println(customer);
        System.out.println("Đang Thanh Toán Hóa Đơn Khách Quen");
        Bill bill = new Bill(0L,
                null,
                customer,
                getTotalPrice(),
                null,
                "Tiền Mặt",
                null,
                null,
                "HaNoi",
                null,
                new Date(),
                null,
                null,
                null);
        bill.setId(billRepo.save(bill).getId());
        for (Cart c : carts) {
            BillDetail billDetail = new BillDetail(0, bill, c.getProductDetail(),c.getQuantity(), null, new Date(), null, null);
            billDetailRepo.save(billDetail);
        }
        carts.clear();
        return "redirect:/sales";
    }
}
