package com.afriland.rapport.model;

import java.time.LocalDate;
import java.util.List;

public class ReportViewModel {
    private LocalDate date;
    private String résumé;

    // Getters and setters

 public String  getRésume() {
	 return résumé;
 }
 
 public void setRésume(String résume) {
	this.résumé = résume;
 }

public LocalDate getDate() {
	return date;
}

public void setDate(LocalDate date) {
	this.date = date;
}
    
}

