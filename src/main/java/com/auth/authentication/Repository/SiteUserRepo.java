package com.auth.authentication.Repository;

import com.auth.authentication.Model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepo extends JpaRepository<SiteUser,Long> {

    SiteUser findByuserName(String username);
}
