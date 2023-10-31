package com.ecom.ECommerce.repo;

import com.ecom.ECommerce.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository  extends JpaRepository<Account,Integer> { }
