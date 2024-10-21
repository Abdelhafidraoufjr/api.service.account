package api.service.account.service;

import api.service.account.entity.AccountHolder;
import api.service.account.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountHolderService {

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    public List<AccountHolder> getAllAccountHolders() {
        return accountHolderRepository.findAll();
    }

    public Optional<AccountHolder> getAccountHolderById(Long id) {
        return accountHolderRepository.findById(id);
    }

    public AccountHolder saveAccountHolder(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    public void deleteAccountHolder(Long id) {
        accountHolderRepository.deleteById(id);
    }
}
