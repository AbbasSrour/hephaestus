package com.abbassrour.backend.user.repository;

import com.abbassrour.backend.user.model.User;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface IUserRepo extends PagingAndSortingRepository<User, UUID> {
    User findUserById(UUID id);
}
