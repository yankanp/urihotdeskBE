package com.uri.hotdesk.repositories;

import com.uri.hotdesk.entities.HotDeskUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<HotDeskUser, String> {

	HotDeskUser findBymobileNumber(String mobileNumber);
}
