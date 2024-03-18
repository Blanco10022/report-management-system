package com.afriland.rapport.service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afriland.rapport.model.ReportDts;
import com.afriland.rapport.model.ReportType;
import com.afriland.rapport.repository.ReportRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.Attribute;

import com.afriland.auth.model.UserDtls;


@Service
public class ReportService {

	
	@Autowired
	private ReportRepository repo;
	
	public void addReport(ReportDts e)
	{
		repo.save(e);
	}
	
	public List<ReportDts > getAllReport()
	{
		return repo.findAll();
	}
	
	/*
	 * public ReportDts getReportById(int id) { Optional<ReportDts>
	 * e=repo.findById(id); if(e.isPresent()) { return e.get(); } return null; }
	 */
	public ReportDts getReportById(int id) {
	    Optional<ReportDts> optionalReport = repo.findById(id);
	    if (optionalReport.isPresent()) {
	        return optionalReport.get();
	    }
	    throw new ReportNotFoundException("Report not found with ID: " + id);
	}

	
	public void deleteReport(int id)
	{
		repo.deleteById(id);
	}
	
	public void updateReport(ReportDts report, String userEmail) {
	    // Set the updatedByUserEmail field of the report to the logged-in user's email
	    report.setCreatedByUserEmail(userEmail);

	    // Save the updated report
	    repo.save(report);
	}
	public List<ReportDts> getReportsByUserEmail(String userEmail) {
	    return repo.findByCreatedByUserEmail(userEmail);
	}
	
	
	
	public List<ReportDts> getReportsByUserEmailandIsweekly(String userEmail, boolean b) {
	    return repo.findByCreatedByUserEmailAndIsWeeklyReport(userEmail,true);
	}



	public List<ReportDts> getReportsByUserEmailAndCreationDateBetween(String userEmail, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ReportDts> getReportsByUserEmailAndReportType(String userEmail, ReportType reportType) {
	    List<ReportDts> reports = new ArrayList<>();

	    // Logic to retrieve reports based on user's email and report type
	    // Replace this with your actual implementation
	   
	    
	    // Assuming you have a list of all reports in your service or repository
	    List<ReportDts> allReports = getAllReport(); // Replace "reportRepository" with your actual repository name
	    
	    // Filter reports based on user's email and report type
	    for (ReportDts report : allReports) {
	        if (report.getCreatedByUserEmail().equals(userEmail) && report.getReportType() == reportType) {
	            reports.add(report);
	        }
	    }
	    
	    return reports;
	}
	
	
	public List<ReportDts> findNewlyCreatedReports() {
	    // Retrieve the last 7 reports from the repository based on creationDate
	    return repo.findAllByOrderByCreationDateDesc().stream()
	            .limit(7)
	            .collect(Collectors.toList());
	}

	public int getTotalReportCount() {
		// TODO Auto-generated method stub
		 return repo.getTotalReportCount();
	}

	public int getTotalUserCount() {
		// TODO Auto-generated method stub
		 return repo.getTotalUserCount();
	}

	
	
	
	
}
