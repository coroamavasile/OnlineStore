package com.ps.ElectronicsStore.service;

import com.ps.ElectronicsStore.model.Admin;
import org.springframework.stereotype.Component;

@Component
public interface AdminService {
    Admin updateAdmin(Admin dto);
    Admin findByUsername(String username);
    Admin save(Admin admin);
}
