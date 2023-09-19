package com.example.VotingSystem.controller;

import com.example.VotingSystem.entity.Region;
import com.example.VotingSystem.entity.Voting;
import com.example.VotingSystem.entity.VotingData;
import com.example.VotingSystem.repository.VotingSystemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(VotingSystemController.class)
public class VotingSystemControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VotingSystemRepository repo;

    @Test
    void shouldReturnListOfVotingDetails() throws Exception {
        List<Voting> V = new ArrayList<>(
                Arrays.asList(new Voting(1, new Region("South","Bengaluru","Karnataka",909098),new VotingData(2000L,1000L) ,"BJP"),
                        new Voting(2, new Region("North","Kolkatta","WestBengal",898767),new VotingData(20000L,16000L) ,"Congress"),
                        new Voting(3, new Region("Middle","Mumbai","Maharashtra",411033),new VotingData(80000L,70000L) ,"Shivsena")));

        when(repo.findAll()).thenReturn(V);
        mockMvc.perform(get("/voting/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(V.size()))
                .andDo(print());
    }

    @Test
    void shouldReturnNoContentForFindAll() throws Exception {
        List<Voting> V = Collections.emptyList();

        when(repo.findAll()).thenReturn(V);
        mockMvc.perform(get("/voting/all"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void shouldCreateVotingEntry() throws Exception {
        Voting V = new Voting(1, new Region("South","Bengaluru","Karnataka",9090987), new VotingData(6000L,3500L), "AJP");

        mockMvc.perform(post("/voting/add").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(V)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnListOfVotingEntriesWithFilter() throws Exception {
        List<Voting> V = new ArrayList<>(
                Arrays.asList(new Voting(1, new Region("South","Bengaluru","Karnataka",909098),new VotingData(2000L,1000L) ,"BJP"),
                        new Voting(2, new Region("North","Kolkatta","WestBengal",898767),new VotingData(20000L,16000L) ,"Congress"),
                        new Voting(3, new Region("Middle","Mumbai","Maharashtra",411033),new VotingData(80000L,70000L) ,"Shivsena")));

        String winnerPary = "BJP";

        when(repo.findAllByWinnerPary(winnerPary)).thenReturn(V);
        mockMvc.perform(get("/voting/findByWinningParty/{winnerPary}",winnerPary))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(V.size()))
                .andDo(print());
    }

    @Test
    void shouldReturnNoContentWhenFilter() throws Exception {
        String winnerPary = "abc";

        List<Voting> V = Collections.emptyList();

        when(repo.findAllByWinnerPary(winnerPary)).thenReturn(V);
        mockMvc.perform(get("/voting/findByWinningParty/{winnerPary}",winnerPary))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void shouldDeleteVoting() throws Exception {
        int uid = 1;

        doNothing().when(repo).deleteById(uid);
        mockMvc.perform(delete("/voting/deleteById/{uid}", uid))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

}
