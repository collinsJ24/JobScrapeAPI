package com.job.scrape.payload.request;

import java.util.List;

public class JobScrapeRequest {

    private String jobKeywords;

    private List<String> jobSitesToScrape;


    public String getJobKeywords() {
        return jobKeywords;
    }

    public void setJobKeywords(String jobKeywords) {
        this.jobKeywords = jobKeywords;
    }

    public List<String> getJobSitesToScrape() {
        return jobSitesToScrape;
    }

    public void setJobSitesToScrape(List<String> jobSitesToScrape) {
        this.jobSitesToScrape = jobSitesToScrape;
    }
}
