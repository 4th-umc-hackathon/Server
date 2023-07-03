package com.example.gpt.src.query.model;

import com.example.gpt.src.user.model.User;
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
    public MyQuery(String level, String age, String place, String myRole, String yourRole, String situation, User user) {
        this.level = level;
        this.age = age;
        this.place = place;
        this.myRole = myRole;
        this.yourRole = yourRole;
        this.situation = situation;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;
    private String level;
    private String age;
    private String place;
    @Column(columnDefinition = "TEXT")
    private String myRole;
    private String yourRole;
    private String situation;
    @Column(columnDefinition = "TIMESTAMP")
    private String createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
