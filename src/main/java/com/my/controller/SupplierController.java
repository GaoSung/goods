package com.my.controller;

import com.my.domain.Supplier;
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
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @ModelAttribute("module")
    public String module() {
        return "supplier";
    }

    @ModelAttribute("supplier")
    public Supplier supplierName(){
        return new Supplier();
    }

    @RequestMapping(value = "supplier", method = RequestMethod.GET)
    public String suppliers(Model model) {
        model.addAttribute("suppliers", supplierService.findAll());
        return "supplier/supplier-list";
    }

    @RequestMapping(value = "supplier/{id}", method = RequestMethod.GET)
    public String supplier(@PathVariable("id") Long id, Model model) {
        model.addAttribute("supplier", supplierService.findOne(id));
        return "supplier/supplier";
    }

    @PostMapping("supplier/search")
    public String findSuppliersByName(@ModelAttribute("supplierName") Supplier supplier, Model model){
        model.addAttribute("suppliers",supplierService.findAllByName(supplier.getName()));
        return "supplier/supplier-list";
    }

    @GetMapping("supplier/add")
    public String add(@ModelAttribute("supplier") Supplier supplier) {
        return "supplier/supplier";
    }

    @PostMapping("supplier")
    public String save(@Valid @ModelAttribute("supplier") Supplier supplier, RedirectAttributes ra) {
        supplierService.save(supplier);
        return "redirect:/supplier";
    }

    @GetMapping("supplier/delete/{id}")
    public String delete(@PathVariable("id") Long id,RedirectAttributes ra) {
        supplierService.delete(id);
        return "redirect:/supplier";
    }


}
