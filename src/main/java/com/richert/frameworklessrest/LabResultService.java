package com.richert.frameworklessrest;

import java.sql.SQLException;
import java.util.List;

class LabResultService {

    private static final LabResultService INSTANCE = new LabResultService();
    private final LabResultRepository repository = LabResultRepository.getInstance();

    private LabResultService() {
    }

    public static LabResultService getInstance() {
        return INSTANCE;
    }

    public void saveLabResult(LabResultDTO dto) throws SQLException {
        LabResult entity = LabResultMapper.toEntity(dto);
        repository.save(entity);
    }

    public List<LabResult> getAllResults() throws SQLException {
        return repository.findAll();
    }

    public List<LabResult> getResultsByPatientId(String patientId) throws SQLException {
        return repository.findByPatientId(patientId);
    }
}