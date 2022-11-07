package com.capitalnumber.demo.controller;

import com.capitalnumber.demo.model.MatchDetail;
import com.capitalnumber.demo.service.MatchDetailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/match")
public class MatchDetailsController {

    @Autowired
    MatchDetailService matchDetailService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    @ResponseBody
    public MatchDetail saveMatchDetail(@RequestBody MatchDetail matchDetail){
        return matchDetailService.save(matchDetail);
    }

    @GetMapping
    @ResponseBody
    public MatchDetail getMatchDetail(@RequestParam("id") Long id){
        return matchDetailService.findById(id);
    }

    @DeleteMapping("upcomingmatches")
    @ResponseBody
    public ResponseEntity<?> deleteUpCommingMatches(){
        matchDetailService.deleteUpComingMatches();
        return new ResponseEntity<>("resource deleted successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("allwinners")
    @ResponseBody
    public List<MatchDetail> getAllWinnerList(){
        return matchDetailService.getAllWinnerList();
    }

    @PatchMapping("{id}")
    @ResponseBody
    public ResponseEntity<?> updateMatchDetails(@PathVariable("id") Long id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {

        MatchDetail matchDetail = matchDetailService.findById(id);
        MatchDetail patchedMatchDetails = applyMatchToMatchDetail(matchDetail, patch);
        matchDetailService.updateMatchDetail(id, patchedMatchDetails);
        return new ResponseEntity<>("resource deleted successfully", new HttpHeaders(), HttpStatus.OK);
    }

    private MatchDetail applyMatchToMatchDetail(MatchDetail matchDetail, JsonPatch patch) throws JsonProcessingException, JsonPatchException {
        JsonNode patched = patch.apply(objectMapper.convertValue(matchDetail, JsonNode.class));
        return objectMapper.treeToValue(patched, MatchDetail.class);
    }

    @GetMapping("team/{id}")
    @ResponseBody
    public List<MatchDetail> getMatchesByTeamId(@PathVariable("id") Long id){
        return matchDetailService.getMatchesByTeamId(id);
    }

    @GetMapping("upcomingmatches")
    @ResponseBody
    public List<MatchDetail> getUpcomingMatchesByTeam(@RequestParam("id") Long id){
        return matchDetailService.getUpcomingMatchesByTeamId(id);
    }

}
