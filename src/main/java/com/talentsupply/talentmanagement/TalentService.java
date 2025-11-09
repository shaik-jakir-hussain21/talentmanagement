package com.talentsupply.talentmanagement;

import com.talentsupply.talentmanagement.entity.Talent;
import com.talentsupply.talentmanagement.repository.TalentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TalentService {

    private final TalentRepository repository;

    public TalentService(TalentRepository repository) {
        this.repository = repository;
    }

    public List<Talent> getAll() {
        return repository.findAll();
    }

    public Talent save(Talent talent) {
        return repository.save(talent);
    }

    public Optional<Talent> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Talent> searchBySkill(String skill) {
        return repository.findBySkillsContainingIgnoreCase(skill);
    }

    public List<Talent> filterByStatus(String status) {
        return repository.findByCurrentStatusIgnoreCase(status);
    }
}
