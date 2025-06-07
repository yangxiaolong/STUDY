package jdbc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @auther yangxiaolong
 * @create 2025/6/7
 */
public class DateUser {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jsonFormatTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate localDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime localDateTime2;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;

    public Date getJsonFormatTime() {
        return jsonFormatTime;
    }

    public void setJsonFormatTime(Date jsonFormatTime) {
        this.jsonFormatTime = jsonFormatTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDateTime getLocalDateTime2() {
        return localDateTime2;
    }

    public void setLocalDateTime2(LocalDateTime localDateTime2) {
        this.localDateTime2 = localDateTime2;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
