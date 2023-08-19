package poly.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.store.entity.Account;
import poly.store.service.AccountService;

@Controller
public class SignUpController {
	 @Autowired
	    private AccountService accountService;

	    @GetMapping("/SignUp/form")
	    public String signUpForm(Model model) {
	        model.addAttribute("account", new Account());
	        return "layout/sign-up";
	    }

	    @PostMapping("/SignUp/submit")
	    public String signUpSubmit(@ModelAttribute Account account, Model model) {
	        if (accountService.isUsernameExists(account.getUsername())) {
	            model.addAttribute("errorMessage", "Username already exists.");
	            return "layout/sign-up";
	        }
	        accountService.registerAccount(account);
	        return "redirect:/security/login/form"; 
	    }
}
