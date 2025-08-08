package config;

import entities.Admin;
import entities.MemberStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import repositories.AdminRepository;
import repositories.MemberStructureRepository;

import java.util.Collections;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MemberStructureRepository memberStructureRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email).orElse(null);
        if (admin != null) {
            return new User(
                    admin.getEmail(),
                    admin.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        }

        MemberStructure member = memberStructureRepository.findByEmail(email).orElse(null);
        if (member != null) {
            return new User(
                    member.getEmail(),
                    member.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_MEMBRE_STRUCTURE"))
            );
        }

        throw new UsernameNotFoundException("Utilisateur introuvable avec l’email : " + email);
    }
}
