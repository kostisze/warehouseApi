package storage.springboot.demo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import storage.springboot.demo.entity.ReportInfo;

public interface ReportInfoRepository extends JpaRepository<ReportInfo, Integer>{
	
	@Query(value = "SELECT p FROM ReportInfo p "
			+ "WHERE p.date <= :date")
	public List<ReportInfo> calculateReport(@Param("date") String date);
	
	@Query(value = "SELECT DISTINCT p FROM ReportInfo p LEFT JOIN p.reports r "
			+ "WHERE p.date <= :date AND "
			+ "r.productReport.barcode = :barcode")
	public List<ReportInfo> calculateReport2(@Param("date") String date, 
			@Param("barcode") String barcode);
	
}
