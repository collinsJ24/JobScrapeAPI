package com.job.scrape.controller;

import com.job.scrape.model.Job;
import com.job.scrape.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ScraperController {

    private ScraperService scraperService;

    @Autowired
    public ScraperController(ScraperService scraperService){
        this.scraperService = scraperService;
    }

    @GetMapping(path = "/{keyword}")
    public List<Job> getJobs(@PathVariable String keyword) {
        return scraperService.getJobs(keyword);
    }
}
