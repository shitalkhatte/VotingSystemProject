package com.example.VotingSystem.controller;

import com.example.VotingSystem.entity.Voting;
import com.example.VotingSystem.repository.VotingSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/voting")
public class VotingSystemController
{
    @Autowired
    private VotingSystemRepository repo;

    @GetMapping("/all")
    public ResponseEntity<List<Voting>> getAllTutorials() {

            List<Voting> V = new ArrayList<Voting>();
            V=repo.findAll();

        if (V.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(V, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Voting> createTutorial(@RequestBody Voting voting) {

            Voting _voting = repo.save(voting);
            return new ResponseEntity<>(_voting, HttpStatus.CREATED);

    }

    @GetMapping("/findByWinningParty/{winnerPary}")
    public ResponseEntity<List<Voting>> getTutorialById(@PathVariable String winnerPary) {
        List<Voting> V = repo.findAllByWinnerPary(winnerPary);

        if (V.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(V, HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteById/{uid}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable int uid) {
            repo.deleteById(uid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/summary")
    public List<String> summaryOfVoting()
    {
        List<Voting> list=repo.findAll();
        List<String> res=new ArrayList<>();
        int n=list.size();
        long arr1[]=new long[n];
        long arr2[]=new long[n];
        for(int i=0;i<list.size();i++)
        {
            arr1[i]=list.get(i).getData().getTotalNoOfVoters();
            arr2[i]=list.get(i).getData().getVotingCount();
        }
        for(int i=0;i<list.size();i++)
        {
            res.add(i, "there is total " + arr1[i] + " voters out of that " + arr2[i] + " voters vote for "+ list.get(i).getWinnerPary() + " in " + list.get(i).getRegion().getCity() + " city ");
        }
        return res;
    }
}
