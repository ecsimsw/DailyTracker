package com.ecsimsw.dailyTracker.Service;

import com.ecsimsw.dailyTracker.Domain.Exception.DuplicatedUserException;
import com.ecsimsw.dailyTracker.Domain.User;
import com.ecsimsw.dailyTracker.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void signUp(String name) {
        if (isExist(name)) {
            throw new DuplicatedUserException();
        }
        userRepository.save(new User(name));
    }

    public boolean isExist(String name) {
        return userRepository.findByName(name) != null;
    }
}
