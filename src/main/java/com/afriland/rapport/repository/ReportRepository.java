package com.afriland.rapport.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import com.afriland.auth.model.ReportType;
import com.afriland.rapport.model.ReportType;
import com.afriland.rapport.model.ReportDts;

public interface ReportRepository extends JpaRepository<ReportDts, Integer> {
	
    List<ReportDts> findByCreatedByUserEmail(String userEmail);

	ReportDts findByIdAndCreatedByUserEmail(int id, String userEmail);

	List<ReportDts> findByCreatedByUserEmailAndIsWeeklyReport(String userEmail, boolean b);

	List<ReportDts> findAllByOrderByCreationDateDesc();

	@Query("SELECT COUNT(r) FROM ReportDts r")
	int getTotalReportCount();

	@Query("SELECT COUNT(r) FROM UserDtls r")
	int getTotalUserCount();

}
