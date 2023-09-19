package com.example.VotingSystem.repository;

import com.example.VotingSystem.entity.Voting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotingSystemRepository extends MongoRepository<Voting,Integer> {
    public List<Voting> findAllByWinnerPary(String winnerpary);
}
