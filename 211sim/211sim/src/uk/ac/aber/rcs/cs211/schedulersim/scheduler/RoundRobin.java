/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.aber.rcs.cs211.schedulersim.scheduler;

import uk.ac.aber.rcs.cs211.schedulersim.Job;
import uk.ac.aber.rcs.cs211.schedulersim.Scheduler;

/**
 *
 * @author Slater
 */
public class RoundRobin implements Scheduler{

    public RoundRobin(){
        
    }
    
    @Override
    public Job getNextJob() throws SchedulerException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addNewJob(Job job) throws SchedulerException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void returnJob(Job job) throws SchedulerException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeJob(Job job) throws SchedulerException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Job[] getJobList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
