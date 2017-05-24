package com.my.controller;

import com.my.domain.Account;
import com.my.domain.Goods;
import com.my.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ModelAttribute("module")
    public String module() {
        return "account";
    }

    @ModelAttribute("account")
    public Account accountModule() {
        return new Account();
    }

    @GetMapping("current")
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseBody
    public Account currentAccount(Principal principal) {
        Assert.notNull(principal);
        return accountService.findOneByUserName(principal.getName());
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    @ResponseBody
    public Account account(@PathVariable("id") Long id) {
        return accountService.findOne(id);
    }

    @GetMapping("edit/{id}")
    @Secured("ROLE_ADMIN")
    public String editAccount(@PathVariable("id") Long id,Model model) {
        model.addAttribute("account", accountService.findOne(id));
        return "account/account";
    }

    @GetMapping("")
    public String accounts(Model model){
        model.addAttribute("accounts",accountService.findAll());
        return "account/account-list";
    }

    @PostMapping("search")
    public String getAccountsByUserName(@ModelAttribute("account") Account account, Model model){
        model.addAttribute("accounts",accountService.findAllByName(account.getUserName()));
        return "account/account-list";
    }

    @GetMapping("add")
    @Secured("ROLE_ADMIN")
    public String add(@ModelAttribute("account") Account account) {
        return "account/account";
    }

    @PostMapping("")
    @Secured("ROLE_ADMIN")
    public String save(@Valid @ModelAttribute("account") Account account, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return "account/account";
        }
        accountService.save(account);
        return "redirect:/account";
    }

    @GetMapping("delete/{id}")
    @Secured("ROLE_ADMIN")
    public String delete(@PathVariable("id") Long id){
        accountService.delete(id);
        return "redirect:/account";
    }

}
