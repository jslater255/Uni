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

    protected ArrayList<Job> priorityQueue;
    private int numberOfJobs;
    private boolean beenInserted;
    private int count;

    public PriorityScheduler() {
        this.priorityQueue = new ArrayList<Job>();
        this.numberOfJobs = 0;
        this.beenInserted = false;
        this.count = 0;
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
        Job highestPriority = (Job) priorityQueue.get(0);

        return highestPriority;
    }

    public void addNewJob(Job job) throws SchedulerException {
        if (this.priorityQueue.contains(job)) {
            throw new SchedulerException("Job already on Queue");
        } 
        
        beenInserted = false;
        count = 0;
        
        if(this.priorityQueue.isEmpty()){
            this.priorityQueue.add(job);
        } else{
            while(!beenInserted){
                if(this.priorityQueue.get(count).getPriority() > job.getPriority()){
                    this.priorityQueue.add(count, job);
                    beenInserted = true;
                }else{
                     this.priorityQueue.add(job);
                     beenInserted = true;
                }
                count++;
            }
        }
        numberOfJobs++;
    }

    public void returnJob(Job job) throws SchedulerException {
        if (!this.priorityQueue.contains(job)) {
            throw new SchedulerException("Job not on Queue");
        }
    }

    public void removeJob(Job job) throws SchedulerException {
        if (!this.priorityQueue.contains(job)) {
            throw new SchedulerException("Job not on Queue");
        }
        this.priorityQueue.remove(job);
        this.numberOfJobs--;
    }

    public void reset() {
        this.priorityQueue.clear();
        this.numberOfJobs = 0;
    }

    public Job[] getJobList() {
        Job[] jobs = new Job[priorityQueue.size()];
        for (int i = 0; i < priorityQueue.size(); i++) {
            jobs[i] = this.priorityQueue.get(i);
        }
        return jobs;
    }
}
