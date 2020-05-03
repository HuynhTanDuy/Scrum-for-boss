package spring.phase2.batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import spring.phase2.entity.User;
import spring.phase2.processor.UserItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	     
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public DataSource dataSource;
	
	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/phase2";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Secret1";
	private static final String SELECT_USER_QUERY = "SELECT id,name FROM user";
	private static final String STEP_1 = "step1";
	private static final String EXPORT_USER_JOB = "exportUserJob";
	
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER_CLASS_NAME);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
	    return dataSource;
	 }
	
	@Bean(destroyMethod="")
	public JdbcCursorItemReader<User> reader(){
		JdbcCursorItemReader<User> reader = new JdbcCursorItemReader<User>();
		reader.setDataSource(dataSource);
		reader.setSql(SELECT_USER_QUERY);
		reader.setRowMapper(new UserRowMapper());
		return reader;
	 }
	
	public class UserRowMapper implements RowMapper<User>{
		  @Override
		  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			  User user = new User();
			  user.setId(rs.getInt("id"));
			  user.setName(rs.getString("name"));
			  return user;
		  }
  	}
	
	@Bean
	public UserItemProcessor processor(){
	  return new UserItemProcessor();
	}
	
	@Bean
	public FlatFileItemWriter<User> writer(String output_file_name){
	   FlatFileItemWriter<User> writer = new FlatFileItemWriter<User>();
	   File newFile = new File(output_file_name);
	   try {
		   BufferedWriter file_writer = new BufferedWriter(new FileWriter(output_file_name));
	   } catch (IOException e) {
			e.printStackTrace();
	   }
	   writer.setResource(new FileSystemResource(output_file_name));
	   writer.setLineAggregator(new DelimitedLineAggregator<User>() {{
		   setDelimiter(",");
		   setFieldExtractor(new BeanWrapperFieldExtractor<User>() {{
			   setNames(new String[] { "id", "name" });
		   }});
	   }});
	   return writer;
	}
	
	@Bean
	public Step step1(String output_file_name) {
	   return stepBuilderFactory.get(STEP_1).<User, User> chunk(10)
	    .reader(reader())
	    .processor(processor())
	    .writer(writer(output_file_name))
	    .build();
	}
	 
	@Bean
	public Job exportUserJob() {
		Date date = new Date();
		Integer minute = date.getMinutes();
	    String output_file_name ="users_" + String.valueOf(minute) + ".csv"; 	
	    return jobBuilderFactory.get(EXPORT_USER_JOB)
		    .incrementer(new RunIdIncrementer())
		    .flow(step1(output_file_name))
		    .end()
		    .build();
	 }	
}
