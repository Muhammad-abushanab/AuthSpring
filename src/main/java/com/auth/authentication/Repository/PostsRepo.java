package com.auth.authentication.Repository;

import com.auth.authentication.Model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepo extends JpaRepository<Posts,Long> {
}
