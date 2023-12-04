package com.ongpatinhasquebrilham.petcontrol.domain.service;

import com.ongpatinhasquebrilham.petcontrol.domain.model.Report;
import com.ongpatinhasquebrilham.petcontrol.domain.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReportService {

    private ReportRepository reportRepository;

    @Transactional
    public void save(Report report) {
        reportRepository.save(report);
    }

}