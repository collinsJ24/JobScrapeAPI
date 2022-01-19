package com.job.scrape.service.impl;

import com.job.scrape.model.Job;
import com.job.scrape.service.ScraperService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperServiceImpl implements ScraperService {


    @Value("#{'${website.urls}'.split(',')}")
    List<String> urls;

    @Override
    public List<Job> getJobs(String keywords, List<String> jobSitesToScrape) {

        List<Job> jobs = new ArrayList<>();

        for (String url: jobSitesToScrape) {

            if (url.equals("Reed")) {
                url = urls.get(0);

                extractDataFromReed(jobs, url + keywords);
            }
            if(url.equals("Indeed")){
                url = urls.get(1);
                extractDataFromReed(jobs,url + keywords);
            }

        }

        return jobs;
    }

    private void extractDataFromReed( List<Job> jobs, String url) {

        try {
            Document document = Jsoup.connect(url).get();
            Element element = document.getElementById("content");

            Elements elements = element.getElementsByClass("job-result");

            for (Element ads: elements) {
                Job job = new Job();
                Elements jobDetails = ads.getElementsByClass("col-sm-12 col-md-9 col-lg-9 details");
                Element JobTitle = jobDetails.select("h3.title").first();
                String title = JobTitle.getElementsByTag("a").attr("title");
                Elements metaData = jobDetails.select("div.metadata").first()
                        .select("ul");
                Elements description = jobDetails.select("div.description").first()
                        .select("p");
                String salary = "";
                String time = "";
                String location = "";
                String remote = "";
                String jobDescription = description.text();
                if (!StringUtils.isEmpty(title) ) {
                Element elementData = metaData.get(0);
                salary = elementData.select("li").first().text();
                time = elementData.select("li.time").first().text();
                Element locationAndRemote = metaData.get(1);
                location = locationAndRemote.select("li").first().text();
                if(locationAndRemote.select("li").size() > 1)
                    remote = locationAndRemote.select("li.remote").first().text();

                }

                if (!StringUtils.isEmpty(title) ) {
                    job.setJobTitle(title);
                    job.setJobDescription(jobDescription);
                    //job.setPostedBy(ads.attr("posted-by"));
                    job.setTime(time);
                    job.setSalary(salary);
                    job.setLocation(location);
                    job.setRemote(remote);
                }
                if (job.getJobTitle() != null) jobs.add(job);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
