package hr.tvz.rentabike.jobs;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

	@Bean
	public JobDetail startReservationJobDetail() {
		return JobBuilder.newJob(StartReservationJob.class).withIdentity("startReservationJob")
				.storeDurably().build();
	}

	@Bean
	public Trigger startReservationJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(10).repeatForever();

		return TriggerBuilder.newTrigger().forJob(startReservationJobDetail())
				.withIdentity("startReservationTrigger").withSchedule(scheduleBuilder).build();
	}
	
	@Bean
	public JobDetail endReservationJobDetail() {
		return JobBuilder.newJob(EndReservationJob.class).withIdentity("endReservationJob")
				.storeDurably().build();
	}

	@Bean
	public Trigger endReservationJobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(10).repeatForever();

		return TriggerBuilder.newTrigger().forJob(endReservationJobDetail())
				.withIdentity("endReservationTrigger").withSchedule(scheduleBuilder).build();
	}
}