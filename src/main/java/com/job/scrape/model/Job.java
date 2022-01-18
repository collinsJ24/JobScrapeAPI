package com.job.scrape.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "jobs")
public class Job implements Serializable {

    public Job(){}

    private static final long serialVersionUID = 3252591505029724236L;

    @Id
    @Column(nullable=false)
    private Long id;

    private String JobTitle;

    private String PostedBy;
    private String Salary;
    private String time;
    private String location;
    private String remote;
    private String JobDescription;

    public String getPostedBy() {
        return PostedBy;
    }

    public void setPostedBy(String postedBy) {
        PostedBy = postedBy;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getJobDescription() {
        return JobDescription;
    }

    public void setJobDescription(String jobDescription) {
        JobDescription = jobDescription;
    }


}
