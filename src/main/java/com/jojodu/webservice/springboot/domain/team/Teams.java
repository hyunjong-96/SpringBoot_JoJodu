package com.jojodu.webservice.springboot.domain.team;

import com.jojodu.webservice.springboot.domain.members.Members;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Members> membersList = new ArrayList<>();

    @Builder
    public Teams(String name){
        this.name=name;
    }
}
