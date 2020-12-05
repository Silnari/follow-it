package followit.service;

import com.google.common.collect.ImmutableList;
import followit.dao.UserRepository;
import followit.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    public Set<User> getRecommendedToFollow(User user) {
        List<String> followingLogin = user.getFollowing().stream().map(User::getLogin).collect(Collectors.toList());
        followingLogin.add(user.getLogin());
        List<String> recommendedList = user.getFollowing().stream()
                .map(u -> userRepository.findByLogin(u.getLogin()))
                .map(User::getFollowing).flatMap(Collection::stream)
                .filter(u -> !followingLogin.contains(u.getLogin()))
                .map(User::getLogin)
                .collect(Collectors.toList());
        recommendedList.sort(Comparator.comparing(u -> Collections.frequency(recommendedList, u)).reversed());
        return new LinkedHashSet<>(recommendedList).stream().map(userRepository::findByLogin).limit(5).collect(Collectors.toSet());
    }

    public void follow(User user, User toFollow) {
        user = userRepository.findByLogin(user.getLogin());
        user.follows(userRepository.findByLogin(toFollow.getLogin()));
        userRepository.save(user);
    }

    @Transactional
    public void unfollow(User user, User toUnfollow) {
        user = userRepository.findByLogin(user.getLogin());
        user.unfollow(userRepository.findByLogin(toUnfollow.getLogin()));
        userRepository.save(user);
    }
}
