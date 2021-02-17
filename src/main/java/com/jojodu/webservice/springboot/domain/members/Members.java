package com.jojodu.webservice.springboot.domain.members;

import com.jojodu.webservice.springboot.domain.address.Address;
import com.jojodu.webservice.springboot.domain.period.Period;
import com.jojodu.webservice.springboot.domain.team.Teams;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Teams team;

    @Embedded
    private Address address;

    @Embedded
    private Period period;

    public void setTeam(Teams team){
        if(this.team != null){
            this.team.getMembersList().remove(this);
        }
        this.team = team;
        team.getMembersList().add(this);
    }

    @Builder
    public Members(String name){
        this.name=name;
    }
}
