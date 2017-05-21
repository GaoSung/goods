package com.my.controller;

import com.my.domain.Goods;
import com.my.domain.Stock;
import com.my.domain.Supplier;
import com.my.service.StockService;
import com.my.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by gaoxiang on 2017/5/18.
 */
@Controller
@Secured("ROLE_USER")
public class StockController {

    @Autowired
    private StockService stockService;

    @ModelAttribute("module")
    public String module() {
        return "stock";
    }

    @ModelAttribute("goods")
    public Goods goods(){
        return new Goods();
    }

    @GetMapping("stock")
    public String stocks(Model model) {
        model.addAttribute("stocks", stockService.findAll());
        System.out.println(stockService.findAll());
        return "stock/stock-list";
    }

    @GetMapping("stock/{id}")
    public String stock(@PathVariable("id") Long id, Model model) {
        model.addAttribute("stock", stockService.findOne(id));
        return "stock/stock";
    }

    @PostMapping("stock/search")
    public String getStocksByGoodsName(@ModelAttribute("goods") Goods goods, Model model){
        model.addAttribute("stocks",stockService.findStocksByGoods(goods));
        return "stock/stock-list";
    }

    @GetMapping("stock/add")
    public String add(@ModelAttribute("stock") Stock stock) {
        return "stock/stock";
    }

    @PostMapping("stock")
    public String save(@Valid @ModelAttribute("stock") Stock stock, RedirectAttributes ra) {
        stockService.save(stock);
        return "redirect:/stock";
    }
}
