package pl.edu.oswiecim.pwsz.inf.hrs.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Role;
import pl.edu.oswiecim.pwsz.inf.hrs.model.User;
import pl.edu.oswiecim.pwsz.inf.hrs.repository.RoleRepo;
import pl.edu.oswiecim.pwsz.inf.hrs.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepo roleRepo;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByUsername(s);
        User userByEmail = userService.findByEmail(s);
        if (user != null) {
            LOGGER.debug(" user from username " + user.toString());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
        } else if (userByEmail != null) {
            LOGGER.debug(" user from email " + userByEmail.toString());
            return new org.springframework.security.core.userdetails.User(userByEmail.getUsername(), userByEmail.getPassword(), getAuthorities(userByEmail));
        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    public List<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Integer roles = user.getRole();
        Iterable<Role> stringRoles = roleRepo.findAll();
        while(roles > 0) {
            for(Role role : stringRoles){
                if(role.getBit() == Integer.highestOneBit(roles)){
                    authorities.add(new SimpleGrantedAuthority(role.getRole()));
                    roles -= Integer.highestOneBit(roles);
                }
            }
        }
        LOGGER.debug("user authorities are " + authorities.toString());
        return authorities;
    }

}
