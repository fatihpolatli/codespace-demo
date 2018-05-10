package com.mvc.login.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class PersistenceConfig {

	@Bean
	public DataSource dataSource() {

		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL).setName("test") // .H2 or
				.addScript("db/sql/create.sql").addScript("db/sql/insert.sql").continueOnError(true).build();
		return db;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

}
