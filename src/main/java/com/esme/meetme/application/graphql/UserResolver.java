package com.esme.meetme.application.graphql;

import com.esme.meetme.domain.User;
import com.esme.meetme.domain.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserResolver {

    private UserService userService;

    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    @GraphQLQuery
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GraphQLQuery
    @JsonProperty("userID")
    public User findUser(UUID id){
        try {
            return userService.findUser(id);
        }catch (ChangeSetPersister.NotFoundException e){
            return null;
        }
    }

}
