package com.ps.ElectronicsStore.service.impl;

import com.ps.ElectronicsStore.model.Admin;
import com.ps.ElectronicsStore.repository.AdminRepository;
import com.ps.ElectronicsStore.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService
{
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin updateAdmin(Admin dto) {
        return null;
    }

    @Override
    public Admin findByUsername(String username) {
        return null;
    }

    @Override
    public Admin save(Admin admin) {
        return null;
    }
}
