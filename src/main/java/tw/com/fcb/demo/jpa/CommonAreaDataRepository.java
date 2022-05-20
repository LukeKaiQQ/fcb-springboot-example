package tw.com.fcb.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonAreaDataRepository extends JpaRepository<CommonAreaData, Long>{
	CommonAreaData findByName(String name);
	
	@Query(value = "SELECT t.custId, COUNT(t.custId) FROM CommonAreaData AS t GROUP BY t.custId")
	List<String> countByCustId();
}
