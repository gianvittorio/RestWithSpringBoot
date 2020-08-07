package br.com.gianvittorio.service;

import br.com.gianvittorio.converter.DozerConverter;
import br.com.gianvittorio.converter.custom.PersonConverter;
import br.com.gianvittorio.data.model.Person;
import br.com.gianvittorio.data.model.User;
import br.com.gianvittorio.data.model.repository.PersonRepository;
import br.com.gianvittorio.data.model.repository.UserRepository;
import br.com.gianvittorio.data.vo.v1.PersonVO;
import br.com.gianvittorio.data.vo.v2.PersonVOV2;
import br.com.gianvittorio.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = repository.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + userName + " not found");
        }

        return user;
    }
}
