package project20280.priorityblockingqueue;

import project20280.interfaces.Position;

import javax.print.attribute.standard.JobPriority;
import java.util.Comparator;
import java.util.concurrent.*;

public class PriorityJobScheduler {
    private ExecutorService priorityJobPoolExecutor;
    private ExecutorService priorityJobScheduler = Executors.newSingleThreadExecutor();
    private PriorityBlockingQueue<Job> priorityQueue;

    public PriorityJobScheduler(Integer poolSize, Integer queueSize){
        priorityJobPoolExecutor = Executors.newFixedThreadPool(poolSize);
        priorityQueue = new PriorityBlockingQueue<Job>(queueSize, Comparator.comparing(Job :: getJobPriority));
        priorityJobScheduler.execute( () -> {
            while(true){
                try{
                    priorityJobPoolExecutor.execute(priorityQueue.take());
                }
                catch(InterruptedException e){
                    break;
                }
            }
        });
    }

    public void scheduleJob(Job job){
        priorityQueue.add(job);
    }

}
