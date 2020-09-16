package storage.springboot.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import storage.springboot.demo.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>{
	

}
