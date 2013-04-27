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

    /**
     * Each Job has been assigned a fixed priority rank, I will also add on the blocked time, the
     * scheduler gets the highest priority job to return. Lower priority
     * processes get interrupted by incoming higher priority processes but also take into account the blocked time. 
     * this will help not saturate any bigger or less priority jobs 
     * Filters
     * through the jobs finding the highest priority job using the
     * getPriority().
     *
     *
     * @return
     * @throws SchedulerException
     */
    public Job getNextJob() throws SchedulerException {
        if (this.numberOfJobs < 1) {
            throw new SchedulerException("Empty Queue");
        }
        // Sets up with the first job in the queue.
        Job highestPriority = (Job) queue.get(0);

        // Gets each job from the queue compares the priority and the time it has been blocked of each and if it 
        // is lower then the previous then set this as the highest priority 
        // job to return.
        for (int i = 1; i < numberOfJobs; i++) {
            if ((highestPriority.getPriority()) > (queue.get(i).getPriority())){
                highestPriority = (Job) queue.get(i);
            }
        }
        return highestPriority;
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
