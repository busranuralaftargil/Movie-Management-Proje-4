package com.sau.mm.service;

import com.sau.mm.dto.ClassificationDTO;
import com.sau.mm.model.Classification;
import com.sau.mm.repository.ClassificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;

    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public List<ClassificationDTO> getAllClassifications() {
        return classificationRepository.findAll().stream()
                .map(Classification::viewAsClassificationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClassificationDTO createClassification(Classification classification) {
        if (classification.getDate() == null) {
            classification.setDate(LocalDate.now());
        }
        Classification savedClassification = classificationRepository.save(classification);
        return savedClassification.viewAsClassificationDTO();
    }

    @Override
    public ClassificationDTO getClassificationById(long id) {
        Classification classification = classificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classification Not Found: " + id));
        return classification.viewAsClassificationDTO();
    }

    @Override
    public ClassificationDTO updateClassification(long id, Classification classification) {
        Classification existingClassification = classificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classification Not Found: " + id));

        existingClassification.setMovie(classification.getMovie());
        existingClassification.setCategory(classification.getCategory());
        existingClassification.setDate(LocalDate.now());

        Classification updatedClassification = classificationRepository.save(existingClassification);
        return updatedClassification.viewAsClassificationDTO();
    }

    @Override
    public void deleteClassification(long id) {
        Classification existingClassification = classificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classification Not Found: " + id));
        classificationRepository.delete(existingClassification);
    }
}