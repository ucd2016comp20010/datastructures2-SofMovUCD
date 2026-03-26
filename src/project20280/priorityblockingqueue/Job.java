package project20280.priorityblockingqueue;

import javax.print.attribute.standard.JobPriority;

public class Job implements Runnable{
    private String jobName;
    private project20280.priorityblockingqueue.JobPriority jobPriority;

    public Job(String job1, project20280.priorityblockingqueue.JobPriority jobPriority) {
        this.jobName = job1;
        this.jobPriority = jobPriority;
    }

    @Override
    public void run(){
        System.out.println("Job: "+jobName+" Priority: "+jobPriority);
        //do some work
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public project20280.priorityblockingqueue.JobPriority getJobPriority() {
        return jobPriority;
    }
}