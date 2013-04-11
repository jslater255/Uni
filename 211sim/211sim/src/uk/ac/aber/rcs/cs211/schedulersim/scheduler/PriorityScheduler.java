/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.aber.rcs.cs211.schedulersim.scheduler;

import java.util.ArrayList;
import uk.ac.aber.rcs.cs211.schedulersim.Job;
import uk.ac.aber.rcs.cs211.schedulersim.Scheduler;

/**
 *
 * @author Slater
 */
public class PriorityScheduler implements Scheduler {

    protected ArrayList<Job> queue;
    private int numberOfJobs;

    public PriorityScheduler() {
        this.queue = new ArrayList<Job>();
        this.numberOfJobs = 0;
    }

    public Job getNextJob() throws SchedulerException {
        if (this.numberOfJobs<1) throw new SchedulerException("Empty Queue");
        Job lastJobReturned = (Job)queue.get(0);
       
        for(int i = 1; i < numberOfJobs; i++){
            if(lastJobReturned.getPriority() > queue.get(i).getPriority()){
                lastJobReturned = (Job)queue.get(i);
            }
        }
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
