package project20280.priorityblockingqueue;

import javax.print.attribute.standard.JobPriority;

import static java.util.Objects.compare;

public class Job implements Runnable, Comparable<Job>{
    private String jobName;
    private project20280.priorityblockingqueue.JobPriority jobPriority;
    private Integer expectedDuration;

    public Job(String job1, project20280.priorityblockingqueue.JobPriority jobPriority) {
        this.jobName = job1;
        this.jobPriority = jobPriority;
        expectedDuration = null;
    }

    public Job(String job1, int eDuration,project20280.priorityblockingqueue.JobPriority jobPriority) {
        this.jobName = job1;
        this.jobPriority = jobPriority;
        expectedDuration = eDuration;
    }

    @Override
    public int compareTo(Job other){
        //initially compare job priority
        int priorityCompare = other.jobPriority.compareTo(this.jobPriority);

        if (priorityCompare != 0) {
            return priorityCompare;
        }

        //then compare duration
        return Long.compare(this.expectedDuration, other.expectedDuration);
    }

    @Override
    public void run(){
        System.out.println("Job: "+jobName+" Priority: "+jobPriority + " Expected duration: "+ ((expectedDuration == null)? "N/A": expectedDuration));
        //do some work
        int ts;
        if(expectedDuration == null){
            ts = 1000;
        }
        else{
            ts = expectedDuration*100;
        }
        try {
            Thread.sleep(ts);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public project20280.priorityblockingqueue.JobPriority getJobPriority() {
        return jobPriority;
    }

    public Integer getExpectedDuration() {
        return expectedDuration;
    }
}