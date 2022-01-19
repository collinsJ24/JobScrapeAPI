package com.job.scrape.controller;

import com.job.scrape.model.Job;
import com.job.scrape.payload.request.JobScrapeRequest;
import com.job.scrape.payload.response.MessageResponse;
import com.job.scrape.repository.JobRepository;
import com.job.scrape.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/scraper")
public class ScraperController {

    @Autowired
    AuthenticationManager authenticationManager;

    private ScraperService scraperService;

    @Autowired
    private JobRepository jobRepo;

    @Autowired
    public ScraperController(ScraperService scraperService){
        this.scraperService = scraperService;
    }

    @GetMapping("/{keyword}")
    public List<Job> getJobs(@PathVariable String keyword) {
        return scraperService.getJobs(keyword, new ArrayList<>());
    }

    @PostMapping("/job_keywords")
    public ResponseEntity<?> registerUser(@Valid @RequestBody JobScrapeRequest jobScrapeRequest) {

        List<Job> jobs = scraperService.getJobs(jobScrapeRequest.getJobKeywords()
                , jobScrapeRequest.getJobSitesToScrape());

       jobRepo.saveAll(jobs);
        return ResponseEntity.ok(new MessageResponse("Jobs saved successfully!"));

    }
}
