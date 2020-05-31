package com.esme.meetme.domain;

import com.esme.meetme.infrastructure.UserDao;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers() {
        return this.userDao.findUsers();
    }

    @Cacheable("users")
    public User findUser(UUID id) throws NotFoundException {
        log.info(id.toString());
        return userDao.findUser(id);
    }

    public User addUser(User user) throws NotFoundException { return userDao.createUser(user); }

    public void deleteUser(UUID id) { userDao.deleteUser(id); };

    public void patchUser(User user) { userDao.replaceUser(user); }

    public User replaceUser(User user) { return userDao.replaceUser(user); }
}
