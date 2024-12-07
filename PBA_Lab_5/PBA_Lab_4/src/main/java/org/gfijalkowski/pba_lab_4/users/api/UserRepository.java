package org.gfijalkowski.pba_lab_4.users.api;

import org.gfijalkowski.pba_lab_4.users.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}