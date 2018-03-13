package dev.chernykh.cellular.notifier;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public class NotificationRepository implements NotificationOperations {
    private final JdbcTemplate jdbcTemplate;
    
    public NotificationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Notification> findAll(String tariffName, LocalDate from, LocalDate to) {
        String sql = "SELECT t.name, o.name, o.cur_price, o.cur_currency, o.old_price, o.old_currency, o" +
            ".date_of_change " +
            "FROM tariffs t " +
            "INNER JOIN options o ON t.id = o.tariff_id " +
            "HAVING t.name LIKE ? AND o.date_of_change BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Notification notification = null;
            if (Objects.nonNull(rs)) {
                notification = new Notification();
                notification.setTariffName(rs.getString("t.name"));
                notification.setOptionName(rs.getString("o.name"));
                notification.setNewPrice(rs.getDouble("o.cur_price"), rs.getString("o.cur_currency"));
                notification.setOldPrice(rs.getDouble("o.old_price"), rs.getString("o.old_currency"));
                notification.setDateOfChange(rs.getDate("o.date_of_change").toLocalDate());
            }
            return notification;
        }, tariffName, Date.valueOf(from), Date.valueOf(to));
    }
}
