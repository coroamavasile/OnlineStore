//package com.ps.ElectronicsStore.security;
//
////import com.ps.ElectronicsStore.model.User;
//
//import com.ps.ElectronicsStore.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Locale;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        final com.ps.ElectronicsStore.model.User userAuthFromDB = userRepository.findByUsername(username);
//
//        if (userAuthFromDB == null) {
//            throw new UsernameNotFoundException(username);
//        }
//
//        UserDetails userDetails =
//                User.withUsername(userAuthFromDB.getUsername()).password(userAuthFromDB.getPassword()).roles(userAuthFromDB.getClass().getSimpleName().toUpperCase(Locale.ROOT)).build();
//        userDetails.getAuthorities().forEach(auth -> {
//            System.out.println(userDetails.getPassword() + " " + auth.toString());
//        });
//        return userDetails;
//    }
//}
