package com.refactor.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUIZ")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @Column(name = "quiz_id")
    private String id;
    private String level;
    private String name;
    private String url;


}
