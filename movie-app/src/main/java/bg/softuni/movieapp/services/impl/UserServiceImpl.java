package bg.softuni.movieapp.services.impl;

import bg.softuni.movieapp.model.binding.UserRegisterBindingModel;
import bg.softuni.movieapp.model.entity.User;
import bg.softuni.movieapp.repository.UserRepository;
import bg.softuni.movieapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        if (userRegisterBindingModel == null) {
            return false;
        }

        String username = userRegisterBindingModel.getUsername();
        if (this.userRepository.findByUsername(username) != null) {
            return false;
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getRepeatPassword())) {
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        user.setEmail(userRegisterBindingModel.getEmail());

        this.userRepository.save(user);

        return true;
    }
}
