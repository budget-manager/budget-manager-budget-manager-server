package edu.cnm.deepdive.budgetmanagerservice.controller;

import edu.cnm.deepdive.budgetmanagerservice.service.BudgetRepository;
import edu.cnm.deepdive.budgetmanagerservice.service.TransactionRepository;
import edu.cnm.deepdive.budgetmanagerservice.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quotes")
public class BudgetPageController {

  private final BudgetRepository budgetRepository;
  private final TransactionRepository transactionRepository;
  private final UserRepository userRepository;

  @Autowired
  public BudgetPageController(BudgetRepository budgetRepository,
      TransactionRepository transactionRepository,
      UserRepository userRepository) {
    this.budgetRepository = budgetRepository;
    this.transactionRepository = transactionRepository;
    this.userRepository = userRepository;
  }

  @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
  public String get(Model model) {
    model.addAttribute("budgets", budgetRepository.getAllByOrderByNameAsc());
    return "list";
  }


//  @GetMapping(value = "/{id:\\d+}", produces = MediaType.TEXT_HTML_VALUE)
//  public String get(@PathVariable long id, Model model) {
//    return quoteRepository.findById(id).orElseThrow(NoSuchElementException::new);
//  }

}
