package com.capitalnumber.demo.controller;

import com.capitalnumber.demo.exceptions.ResourceUnavailableException;
import com.capitalnumber.demo.model.MatchDetail;
import com.capitalnumber.demo.service.MatchDetailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api/match")
public class MatchDetailsController {

    @Autowired
    MatchDetailService matchDetailService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public MatchDetail saveMatchDetail(@RequestBody MatchDetail matchDetail, HttpServletRequest request){
        log.info("POST request {} received with at time stamp {}", request.getRequestURL(), LocalDateTime.now());
        return matchDetailService.save(matchDetail);
    }

    @GetMapping
    @ResponseBody
    public MatchDetail getMatchDetail(@RequestParam("id") Long id, HttpServletRequest request) throws ResourceUnavailableException {
        log.info("GET request {} received with at time stamp {}", request.getRequestURL(), LocalDateTime.now());
        return matchDetailService.findById(id);
    }

    @DeleteMapping("upcomingmatches")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteUpCommingMatches(HttpServletRequest request) throws ResourceUnavailableException{
        log.info("DELETE request {} received with at time stamp {}", request.getRequestURL(), LocalDateTime.now());
        matchDetailService.deleteUpComingMatches();
        return new ResponseEntity<>("resource deleted successfully", new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("allwinners")
    @ResponseBody
    public List<MatchDetail> getAllWinnerList(HttpServletRequest request){
        log.info("GET request {} received with at time stamp {}", request.getRequestURL(), LocalDateTime.now());
        return matchDetailService.getAllWinnerList();
    }

    @PatchMapping("{id}")
    @ResponseBody
    public ResponseEntity<?> updateMatchDetails(@PathVariable("id") Long id,
                                                @RequestBody JsonPatch patch,
                                                HttpServletRequest request) throws JsonPatchException, JsonProcessingException {
        log.info("PATCH request {} received with at time stamp {}", request.getRequestURL(), LocalDateTime.now());
        MatchDetail matchDetail = matchDetailService.findById(id);
        MatchDetail patchedMatchDetails = applyMatchToMatchDetail(matchDetail, patch);
        matchDetailService.updateMatchDetail(id, patchedMatchDetails);
        return new ResponseEntity<>("resource updated successfully", new HttpHeaders(), HttpStatus.OK);
    }

    private MatchDetail applyMatchToMatchDetail(MatchDetail matchDetail, JsonPatch patch) throws JsonProcessingException, JsonPatchException {
        JsonNode patched = patch.apply(objectMapper.convertValue(matchDetail, JsonNode.class));
        return objectMapper.treeToValue(patched, MatchDetail.class);
    }

    @GetMapping("team/{id}")
    @ResponseBody
    public List<MatchDetail> getMatchesByTeamId(@PathVariable("id") Long id, HttpServletRequest request){
        log.info("GET request {} received with at time stamp {}", request.getRequestURL(), LocalDateTime.now());
        return matchDetailService.getMatchesByTeamId(id);
    }

    @GetMapping("upcomingmatches")
    @ResponseBody
    public List<MatchDetail> getUpcomingMatchesByTeam(@RequestParam("id") Long id, HttpServletRequest request){
        log.info("GET request {} received with at time stamp {}", request.getRequestURL(), LocalDateTime.now());
        return matchDetailService.getUpcomingMatchesByTeamId(id);
    }

}
