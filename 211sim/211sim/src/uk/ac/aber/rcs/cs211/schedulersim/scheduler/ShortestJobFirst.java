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
public class ShortestJobFirst implements Scheduler {

    protected ArrayList<Job> shortlist;
    private int numberOfJobs;
    private boolean beenInserted;

    public ShortestJobFirst() {
        this.shortlist = new ArrayList<Job>();
        this.numberOfJobs = 0;
        this.beenInserted = false;
    }

    /**
     * With this strategy the scheduler arranges processes with the shortest job
     * to be next in the shortlist. This requires advanced knowledge or
     * estimations about the time required for a process to complete. This
     * method filters through all the jobs in the shortlist and returns the
     * shortest job every time.
     *
     * @return the shortest job.
     * @throws SchedulerException
     */
    public Job getNextJob() throws SchedulerException {
        Job lastJobReturned;
        if (this.numberOfJobs < 1) {
            throw new SchedulerException("Empty Queue");
        }
        lastJobReturned = (Job) this.shortlist.get(0);
        return lastJobReturned;
    }

    public void addNewJob(Job job) throws SchedulerException {
        if (this.shortlist.contains(job)) {
            throw new SchedulerException("Job already on Queue");
        } 
        
        beenInserted = false;
        int count = 0;
        
        if(this.shortlist.isEmpty()){
            this.shortlist.add(job);
        } else{
            while(!beenInserted){
                if(this.shortlist.get(count).getLength() > job.getLength()){
                    this.shortlist.add(count, job);
                    beenInserted = true;
                }else{
                     this.shortlist.add(job);
                     beenInserted = true;
                }
                count++;
            }
        }
        numberOfJobs++;
    }

    public void returnJob(Job job) throws SchedulerException {
        if (!this.shortlist.contains(job)) {
            throw new SchedulerException("Job not on Queue");
        }
    }

    public void removeJob(Job job) throws SchedulerException {
        if (!this.shortlist.contains(job)) {
            throw new SchedulerException("Job not on Queue");
        }
        this.shortlist.remove(job);
        this.numberOfJobs--;
    }

    public void reset() {
        this.shortlist.clear();
        this.numberOfJobs = 0;
    }

    public Job[] getJobList() {
        Job[] jobs = new Job[shortlist.size()];
        for (int i = 0; i < shortlist.size(); i++) {
            jobs[i] = this.shortlist.get(i);
        }
        return jobs;
    }
}