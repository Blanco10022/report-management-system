package com.afriland.rapport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afriland.rapport.model.ReportDocument;

public interface ReportDocumentRepository extends JpaRepository<ReportDocument, Integer> {
    List<ReportDocument> findByReportId(int reportId);
}
