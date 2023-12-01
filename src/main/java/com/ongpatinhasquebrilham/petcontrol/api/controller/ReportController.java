package com.ongpatinhasquebrilham.petcontrol.api.controller;

import com.ongpatinhasquebrilham.petcontrol.domain.model.Report;
import com.ongpatinhasquebrilham.petcontrol.domain.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/report")
public class ReportController {

    private ReportService statisticService;

    @GetMapping
    public ResponseEntity<?> dummy() {
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> createReport() {
        statisticService.save(new Report());
        return ResponseEntity.ok().build();
    }

}
