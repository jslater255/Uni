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

    public ShortestJobFirst() {
        this.shortlist = new ArrayList<Job>();
        this.numberOfJobs = 0;
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
        if (this.numberOfJobs < 1) {
            throw new SchedulerException("Empty Queue");
        }
        // Start by takeing the first job in the shortlist.
        // Without this it will compare null and fail.
        Job shortestJobReturned = (Job) shortlist.get(0);

        //Loop through everything comparing the length of each job.
        for (int i = 1; i < numberOfJobs; i++) {
            //If the job length is shorter then make this the new shortest Job
            if (shortestJobReturned.getLength() > shortlist.get(i).getLength()) {
                shortestJobReturned = (Job) shortlist.get(i);
            }
        }
        return shortestJobReturned;
    }

    public void addNewJob(Job job) throws SchedulerException {
        if (this.shortlist.contains(job)) {
            throw new SchedulerException("Job already on Queue");
        }

        this.shortlist.add(this.numberOfJobs, job);
        this.numberOfJobs++;
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