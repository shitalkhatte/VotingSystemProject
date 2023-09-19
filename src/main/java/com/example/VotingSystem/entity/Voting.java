package com.example.VotingSystem.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@ToString
@Document("Voting")
public class Voting
{
    @Id
    private Integer uid;
    private Region region;
    private VotingData data;
    private String winnerPary;

    public Integer getUid() {
        return uid;
    }

    public Region getRegion() {
        return region;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setData(VotingData data) {
        this.data = data;
    }

    public void setWinnerPary(String winnerPary) {
        this.winnerPary = winnerPary;
    }

    public VotingData getData() {
        return data;
    }

    public String getWinnerPary() {
        return winnerPary;
    }

    public Voting(Integer uid, Region region, VotingData data, String winnerPary) {
        this.uid = uid;
        this.region = region;
        this.data = data;
        this.winnerPary = winnerPary;
    }
}
