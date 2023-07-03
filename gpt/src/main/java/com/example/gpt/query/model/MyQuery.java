package com.example.gpt.query.model;

import com.example.gpt.utils.TimestampFormatter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "my_query")
@Getter
@Setter
@NoArgsConstructor
public class MyQuery {
    @PrePersist
    public void prePersist() {
        this.createdAt = this.createdAt == null ?
                TimestampFormatter.format(new Timestamp(System.currentTimeMillis()))
                : this.createdAt;
    }
    @Builder
    public MyQuery(String level, String place, String situation, String myRole, String aiRole) {
        this.level = level;
        this.place = place;
        this.situation = situation;
        this.myRole = myRole;
        this.aiRole = aiRole;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;
    private String level;
    private String place;
    private String situation;
    private String myRole;
    private String aiRole;
    @Column(columnDefinition = "TIMESTAMP")
    private String createdAt;
}
