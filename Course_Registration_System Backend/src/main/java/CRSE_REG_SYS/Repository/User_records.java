package CRSE_REG_SYS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CRSE_REG_SYS.Model.User_Records;

@Repository
public interface User_records extends JpaRepository<User_Records,Long>{
	User_Records findByUsername(String username);
}

