package com.afriland.rapport.model;

import java.time.LocalDate;
import java.util.Date;

import com.afriland.rapport.model.ReportType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "Rapport_tbl")
public class ReportDts {
	

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	

	
	
	@Column(name = "id")
    private Integer id;

    @Column(name = "month", columnDefinition = "VARCHAR(255)")
    private String month;

    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "unite", columnDefinition = "TEXT")
    private String unite;
    
    
    @Column(name = "creation_date")
    private Date creationDate;
    
    @Column(name = "is_weekly_report")
    private Boolean isWeeklyReport;

    
    private ReportType reportType;

    
    
	
	// Contrôle section fields

    @Column(name = "controle_date_butoir")
    private LocalDate controleDateButoir;

    @Column(name = "controle_nombre_total")
    private Integer controleNombreTotal;

    @Column(name = "controle_observation", columnDefinition = "TEXT")
    private String controleObservation;

    @Column(name = "controle_planifie", columnDefinition = "TEXT")
    private String controlePlanifie;

    @Column(name = "controle_statut_activites", columnDefinition = "TEXT")
    private String controleStatutActivites;

    @Column(name = "controle_statut_delai", columnDefinition = "TEXT")
    private String controleStatutDelai;

    @Column(name = "controle_statut_taches", columnDefinition = "TEXT")
    private String controleStatutTaches;

    @Column(name = "controle_taches", columnDefinition = "TEXT")
    private String controleTaches;
    
    // Maintenance section fields
    @Column(name = "maintenance_date_butoir")
    private LocalDate maintenanceDateButoir;

    @Column(name = "maintenance_nombre_total")
    private Integer maintenanceNombreTotal;

    @Column(name = "maintenance_observation", columnDefinition = "TEXT")
    private String maintenanceObservation;

    @Column(name = "maintenance_planifie", columnDefinition = "TEXT")
    private String maintenancePlanifie;

    @Column(name = "maintenance_statut_activites", columnDefinition = "TEXT")
    private String maintenanceStatutActivites;

    @Column(name = "maintenance_statut_delai", columnDefinition = "TEXT")
    private String maintenanceStatutDelai;

    @Column(name = "maintenance_statut_taches", columnDefinition = "TEXT")
    private String maintenanceStatutTaches;

    @Column(name = "maintenance_taches", columnDefinition = "TEXT")
    private String maintenanceTaches;


    
    // Contribution dans les Projets section fields
    @Column(name = "contribution_projets_date_butoir")
    private LocalDate contributionProjetsDateButoir;

    @Column(name = "contribution_projets_nombre_total")
    private Integer contributionProjetsNombreTotal;

    @Column(name = "contribution_projets_observation", columnDefinition = "TEXT")
    private String contributionProjetsObservation;

    @Column(name = "contribution_projets_planifie", columnDefinition = "TEXT")
    private String contributionProjetsPlanifie;

    @Column(name = "contribution_projets_statut_activites", columnDefinition = "TEXT")
    private String contributionProjetsStatutActivites;

    @Column(name = "contribution_projets_statut_delai", columnDefinition = "TEXT")
    private String contributionProjetsStatutDelai;

    @Column(name = "contribution_projets_statut_taches", columnDefinition = "TEXT")
    private String contributionProjetsStatutTaches;

    @Column(name = "contribution_projets_taches", columnDefinition = "TEXT")
    private String contributionProjetsTaches;


    // Support Utilisateurs section fields
    @Column(name = "support_utilisateurs_date_butoir")
    private LocalDate supportUtilisateursDateButoir;

    @Column(name = "support_utilisateurs_nombre_total")
    private Integer supportUtilisateursNombreTotal;

    @Column(name = "support_utilisateurs_observation", columnDefinition = "TEXT")
    private String supportUtilisateursObservation;

    @Column(name = "support_utilisateurs_planifie", columnDefinition = "TEXT")
    private String supportUtilisateursPlanifie;

    @Column(name = "support_utilisateurs_statut_activites", columnDefinition = "TEXT")
    private String supportUtilisateursStatutActivites;

    @Column(name = "support_utilisateurs_statut_delai", columnDefinition = "TEXT")
    private String supportUtilisateursStatutDelai;

    @Column(name = "support_utilisateurs_statut_taches", columnDefinition = "TEXT")
    private String supportUtilisateursStatutTaches;

    @Column(name = "support_utilisateurs_taches", columnDefinition = "TEXT")
    private String supportUtilisateursTaches;

    

    // Support Partenaires section fields
    @Column(name = "support_partenaires_date_butoir")
    private LocalDate supportPartenairesDateButoir;

    @Column(name = "support_partenaires_nombre_total")
    private Integer supportPartenairesNombreTotal;

    @Column(name = "support_partenaires_observation", columnDefinition = "TEXT")
    private String supportPartenairesObservation;

    @Column(name = "support_partenaires_planifie", columnDefinition = "TEXT")
    private String supportPartenairesPlanifie;

    @Column(name = "support_partenaires_statut_activites", columnDefinition = "TEXT")
    private String supportPartenairesStatutActivites;

    @Column(name = "support_partenaires_statut_delai", columnDefinition = "TEXT")
    private String supportPartenairesStatutDelai;

    @Column(name = "support_partenaires_statut_taches", columnDefinition = "TEXT")
    private String supportPartenairesStatutTaches;

    @Column(name = "support_partenaires_taches", columnDefinition = "TEXT")
    private String supportPartenairesTaches;


    // Reporting section fields
    @Column(name = "reporting_date_butoir")
    private LocalDate reportingDateButoir;

    @Column(name = "reporting_nombre_total")
    private Integer reportingNombreTotal;

    @Column(name = "reporting_observation", columnDefinition = "TEXT")
    private String reportingObservation;

    @Column(name = "reporting_planifie", columnDefinition = "TEXT")
    private String reportingPlanifie;

    @Column(name = "reporting_statut_activites", columnDefinition = "TEXT")
    private String reportingStatutActivites;

    @Column(name = "reporting_statut_delai", columnDefinition = "TEXT")
    private String reportingStatutDelai;

    @Column(name = "reporting_statut_taches", columnDefinition = "TEXT")
    private String reportingStatutTaches;

    @Column(name = "reporting_taches", columnDefinition = "TEXT")
    private String reportingTaches;

    

    
    // AUTRES section fields
    @Column(name = "autres_date_butoir")
    private LocalDate autresDateButoir;

    @Column(name = "autres_nombre_total")
    private Integer autresNombreTotal;

    @Column(name = "autres_observation", columnDefinition = "TEXT")
    private String autresObservation;

    @Column(name = "autres_planifie", columnDefinition = "TEXT")
    private String autresPlanifie;

    @Column(name = "autres_statut_activites", columnDefinition = "TEXT")
    private String autresStatutActivites;

    @Column(name = "autres_statut_delai", columnDefinition = "TEXT")
    private String autresStatutDelai;

    @Column(name = "autres_statut_taches", columnDefinition = "TEXT")
    private String autresStatutTaches;

    @Column(name = "autres_taches", columnDefinition = "TEXT")
    private String autresTaches;

    
    @Column(name = "created_By_UserEmail", columnDefinition = "TEXT")
    private String createdByUserEmail;
    
    //Getter and Setter 

	public String getCreatedByUserEmail() {
		return createdByUserEmail;
	}

	public void setCreatedByUserEmail(String createdByUserEmail) {
		this.createdByUserEmail = createdByUserEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getControlePlanifie() {
		return controlePlanifie;
	}

	public void setControlePlanifie(String controlePlanifie) {
		this.controlePlanifie = controlePlanifie;
	}

	public String getControleStatutActivites() {
		return controleStatutActivites;
	}

	public void setControleStatutActivites(String controleStatutActivites) {
		this.controleStatutActivites = controleStatutActivites;
	}

	public String getControleTaches() {
		return controleTaches;
	}

	public void setControleTaches(String controleTaches) {
		this.controleTaches = controleTaches;
	}

	public Integer getControleNombreTotal() {
		return controleNombreTotal;
	}

	public void setControleNombreTotal(Integer controleNombreTotal) {
		this.controleNombreTotal = controleNombreTotal;
	}

	public String getControleStatutTaches() {
		return controleStatutTaches;
	}

	public void setControleStatutTaches(String controleStatutTaches) {
		this.controleStatutTaches = controleStatutTaches;
	}

	public LocalDate getControleDateButoir() {
		return controleDateButoir;
	}

	public void setControleDateButoir(LocalDate controleDateButoir) {
		this.controleDateButoir = controleDateButoir;
	}

	public String getControleStatutDelai() {
		return controleStatutDelai;
	}

	public void setControleStatutDelai(String controleStatutDelai) {
		this.controleStatutDelai = controleStatutDelai;
	}

	public String getControleObservation() {
		return controleObservation;
	}

	public void setControleObservation(String controleObservation) {
		this.controleObservation = controleObservation;
	}

	public String getMaintenancePlanifie() {
		return maintenancePlanifie;
	}

	public void setMaintenancePlanifie(String maintenancePlanifie) {
		this.maintenancePlanifie = maintenancePlanifie;
	}

	public String getMaintenanceStatutActivites() {
		return maintenanceStatutActivites;
	}

	public void setMaintenanceStatutActivites(String maintenanceStatutActivites) {
		this.maintenanceStatutActivites = maintenanceStatutActivites;
	}

	public String getMaintenanceTaches() {
		return maintenanceTaches;
	}

	public void setMaintenanceTaches(String maintenanceTaches) {
		this.maintenanceTaches = maintenanceTaches;
	}

	public Integer getMaintenanceNombreTotal() {
		return maintenanceNombreTotal;
	}

	public void setMaintenanceNombreTotal(Integer maintenanceNombreTotal) {
		this.maintenanceNombreTotal = maintenanceNombreTotal;
	}

	public String getMaintenanceStatutTaches() {
		return maintenanceStatutTaches;
	}

	public void setMaintenanceStatutTaches(String maintenanceStatutTaches) {
		this.maintenanceStatutTaches = maintenanceStatutTaches;
	}

	public LocalDate getMaintenanceDateButoir() {
		return maintenanceDateButoir;
	}

	public void setMaintenanceDateButoir(LocalDate maintenanceDateButoir) {
		this.maintenanceDateButoir = maintenanceDateButoir;
	}

	public String getMaintenanceStatutDelai() {
		return maintenanceStatutDelai;
	}

	public void setMaintenanceStatutDelai(String maintenanceStatutDelai) {
		this.maintenanceStatutDelai = maintenanceStatutDelai;
	}

	public String getMaintenanceObservation() {
		return maintenanceObservation;
	}

	public void setMaintenanceObservation(String maintenanceObservation) {
		this.maintenanceObservation = maintenanceObservation;
	}

	public String getContributionProjetsPlanifie() {
		return contributionProjetsPlanifie;
	}

	public void setContributionProjetsPlanifie(String contributionProjetsPlanifie) {
		this.contributionProjetsPlanifie = contributionProjetsPlanifie;
	}

	public String getContributionProjetsStatutActivites() {
		return contributionProjetsStatutActivites;
	}

	public void setContributionProjetsStatutActivites(String contributionProjetsStatutActivites) {
		this.contributionProjetsStatutActivites = contributionProjetsStatutActivites;
	}

	public String getContributionProjetsTaches() {
		return contributionProjetsTaches;
	}

	public void setContributionProjetsTaches(String contributionProjetsTaches) {
		this.contributionProjetsTaches = contributionProjetsTaches;
	}

	public Integer getContributionProjetsNombreTotal() {
		return contributionProjetsNombreTotal;
	}

	public void setContributionProjetsNombreTotal(Integer contributionProjetsNombreTotal) {
		this.contributionProjetsNombreTotal = contributionProjetsNombreTotal;
	}

	public String getContributionProjetsStatutTaches() {
		return contributionProjetsStatutTaches;
	}

	public void setContributionProjetsStatutTaches(String contributionProjetsStatutTaches) {
		this.contributionProjetsStatutTaches = contributionProjetsStatutTaches;
	}

	public LocalDate getContributionProjetsDateButoir() {
		return contributionProjetsDateButoir;
	}

	public void setContributionProjetsDateButoir(LocalDate contributionProjetsDateButoir) {
		this.contributionProjetsDateButoir = contributionProjetsDateButoir;
	}

	public String getContributionProjetsStatutDelai() {
		return contributionProjetsStatutDelai;
	}

	public void setContributionProjetsStatutDelai(String contributionProjetsStatutDelai) {
		this.contributionProjetsStatutDelai = contributionProjetsStatutDelai;
	}

	public String getContributionProjetsObservation() {
		return contributionProjetsObservation;
	}

	public void setContributionProjetsObservation(String contributionProjetsObservation) {
		this.contributionProjetsObservation = contributionProjetsObservation;
	}

	public String getSupportUtilisateursPlanifie() {
		return supportUtilisateursPlanifie;
	}

	public void setSupportUtilisateursPlanifie(String supportUtilisateursPlanifie) {
		this.supportUtilisateursPlanifie = supportUtilisateursPlanifie;
	}

	public String getSupportUtilisateursStatutActivites() {
		return supportUtilisateursStatutActivites;
	}

	public void setSupportUtilisateursStatutActivites(String supportUtilisateursStatutActivites) {
		this.supportUtilisateursStatutActivites = supportUtilisateursStatutActivites;
	}

	public String getSupportUtilisateursTaches() {
		return supportUtilisateursTaches;
	}

	public void setSupportUtilisateursTaches(String supportUtilisateursTaches) {
		this.supportUtilisateursTaches = supportUtilisateursTaches;
	}

	public Integer getSupportUtilisateursNombreTotal() {
		return supportUtilisateursNombreTotal;
	}

	public void setSupportUtilisateursNombreTotal(Integer supportUtilisateursNombreTotal) {
		this.supportUtilisateursNombreTotal = supportUtilisateursNombreTotal;
	}

	public String getSupportUtilisateursStatutTaches() {
		return supportUtilisateursStatutTaches;
	}

	public void setSupportUtilisateursStatutTaches(String supportUtilisateursStatutTaches) {
		this.supportUtilisateursStatutTaches = supportUtilisateursStatutTaches;
	}

	public LocalDate getSupportUtilisateursDateButoir() {
		return supportUtilisateursDateButoir;
	}

	public void setSupportUtilisateursDateButoir(LocalDate supportUtilisateursDateButoir) {
		this.supportUtilisateursDateButoir = supportUtilisateursDateButoir;
	}

	public String getSupportUtilisateursStatutDelai() {
		return supportUtilisateursStatutDelai;
	}

	public void setSupportUtilisateursStatutDelai(String supportUtilisateursStatutDelai) {
		this.supportUtilisateursStatutDelai = supportUtilisateursStatutDelai;
	}

	public String getSupportUtilisateursObservation() {
		return supportUtilisateursObservation;
	}

	public void setSupportUtilisateursObservation(String supportUtilisateursObservation) {
		this.supportUtilisateursObservation = supportUtilisateursObservation;
	}

	public String getSupportPartenairesPlanifie() {
		return supportPartenairesPlanifie;
	}

	public void setSupportPartenairesPlanifie(String supportPartenairesPlanifie) {
		this.supportPartenairesPlanifie = supportPartenairesPlanifie;
	}

	public String getSupportPartenairesStatutActivites() {
		return supportPartenairesStatutActivites;
	}

	public void setSupportPartenairesStatutActivites(String supportPartenairesStatutActivites) {
		this.supportPartenairesStatutActivites = supportPartenairesStatutActivites;
	}

	public String getSupportPartenairesTaches() {
		return supportPartenairesTaches;
	}

	public void setSupportPartenairesTaches(String supportPartenairesTaches) {
		this.supportPartenairesTaches = supportPartenairesTaches;
	}

	public Integer getSupportPartenairesNombreTotal() {
		return supportPartenairesNombreTotal;
	}

	public void setSupportPartenairesNombreTotal(Integer supportPartenairesNombreTotal) {
		this.supportPartenairesNombreTotal = supportPartenairesNombreTotal;
	}

	public String getSupportPartenairesStatutTaches() {
		return supportPartenairesStatutTaches;
	}

	public void setSupportPartenairesStatutTaches(String supportPartenairesStatutTaches) {
		this.supportPartenairesStatutTaches = supportPartenairesStatutTaches;
	}

	public LocalDate getSupportPartenairesDateButoir() {
		return supportPartenairesDateButoir;
	}

	public void setSupportPartenairesDateButoir(LocalDate supportPartenairesDateButoir) {
		this.supportPartenairesDateButoir = supportPartenairesDateButoir;
	}

	public String getSupportPartenairesStatutDelai() {
		return supportPartenairesStatutDelai;
	}

	public void setSupportPartenairesStatutDelai(String supportPartenairesStatutDelai) {
		this.supportPartenairesStatutDelai = supportPartenairesStatutDelai;
	}

	public String getSupportPartenairesObservation() {
		return supportPartenairesObservation;
	}

	public void setSupportPartenairesObservation(String supportPartenairesObservation) {
		this.supportPartenairesObservation = supportPartenairesObservation;
	}

	public String getReportingPlanifie() {
		return reportingPlanifie;
	}

	public void setReportingPlanifie(String reportingPlanifie) {
		this.reportingPlanifie = reportingPlanifie;
	}

	public String getReportingStatutActivites() {
		return reportingStatutActivites;
	}

	public void setReportingStatutActivites(String reportingStatutActivites) {
		this.reportingStatutActivites = reportingStatutActivites;
	}

	public String getReportingTaches() {
		return reportingTaches;
	}

	public void setReportingTaches(String reportingTaches) {
		this.reportingTaches = reportingTaches;
	}

	public Integer getReportingNombreTotal() {
		return reportingNombreTotal;
	}

	public void setReportingNombreTotal(Integer reportingNombreTotal) {
		this.reportingNombreTotal = reportingNombreTotal;
	}

	public String getReportingStatutTaches() {
		return reportingStatutTaches;
	}

	public void setReportingStatutTaches(String reportingStatutTaches) {
		this.reportingStatutTaches = reportingStatutTaches;
	}

	public LocalDate getReportingDateButoir() {
		return reportingDateButoir;
	}

	public void setReportingDateButoir(LocalDate reportingDateButoir) {
		this.reportingDateButoir = reportingDateButoir;
	}

	public String getReportingStatutDelai() {
		return reportingStatutDelai;
	}

	public void setReportingStatutDelai(String reportingStatutDelai) {
		this.reportingStatutDelai = reportingStatutDelai;
	}

	public String getReportingObservation() {
		return reportingObservation;
	}

	public void setReportingObservation(String reportingObservation) {
		this.reportingObservation = reportingObservation;
	}

	public String getAutresPlanifie() {
		return autresPlanifie;
	}

	public void setAutresPlanifie(String autresPlanifie) {
		this.autresPlanifie = autresPlanifie;
	}

	public String getAutresStatutActivites() {
		return autresStatutActivites;
	}

	public void setAutresStatutActivites(String autresStatutActivites) {
		this.autresStatutActivites = autresStatutActivites;
	}

	public String getAutresTaches() {
		return autresTaches;
	}

	public void setAutresTaches(String autresTaches) {
		this.autresTaches = autresTaches;
	}

	public Integer getAutresNombreTotal() {
		return autresNombreTotal;
	}

	public void setAutresNombreTotal(Integer autresNombreTotal) {
		this.autresNombreTotal = autresNombreTotal;
	}

	public String getAutresStatutTaches() {
		return autresStatutTaches;
	}

	public void setAutresStatutTaches(String autresStatutTaches) {
		this.autresStatutTaches = autresStatutTaches;
	}

	public LocalDate getAutresDateButoir() {
		return autresDateButoir;
	}

	public void setAutresDateButoir(LocalDate autresDateButoir) {
		this.autresDateButoir = autresDateButoir;
	}

	public String getAutresStatutDelai() {
		return autresStatutDelai;
	}

	public void setAutresStatutDelai(String autresStatutDelai) {
		this.autresStatutDelai = autresStatutDelai;
	}

	public String getAutresObservation() {
		return autresObservation;
	}

	public void setAutresObservation(String autresObservation) {
		this.autresObservation = autresObservation;
	}


    // Constructors 
	
	public ReportDts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setDate(LocalDate now) {
		// TODO Auto-generated method stub
		
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	@PrePersist
    protected void onCreate() {
        creationDate = new Date();
    }

	public boolean isWeeklyReport() {
	    return isWeeklyReport != null ? isWeeklyReport : false;
	}

	public void setWeeklyReport(Boolean isWeeklyReport) {
	    this.isWeeklyReport = isWeeklyReport;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}
	
    
}
