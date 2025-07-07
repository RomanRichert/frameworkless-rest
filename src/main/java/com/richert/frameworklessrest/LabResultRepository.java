package com.richert.frameworklessrest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class LabResultRepository {

    private static final LabResultRepository INSTANCE = new LabResultRepository();
    private final String url = Config.get("DB_URL");
    private final String user = Config.get("DB_USER");
    private final String password = Config.get("DB_PASSWORD");

    private LabResultRepository() {
    }

    public static LabResultRepository getInstance() {
        return INSTANCE;
    }

    public void save(LabResult entity) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO lab_results (id, patient_id, result, unit, timestamp) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, entity.getId());
            statement.setString(2, entity.getPatientId());
            statement.setDouble(3, entity.getResult());
            statement.setString(4, entity.getUnit());
            statement.setTimestamp(5, entity.getTimestamp());
            statement.executeUpdate();
        }
    }


    public List<LabResult> findAll() throws SQLException {
        List<LabResult> results = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM lab_results")) {

            while (rs.next()) {
                results.add(fromResultSet(rs));
            }
        }
        return results;
    }

    public List<LabResult> findByPatientId(String patientId) throws SQLException {
        List<LabResult> results = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM lab_results WHERE patient_id = ?")) {

            stmt.setString(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(fromResultSet(rs));
                }
            }
        }
        return results;
    }

    private LabResult fromResultSet(ResultSet rs) throws SQLException {
        return new LabResult(
                UUID.fromString(rs.getString("id")),
                rs.getString("patient_id"),
                rs.getDouble("result"),
                rs.getString("unit"),
                rs.getTimestamp("timestamp")
        );
    }
}
