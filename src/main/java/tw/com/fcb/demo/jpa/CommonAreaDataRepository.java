package tw.com.fcb.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonAreaDataRepository extends JpaRepository<CommonAreaData, Long>{

}
