package com.esme.meetme.application.graphql;

import com.esme.meetme.domain.User;
import com.esme.meetme.domain.UserService;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResolver {

    private UserService userService;

    public UserResolver(UserService userservice) {
        this.userService = userService;
    }

    @GraphQLQuery
    public List<User> getUsers(){
        return userService.getUsers();
    }
}
