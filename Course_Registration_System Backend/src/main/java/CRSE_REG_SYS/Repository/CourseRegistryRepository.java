package CRSE_REG_SYS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CRSE_REG_SYS.Model.CourseRegistry;

@Repository
public interface CourseRegistryRepository extends JpaRepository<CourseRegistry,Integer>{

}
