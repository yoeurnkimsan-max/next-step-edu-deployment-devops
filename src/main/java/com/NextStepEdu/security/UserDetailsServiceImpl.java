package com.NextStepEdu.security;

import com.NextStepEdu.models.UserModel;
import com.NextStepEdu.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByEmail(email)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User Email not found"));

        CustomUserDetail customUserDetail = new CustomUserDetail();
        customUserDetail.setUser(userModel);
        return customUserDetail;
    }
}
