package com.job.scrape.service;

import com.job.scrape.model.Job;

import java.util.List;

public interface ScraperService {

    List<Job> getJobs(String website);
}
