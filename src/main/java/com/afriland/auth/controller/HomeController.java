package com.afriland.auth.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.afriland.rapport.model.ReportType;
import com.afriland.auth.model.UserDtls;
import com.afriland.auth.repository.UserRepository;
import com.afriland.auth.service.UserService;
import com.afriland.rapport.model.ReportDocument;
import com.afriland.rapport.model.ReportDts;
import com.afriland.rapport.repository.ReportDocumentRepository;
import com.afriland.rapport.repository.ReportRepository;
import com.afriland.rapport.service.ReportService;
import com.afriland.util.SecurityUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;


@Controller
public class HomeController {
	
	private  UserService userService ;
	private final ReportService reportService;
	
	@Autowired
	private final UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ReportDocumentRepository reportDocumentRepository;

	@Autowired
	private ReportRepository reportRepository; 


	public HomeController(UserService userService, ReportService reportService, UserRepository userRepository) {
        this.reportService = reportService;
		this.userService = userService;
	     this.userRepository = userRepository;
		
    }
	
	

	@GetMapping("/")
    public String index()
	{
		return "index";
	
	}
	
	

	@GetMapping("/dashboard")
	public String admindashboard(Model model) {
	    List<ReportDts> newlyCreatedReports = reportService.findNewlyCreatedReports();
	    model.addAttribute("reports", newlyCreatedReports);
	    
	    int reportCount = reportService.getTotalReportCount(); // Fetch the count from the database
	    model.addAttribute("reportCount", reportCount); // Pass the count to the template
	    
	    
	    int userCount = reportService.getTotalUserCount();
	    model.addAttribute("userCount", userCount);
	    
	    
	    return "dashboard";
	}
	
	@GetMapping("/admindash")
	public String admindash(Model model) {
	    List<ReportDts> newlyCreatedReports = reportService.findNewlyCreatedReports();
	    model.addAttribute("reports", newlyCreatedReports);
	    
	    int reportCount = reportService.getTotalReportCount(); // Fetch the count from the database
	    model.addAttribute("reportCount", reportCount); // Pass the count to the template
	    
	    
	    int userCount = reportService.getTotalUserCount();
	    model.addAttribute("userCount", userCount);
	    
	    
	    return "admindash";
	}
	
	@GetMapping("/employee")
    public String employeelist(Model model) {
        List<UserDtls> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "employee";
    }
	
	
	@GetMapping("/touslesrapport")
	  public String listofallreport(Model m) 
	  { 

		List<ReportDts> rep =reportService.getAllReport();
		m.addAttribute("rep", rep);
		  return "touslesrapport"; 
	  }
	
	
	
	
	
	@GetMapping("/login")    //it was "/signin" i changed to login
	public String login()
	{
		return "login";
	}
	
		
	@GetMapping("/register")
	public String register()
	{
		return "register";
	}
	
	
	
