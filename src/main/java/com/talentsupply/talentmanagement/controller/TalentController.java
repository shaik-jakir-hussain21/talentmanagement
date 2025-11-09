package com.talentsupply.talentmanagement.controller;

import com.talentsupply.talentmanagement.entity.Talent;
import com.talentsupply.talentmanagement.TalentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/talents")
public class TalentController {

    private final TalentService service;

    public TalentController(TalentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Talent> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Talent> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Talent create(@RequestBody Talent talent) {
        return service.save(talent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Talent> update(@PathVariable Long id, @RequestBody Talent updated) {
        return service.getById(id).map(t -> {
            t.setName(updated.getName());
            t.setSkills(updated.getSkills());
            t.setExperience(updated.getExperience());
            t.setCurrentStatus(updated.getCurrentStatus());
            t.setInterviewFeedback(updated.getInterviewFeedback());
            return ResponseEntity.ok(service.save(t));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Talent> search(@RequestParam String skill) {
        return service.searchBySkill(skill);
    }

    @GetMapping("/status")
    public List<Talent> filter(@RequestParam String status) {
        return service.filterByStatus(status);
    }
}
