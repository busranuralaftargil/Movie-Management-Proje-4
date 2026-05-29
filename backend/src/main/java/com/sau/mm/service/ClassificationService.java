package com.sau.mm.service;

import com.sau.mm.dto.ClassificationDTO;
import com.sau.mm.model.Classification;
import java.util.List;

public interface ClassificationService {
    List<ClassificationDTO> getAllClassifications();
    ClassificationDTO createClassification(Classification classification);

    ClassificationDTO getClassificationById(long id);
    ClassificationDTO updateClassification(long id, Classification classification);
    void deleteClassification(long id);
}