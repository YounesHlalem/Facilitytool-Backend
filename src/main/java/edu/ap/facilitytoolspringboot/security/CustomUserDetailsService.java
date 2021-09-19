package edu.ap.facilitytoolspringboot.security;


import edu.ap.facilitytoolspringboot.exception.ResourceNotFoundException;
import edu.ap.facilitytoolspringboot.models.ExternalFirm;
import edu.ap.facilitytoolspringboot.models.ExternalFirmPrincipal;
import edu.ap.facilitytoolspringboot.models.User;
import edu.ap.facilitytoolspringboot.models.UserPrincipal;
import edu.ap.facilitytoolspringboot.repositories.ExternalFirmRepository;
import edu.ap.facilitytoolspringboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExternalFirmRepository externalFirmsRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadExternalFirmById(String id) {
        ExternalFirm externalFirms = externalFirmsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return ExternalFirmPrincipal.create(externalFirms);
    }
}