	/*
	 * //je descide de mettre la liste ici parcque le mapping du url dans le Report
	 * controllerou ca dois normallement etre ca derange du coups c'est une
	 * alternative qe j'ai fais
	 */

	
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
	 * if (weeklyReports.isEmpty()) { return ""; // Redirect to the "list.html"
	 * template if there are no weekly reports } else { return "list"; // Redirect
	 * to the "weeklylist.html" template if there are weekly reports } }
	 */

	@GetMapping("/view/{id}")
	public String viewReport(@PathVariable("id") int id, Model model, Principal principal) {
	    // Retrieve the authenticated user's email
	    String userEmail = principal.getName();

	    List<ReportDocument> reportDocuments = reportDocumentRepository.findByReportId(id);
	    model.addAttribute("reportDocuments", reportDocuments);

	    ReportDts reportview = reportService.getReportById(id);
	    model.addAttribute("reportview", reportview);

	    // Fetch the document content and pass it to the view template
	    if (!reportDocuments.isEmpty()) {
	        List<String> imageTypes = Arrays.asList("image/jpeg", "image/png");
	        ReportDocument document = reportDocuments.get(0); // Assuming you only display the first document
	        String documentPath = System.getProperty("user.dir") + "/src/main/resources/static/upload/";

	        try {
	            Path filePath = Paths.get(documentPath);

	            byte[] fileContent = Files.readAllBytes(filePath);
	            String contentType = Files.probeContentType(filePath);

	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.parseMediaType(contentType));
	            headers.setContentDisposition(ContentDisposition.builder("inline").filename(filePath.getFileName().toString()).build());

	            ResponseEntity<byte[]> documentContent = new ResponseEntity<>(fileContent, headers, HttpStatus.OK);

	            model.addAttribute("documentContent", documentContent);
	            model.addAttribute("documentType", document.getDocumentType());
	            model.addAttribute("isImage", imageTypes.contains(document.getDocumentType()));
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Handle the exception or return an appropriate response in case of an error
	        }
	    }

	    return "viewrepport";
	}






	
	
	
	
	
	
	@GetMapping("/adminview/{id}")
	 public String adminviewReport(@PathVariable int id, Model model ) {
	    
	     ReportDts reportview = reportService.getReportById(id);
	     model.addAttribute("reportview", reportview);
	     return "adminview";
	 }
	
	@GetMapping("/edit-report/{reportId}")
	public String showEditReportPage(@PathVariable("reportId") int id, Model model) {
	    ReportDts report = reportService.getReportById(id);
	    model.addAttribute("report", report);
	    return "admintemplate";
	}

	
	
	@GetMapping("/list")
	public String listofreport(Model model, Principal principal) {
	    // Retrieve the authenticated user's email
	    String userEmail = principal.getName();

	    // Retrieve the reports associated with the authenticated user's email and report type MONTHLY
	    List<ReportDts> regularReports = reportService.getReportsByUserEmailAndReportType(userEmail, ReportType.QUOTIDIEN);
	    model.addAttribute("rep", regularReports);
	    return "list";
	}

	
	@GetMapping("/weeklylist")
	public String listofweeklyreport(Model model, Principal principal) {
	    // Retrieve the authenticated user's email
	    String userEmail = principal.getName();

	    // Retrieve the reports associated with the authenticated user's email
	    List<ReportDts> weeklyReports = reportService.getReportsByUserEmailandIsweekly(userEmail,true);
	    
	    model.addAttribute("weeklylist", weeklyReports );
       return "weeklylist";
	}
	
	@GetMapping("/monthlylist")
	public String listofMonthlyreport(Model model, Principal principal) {
	    // Retrieve the authenticated user's email
	    String userEmail = principal.getName();

	    // Retrieve the reports associated with the authenticated user's email and report type MONTHLY
	    List<ReportDts> monthlyReports = reportService.getReportsByUserEmailAndReportType(userEmail, ReportType.MENSUEL);
	    model.addAttribute("monthlylist", monthlyReports);
	    return "monthlylist";
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


	
	//
	  
	  @GetMapping("/edit/{id}")
	  public String editReport(@PathVariable int id, Model model) {
	      ReportDts rep = reportService.getReportById(id); 
	      model.addAttribute("rep", rep); 
	      return "/editreport";
	  }
	  
	  
	  
	  @GetMapping("/edit-weekly/{id}")
	  public String editweeklyReport(@PathVariable int id, Model model) {
	      ReportDts weeklylist = reportService.getReportById(id); 

	      model.addAttribute("weeklylist", weeklylist); 
	      return "/editweeklyrepport";
	  }
	  
	  
	  @GetMapping("/edit-monthly/{id}")
	  public String editmonthReport(@PathVariable int id, Model model) {
	      ReportDts monthlylist = reportService.getReportById(id); 
	      model.addAttribute("monthlylist", monthlylist); 
	      return "/editmonthlyrepport";
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

	  
	  @PostMapping("/updateweek")
	  public String updateweekReport(@ModelAttribute ReportDts weeklist, Model model, Principal principal) {
	      // Retrieve the authenticated user's email
	      String userEmail = principal.getName();
	      
	      // Set the isWeeklyReport property to true
	      weeklist.setWeeklyReport(true);


	      // Update the report with the logged-in user's email
	      reportService.updateReport(weeklist, userEmail);

	      // Retrieve the updated list of reports for the authenticated user
		    List<ReportDts> weeklyReports = reportService.getReportsByUserEmailandIsweekly(userEmail,true);

	      model.addAttribute("weeklist", weeklyReports);
	      model.addAttribute("msg", "Report updated successfully.");
	      return "redirect:/weeklylist";
	  }
	  
	  @PostMapping("/updatemonth")
	  public String updatemonthlyReport(@ModelAttribute ReportDts monthlylist, Model model, Principal principal) {
	      // Retrieve the authenticated user's email
	      String userEmail = principal.getName();
	      
	      
	      // Set the report type to MONTHLY
	      monthlylist.setReportType(ReportType.MENSUEL);
	      

	      // Update the report with the logged-in user's email
	      reportService.updateReport(monthlylist, userEmail);

	      // Retrieve the updated list of reports for the authenticated user
		    List<ReportDts> weeklyReports = reportService.getReportsByUserEmailAndReportType(userEmail, ReportType.MENSUEL);

	      model.addAttribute("monthlylist", weeklyReports);
	      model.addAttribute("msg", "Report updated successfully.");
	      return "redirect:/monthlylist";
	  }
	  
	  

	@PostMapping("/createUser")
	public String createuser(@ModelAttribute UserDtls user, HttpSession session)
	{
		 
		//System.out.println(user);
		
		boolean f =userService.checkEmail(user.getEmail());
		if(f)
		{
			session.setAttribute("msg", "Email déja existant ");
		}
		else {
			
			UserDtls userDtls =userService.createUser(user);
			
			if(userDtls != null)
			{
				session.setAttribute("msg", "Inscription Réussi Allez a la page de connexion");
			}else {
				session.setAttribute("msg", "Inscription échouer ");

			}
		}
		
		return "redirect:/register";
	}
	
	
	
}
