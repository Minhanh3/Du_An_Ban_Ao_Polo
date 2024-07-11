package com.example.Xuong_duAn_L1.controller;

import com.example.Xuong_duAn_L1.entity.Bill;
import com.example.Xuong_duAn_L1.entity.BillDetail;
import com.example.Xuong_duAn_L1.repository.BillDetailRepo;
import com.example.Xuong_duAn_L1.repository.BillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {


    Bill billShow= new Bill();

    public List<BillDetail> billdetailShow= new ArrayList<>();


        @Autowired
    BillRepo billRepo;

    @Autowired
    BillDetailRepo billDetailRepo;

    @ModelAttribute("bill")
    public Bill billShow(){
        return billShow;
    }

    @ModelAttribute("billdetails")
    public List<BillDetail> billdetailShow(){
        return billdetailShow;
    }

    @ModelAttribute("bill")
    public Page<Bill> listBill(@RequestParam(defaultValue = "0") int p,
                               @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                               @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                               @RequestParam(defaultValue = "") String keyWord
    ) {
        Sort sort = Sort.by(Sort.Direction.DESC, "ngaytao");
        Pageable page = PageRequest.of(p, 5, sort);
//        return billRepo.findByKeywordAndDateRange(keyWord,startDate,endDate,page);
        return  billRepo.findAll(page);
    }

    @GetMapping("")
    public String bill() {
        return "bill/hoadon";
    }

    @GetMapping("/billdetail/{id}")
    public String showBillDetail(@ModelAttribute("id")Long id,Model model){
        billShow=billRepo.findById(id.intValue()).get();
        billdetailShow=billDetailRepo.findByIDBill(id);
        return "redirect:/";
    }
}
