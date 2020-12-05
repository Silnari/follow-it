package followit.service;

import com.google.common.collect.ImmutableList;
import followit.dao.UserRepository;
import followit.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUser(String login) {
        return userRepository.findByLogin(login);
    }

    public boolean isUserInDb(String login) {
        return userRepository.existsByLogin(login);
    }

    public List<User> getAll() {
        return ImmutableList.copyOf(userRepository.findAll());
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void follow(User user, User toFollow) {
        System.out.println("to follow login:" + toFollow.getLogin());

        user = userRepository.findByLogin(user.getLogin());
        toFollow = userRepository.findByLogin(toFollow.getLogin());
        System.out.println("user login:" + user.getLogin());
        System.out.println("to follow login:" + toFollow.getLogin());
        user.follows(toFollow);
        userRepository.save(user);
    }
}
