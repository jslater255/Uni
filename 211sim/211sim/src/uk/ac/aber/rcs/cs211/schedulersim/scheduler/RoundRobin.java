/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.aber.rcs.cs211.schedulersim.scheduler;

import java.util.*;
import uk.ac.aber.rcs.cs211.schedulersim.*;

/**
 *
 * @author Slater
 */
public class RoundRobin implements Scheduler {

    protected ArrayList<Job> queue;
    private int numberOfJobs;
    private int count;

    public RoundRobin() {
        this.queue = new ArrayList<Job>();
        this.numberOfJobs = 0;
        this.count = 0;
    }

    public Job getNextJob() throws SchedulerException {
        Job lastJobReturned;
        if (this.numberOfJobs < 1) {
            throw new SchedulerException("Empty Queue");
        }
        if (count >= numberOfJobs) {
            count = 0;
        }
        lastJobReturned = (Job) this.queue.get(count);
        count++;

        return lastJobReturned;
    }

    public void addNewJob(Job job) throws SchedulerException {
        if (this.queue.contains(job)) {
            throw new SchedulerException("Job already on Queue");
        }
        this.queue.add(this.numberOfJobs, job);
        this.numberOfJobs++;
    }

    public void returnJob(Job job) throws SchedulerException {
        if (!this.queue.contains(job)) {
            throw new SchedulerException("Job not on Queue");
        }
        // nothing to do in this implementation.
    }

    public void removeJob(Job job) throws SchedulerException {
        if (!this.queue.contains(job)) {
            throw new SchedulerException("Job not on Queue");
        }
        this.queue.remove(job);
        this.numberOfJobs--;
    }

    public void reset() {
        this.queue.clear();
        this.numberOfJobs = 0;
    }

    public Job[] getJobList() {
        Job[] jobs = new Job[queue.size()];
        for (int i = 0; i < queue.size(); i++) {
            jobs[i] = this.queue.get(i);
        }
        return jobs;
    }
}
