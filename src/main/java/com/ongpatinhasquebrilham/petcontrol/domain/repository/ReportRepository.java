package com.ongpatinhasquebrilham.petcontrol.domain.repository;

import com.ongpatinhasquebrilham.petcontrol.domain.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

}