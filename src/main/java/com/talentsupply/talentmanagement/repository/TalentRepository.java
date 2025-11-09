package com.talentsupply.talentmanagement.repository;

import com.talentsupply.talentmanagement.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalentRepository extends JpaRepository<Talent, Long> {
    List<Talent> findBySkillsContainingIgnoreCase(String skill);
    List<Talent> findByCurrentStatusIgnoreCase(String status);
}
