package application;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import common.QuartzJob;

@SpringBootApplication
@EntityScan(basePackages = {"model"})
public class BackendApplication {

	public static void main(String[] args) throws SchedulerException {
		SpringApplication.run(BackendApplication.class, args);
		
		JobDetail job = JobBuilder.newJob(QuartzJob.class).build();
		
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("myTrigger")
				.withSchedule(CronScheduleBuilder
						.cronSchedule("0 0 0 1/1 * ? *"))
				.build();           

		Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
		sc.start();
		sc.scheduleJob(job, trigger);
	}
}
