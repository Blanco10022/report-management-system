package com.afriland.rapport.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

//import com.afriland.auth.model.ReportType;
import com.afriland.rapport.model.ReportType;
import com.afriland.rapport.model.ReportDts;
import com.afriland.rapport.model.ReportDocument ;
import com.afriland.rapport.repository.ReportDocumentRepository;
import com.afriland.rapport.repository.ReportRepository;
import com.afriland.rapport.service.ReportService;

import jakarta.persistence.criteria.Path;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/user")
public class ReportController {
	
	private final ReportService reportService;
	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private ReportDocumentRepository reportDocumentRepository;


    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

	
	@GetMapping("/")
	public String repport()
	{
		return "user/home";
	}
	
	
//	@GetMapping("/list")
//	  public String listofreport(Model m) 
//	  { 
//		  	
//			List<ReportDts> rep =reportService.getAllReport();
//			m.addAttribute("rep", rep);
//		  return "list"; 
//	  }
//	@GetMapping("/list")
//	public String listofreport(Model model, Principal principal) {
//	    // Retrieve the authenticated user's email
//	    String userEmail = principal.getName();
//
//	    // Retrieve the reports associated with the authenticated user's email
//	    List<ReportDts> userReports = reportService.getReportsByUserEmail(userEmail);
//
//	    model.addAttribute("rep", userReports);
//	    return "list";
//	}
//	
	
	/*
	 * @GetMapping("/list") public String listofreport(Model model, Principal
	 * principal) { // Retrieve the authenticated user's email String userEmail =
	 * principal.getName();
	 * 
	 * // Retrieve all reports associated with the authenticated user's email
	 * List<ReportDts> userReports = reportService.getReportsByUserEmail(userEmail);
	 * 
	 * List<ReportDts> weeklyReports = new ArrayList<>(); List<ReportDts>
	 * regularReports = new ArrayList<>();
	 * 
	 * for (ReportDts report : userReports) { if (report.isWeeklyReport()) {
	 * weeklyReports.add(report); } else { regularReports.add(report); } }
	 * 
	 * model.addAttribute("weeklyReports", weeklyReports); model.addAttribute("rep",
	 * regularReports);
	 * 
	 * if (weeklyReports.isEmpty()) { return ""; // Redirect to the
	 * "weeklylist.html" template if there are weekly reports } else { return
	 * "list"; // Redirect to the "list.html" template if there are no weekly
	 * reports } }
	 */

	/*
	 * @GetMapping("/weeklylist") public String listofweeklyreport(Model model,
	 * Principal principal) { // Retrieve the authenticated user's email String
	 * userEmail = principal.getName();
	 * 
	 * // Retrieve the reports associated with the authenticated user's email
	 * List<ReportDts> weeklyReports =
	 * reportService.getReportsByUserEmailandIsweekly(userEmail,true);
	 * 
	 * model.addAttribute("weeklylist", weeklyReports ); return "weeklylist"; }
	 */
	
	
	
	


	 
	  
		/*
		 * @GetMapping("/delete/{id}") public String deleteReport(@PathVariable int id,
		 * HttpSession session) { reportService.deleteReport(id);
		 * session.setAttribute("msg", "Report Data Deleted Successfully.");
		 * 
		 * return "redirect:user/list"; }
		 */
	  
		/*
		 * @GetMapping("/edit/{id}") public String editReport(@PathVariable int id,
		 * Model model) { ReportDts report = reportService.getReportById(id);
		 * model.addAttribute("report", report); return "user/editreport"; }
		 */
		/*
		 * @PostMapping("/update") public String updateReport(@ModelAttribute ReportDts
		 * report, Model model) { reportService.updateReport(report);
		 * model.addAttribute("msg", "Report updated successfully."); return
		 * "redirect:/user/list"; }
		 * 
		 */
	
	@GetMapping("/view/{id}")
	 public String viewReport(@PathVariable int id, Model model) {
	     ReportDts reportview = reportService.getReportById(id);
	     model.addAttribute("reportview", reportview);
	     return "viewrepport";
	 }
	
	
	
	
	@GetMapping("/delete/{id}")
	public String deleteReport(@PathVariable int id, HttpSession session, Principal principal) {
	    // Get the email of the currently logged-in user
	    String userEmail = principal.getName();

	    // Call the repository method to find the report by ID and user email
	    ReportDts report = reportRepository.findByIdAndCreatedByUserEmail(id, userEmail);

	    // Check if the report exists
	    if (report != null) {
	        // Delete the report using the service
	        reportService.deleteReport(id);
	        session.setAttribute("msg", "Report Data Deleted Successfully.");
	    } else {
	        session.setAttribute("msg", "Report Not Found.");
	    }

	    return "redirect:/list";
	   
	}
	

