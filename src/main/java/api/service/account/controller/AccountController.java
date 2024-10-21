package api.service.account.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.service.account.entity.AccountHolder;
import api.service.account.entity.BankAccount;
import api.service.account.entity.Transaction;
import api.service.account.service.AccountHolderService;
import api.service.account.service.BankAccountService;
import api.service.account.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account Management API", description = "API for managing account holders, bank accounts, and transactions")
public class AccountController {

    @Autowired
    private AccountHolderService accountHolderService;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private TransactionService transactionService;

    

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
    return ResponseEntity.ok("Authentication API is up and running");
    }

    // Gestion des AccountHolders
    @Operation(summary = "Create a new account holder", description = "Creates a new account holder in the system")
    @PostMapping("/account-holders")
    public ResponseEntity<AccountHolder> createAccountHolder(@RequestBody AccountHolder accountHolder) {
        AccountHolder createdAccountHolder = accountHolderService.saveAccountHolder(accountHolder);
        return ResponseEntity.ok(createdAccountHolder);
    }

    @Operation(summary = "Get all account holders", description = "Retrieves a list of all account holders")
    @GetMapping("/account-holders")
    public ResponseEntity<List<AccountHolder>> getAllAccountHolders() {
        List<AccountHolder> accountHolders = accountHolderService.getAllAccountHolders();
        return ResponseEntity.ok(accountHolders);
    }

    @Operation(summary = "Update account holder", description = "Updates the details of an existing account holder")
    @PutMapping("/account-holders/{id}")
    public ResponseEntity<AccountHolder> updateAccountHolder(@PathVariable Long id, @RequestBody AccountHolder accountHolderDetails) {
        Optional<AccountHolder> accountHolderOptional = accountHolderService.getAccountHolderById(id);
        if (accountHolderOptional.isPresent()) {
            AccountHolder accountHolder = accountHolderOptional.get();
            accountHolder.setName(accountHolderDetails.getName());
            accountHolder.setEmail(accountHolderDetails.getEmail());
            AccountHolder updatedAccountHolder = accountHolderService.saveAccountHolder(accountHolder);
            return ResponseEntity.ok(updatedAccountHolder);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete account holder", description = "Deletes an account holder by their ID")
    @DeleteMapping("/account-holders/{id}")
    public ResponseEntity<Void> deleteAccountHolder(@PathVariable Long id) {
        accountHolderService.deleteAccountHolder(id);
        return ResponseEntity.noContent().build();
    }

    // Gestion des BankAccounts
    @Operation(summary = "Create a new bank account", description = "Creates a new bank account in the system")
    @PostMapping("/bank-accounts")
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
        BankAccount createdBankAccount = bankAccountService.saveBankAccount(bankAccount);
        return ResponseEntity.ok(createdBankAccount);
    }

    @Operation(summary = "Get all bank accounts", description = "Retrieves a list of all bank accounts")
    @GetMapping("/bank-accounts")
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountService.getAllBankAccounts();
        return ResponseEntity.ok(bankAccounts);
    }

    @Operation(summary = "Update bank account", description = "Updates the details of an existing bank account")
    @PutMapping("/bank-accounts/{id}")
    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccountDetails) {
        Optional<BankAccount> bankAccountOptional = bankAccountService.getBankAccountById(id);
        if (bankAccountOptional.isPresent()) {
            BankAccount bankAccount = bankAccountOptional.get();
            bankAccount.setAccountNumber(bankAccountDetails.getAccountNumber());
            bankAccount.setBalance(bankAccountDetails.getBalance());
            BankAccount updatedBankAccount = bankAccountService.saveBankAccount(bankAccount);
            return ResponseEntity.ok(updatedBankAccount);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete bank account", description = "Deletes a bank account by their ID")
    @DeleteMapping("/bank-accounts/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long id) {
        bankAccountService.deleteBankAccount(id);
        return ResponseEntity.noContent().build();
    }

    // Gestion des Transactions
    @Operation(summary = "Create a new transaction", description = "Creates a new transaction record in the system")
    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.saveTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }

    @Operation(summary = "Get all transactions", description = "Retrieves a list of all transactions")
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @Operation(summary = "Update transaction", description = "Updates the details of an existing transaction")
    @PutMapping("/transactions/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        Optional<Transaction> transactionOptional = transactionService.getTransactionById(id);
        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            transaction.setAmount(transactionDetails.getAmount());
            transaction.setTransactionType(transactionDetails.getTransactionType());
            Transaction updatedTransaction = transactionService.saveTransaction(transaction);
            return ResponseEntity.ok(updatedTransaction);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete transaction", description = "Deletes a transaction by their ID")
    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
