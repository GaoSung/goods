package com.my.controller;

import com.my.domain.Goods;
import com.my.service.GoodsService;
import com.my.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

/**
 * Created by gaoxiang on 2017/5/18.
 */
@Controller
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SupplierService supplierService;

    @ModelAttribute("module")
    public String module() {
        return "goods";
    }

    @ModelAttribute("goodsParam")
    public Goods goodsName(){
        return new Goods();
    }

    @RequestMapping(value = "goods", method = RequestMethod.GET)
    public String goodsLsit(Model model) {
        model.addAttribute("goodsList", goodsService.findAll());
        return "goods/goods-list";
    }

    @RequestMapping(value = "goods/{id}", method = RequestMethod.GET)
    public String goods(@PathVariable("id") Long id, Model model) {
        model.addAttribute("goods", goodsService.findOne(id));
        model.addAttribute("suppliers",supplierService.findAll());
        return "goods/goods";
    }

    @GetMapping("goods/add")
    public String add(@ModelAttribute("goods") Goods goods,Model model) {
        model.addAttribute("suppliers",supplierService.findAll());
        return "goods/goods";
    }

    @PostMapping("goods")
    public String save(@Valid @ModelAttribute("goods") Goods goods,RedirectAttributes ra) {
        goodsService.save(goods);
        return "redirect:/goods";
    }


    @PostMapping("goods/search")
    public String getStocksByGoodsName(@ModelAttribute("goodsParam") Goods goods, Model model){
        model.addAttribute("goodsList",goodsService.findAllByName(goods.getName()));
        return "goods/goods-list";
    }
}
