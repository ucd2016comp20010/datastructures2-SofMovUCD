package project20280.priorityblockingqueue;

import javax.print.attribute.standard.JobPriority;

public class Job implements Runnable{
    private String jobName;
    private JobPriority jobPriority;

    @Override
    public void run(){
        System.out.println("Job: "+jobName+" Priority: "+jobPriority);
        //do some work
        Thread.sleep(1000);
    }

    public JobPriority getJobPriority() {
        return jobPriority;
    }
}