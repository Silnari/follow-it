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
        return userRepository.findByLoginIgnoreCase(login);
    }

    public boolean isUserInDb(String login) {
        return userRepository.existsByLogin(login);
    }

    public List<User> getAll() {
        return ImmutableList.copyOf(userRepository.findAll());
    }

    public List<User> getMostFollowedWithoutUser(String login) {
        return userRepository.findMostFollowedWithoutGiven(login);
    }

    public int getFollowingNumber(String userLogin) {
        return userRepository.findByFollowingLogin(userLogin).size();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> getRecommendedToFollow(User user) {
        List<User> recommendedList = userRepository.findRecommendedToFollow(user.getLogin());
        int recommendedListSize = recommendedList.size();
        if (recommendedListSize >= 5) return recommendedList.stream().limit(5).collect(Collectors.toList());
        recommendedList.addAll(getMostFollowedWithoutUser(user.getLogin()).stream()
                .filter(u -> !recommendedList.contains(u)).limit(5 - recommendedListSize).collect(Collectors.toList()));
        return recommendedList;
    }

    public void follow(User user, User toFollow) {
        user = userRepository.findByLoginIgnoreCase(user.getLogin());
        user.follows(userRepository.findByLoginIgnoreCase(toFollow.getLogin()));
        userRepository.save(user);
    }

    @Transactional
    public void unfollow(User user, User toUnfollow) {
        user = userRepository.findByLoginIgnoreCase(user.getLogin());
        user.unfollow(userRepository.findByLoginIgnoreCase(toUnfollow.getLogin()));
        userRepository.save(user);
    }
}
