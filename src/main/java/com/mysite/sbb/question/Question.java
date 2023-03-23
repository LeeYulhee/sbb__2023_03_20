package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    // OneToMany 자바세상에서의 편의를 위해서 필드 생성
    // 이 녀석은 실제 DB 테이블에 칼럼이 생성되지 않는다.
    // DB는 배열이나 리스트를 저장할 수 없다.
    // 칼럼에 저장할 수 없다.
    // 만들어도 되고 안 만들어도 됩니다.
    // 다만 만들면 해당 객체(질문객체)에서 관련된 답변들을 찾을 때 편합니다.
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    // OneToMany에는 직접 객체 초기화 해주기
    private List<Answer> answerList = new ArrayList<>();

    public void addAnswer(Answer a) {
        a.setQuestion(this);
        answerList.add(a);
    }

    @ManyToOne
    private SiteUser author;
}