	 @PostMapping("/update")
	  public String updateReport(@ModelAttribute ReportDts rep, Model model, Principal principal) {
	      // Retrieve the authenticated user's email
	      String userEmail = principal.getName();
	      

	      // Set the report type to MONTHLY
	      rep.setReportType(ReportType.QUOTIDIEN);

	      // Update the report with the logged-in user's email
	      reportService.updateReport(rep, userEmail);

	   // Retrieve the updated list of reports for the authenticated user
		    List<ReportDts> userReports= reportService.getReportsByUserEmailAndReportType(userEmail, ReportType.QUOTIDIEN);

	      model.addAttribute("rep", userReports);
	      model.addAttribute("msg", "Report updated successfully.");

	      return "redirect:/list";
	  }
	 
	 

	
	@GetMapping("/deleteweek/{id}")
	public String deleteweekReport(@PathVariable int id, HttpSession session, Principal principal) {
	    // Get the email of the currently logged-in user
	    String userEmail = principal.getName();

	    // Call the repository method to find the report by ID and user email
	    ReportDts report = reportRepository.findByIdAndCreatedByUserEmail(id, userEmail);

	    // Check if the report exists
	    if (report != null) {
	        // Delete the report using the service
	        reportService.deleteReport(id);
	        session.setAttribute("msg", "Report Data Deleted Successfully.");
	    } else {
	        session.setAttribute("msg", "Report Not Found.");
	    }

	    return "redirect:/weeklylist";
	}
	
	@GetMapping("/deletemonth/{id}")
	public String deletemonthReport(@PathVariable int id, HttpSession session, Principal principal) {
	    // Get the email of the currently logged-in user
	    String userEmail = principal.getName();

	    // Call the repository method to find the report by ID and user email
	    ReportDts report = reportRepository.findByIdAndCreatedByUserEmail(id, userEmail);

	    // Check if the report exists
	    if (report != null) {
	        // Delete the report using the service
	        reportService.deleteReport(id);
	        session.setAttribute("msg", "Report Data Deleted Successfully.");
	    } else {
	        session.setAttribute("msg", "Report Not Found.");
	    }

	    return "redirect:/monthlylist";
	}



	
	 
//	 @PostMapping("/createrapport")
//	 public String createReport(@ModelAttribute ReportDts report, Model model) {
//	     report.setDate(LocalDate.now());
//	     reportService.addReport(report);
//	     model.addAttribute("msg", "Rapport cree avec Success");
//	     return "user/home";
//	 }

//	@PostMapping("/createrapport")
//	public String createReport(@ModelAttribute ReportDts report, Principal principal, Model model) {
//	    report.setDate(LocalDate.now());
//
//	    // Retrieve the authenticated user's email
//	    String userEmail = principal.getName();
//	    report.setCreatedByUserEmail(userEmail);
//
//	    reportService.addReport(report);
//	    model.addAttribute("msg", "Rapport cree avec Success");
//	    return "user/home";
//	}
	@PostMapping("/createrapport")
	public String createReport(@ModelAttribute ReportDts report, @RequestParam("submitType") String submitType, @RequestParam("file") MultipartFile file, Principal principal, Model model) {
	    report.setDate(LocalDate.now());
	    
	    // Retrieve the authenticated user's email
	    String userEmail = principal.getName();
	    report.setCreatedByUserEmail(userEmail);

	    if (submitType.equals("SoumetreRapportHebdo")) {
	        report.setWeeklyReport(true);
	        report.setReportType(ReportType.HEBDOMADAIRE);
	    } 
	    else if (submitType.equals("SoumetreRapportMensuel")) {
	        report.setReportType(ReportType.MENSUEL);
	    } else {
	        report.setReportType(ReportType.QUOTIDIEN);
	        report.setWeeklyReport(false);
	    }
	    
	    // Save the uploaded file if it exists
	    if (!file.isEmpty()) {
	        String documentPath = saveFile(file);
	        ReportDocument reportDocument = new ReportDocument();
	        reportService.addReport(report); // Save the report to the database
	        reportDocument.setReport(report);
	        reportDocument.setDocumentType(file.getContentType());
	        reportDocument.setDocumentPath(documentPath);
	        reportDocumentRepository.save(reportDocument);

	    }
	    
	    reportService.addReport(report); // Save the report to the database
	    
	    model.addAttribute("msg", "Rapport créé avec succès");
	    return "user/home";
	}
	
	
	
	private String saveFile(MultipartFile file) {
	    if (file.isEmpty()) {
	        // Handle the case when no file is uploaded
	        return null;
	    }

	    try {
	        // Define the directory path to save the file
	    	String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/upload/";
	        
	        // Generate a unique file name or use the original file name
	        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

	        // Save the file to the directory
	        java.nio.file.Path filePath = Paths.get(uploadDir, fileName);
	        Files.createDirectories(filePath.getParent());
	        file.transferTo(filePath);

	        // Return the file path or URL
	        return filePath.toString();
	    } catch (IOException e) {
	        e.printStackTrace();
	        // Handle the exception or return an appropriate value in case of an error
	        return null;
	    }
	}



}


