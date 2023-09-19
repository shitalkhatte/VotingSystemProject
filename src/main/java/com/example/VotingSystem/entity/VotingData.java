package com.example.VotingSystem.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class VotingData
{
    private Long totalNoOfVoters=1L;
    private Long votingCount=1L;

    public VotingData(Long totalNoOfVoters, Long votingCount) {
        this.totalNoOfVoters = totalNoOfVoters;
        this.votingCount = votingCount;
    }

    public Long getTotalNoOfVoters() {
        return totalNoOfVoters;
    }

    public Long getVotingCount() {
        return votingCount;
    }

    public void setTotalNoOfVoters(Long totalNoOfVoters) {
        this.totalNoOfVoters = totalNoOfVoters;
    }

    public void setVotingCount(Long votingCount) {
        this.votingCount = votingCount;
    }
}
