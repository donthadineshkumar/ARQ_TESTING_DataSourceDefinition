package com.test.datasource;

import static org.junit.Assert.assertThat;

import java.io.File;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.datasource.def.DataSourceDefinitionConfig;


@RunWith(Arquillian.class)
public class DataSourceDefinitionTest {
	
	@Deployment
	public static Archive<?> deploy() {
		
		File h2Library = Maven.resolver().loadPomFromFile("pom.xml")
				.resolve("com.h2database:h2").withoutTransitivity()
				.asSingleFile();
		
		
		return ShrinkWrap.create(WebArchive.class)
				.addClasses(DataSourceDefinitionConfig.class)
				.addAsLibraries(h2Library);
	}
	
	
	@Resource(lookup="java:app/MyDatasource") DataSource datasource;
	
	
	@Test
	public void checkDatasourceCreated() throws Exception {
		
		assertThat(datasource , is(notNullValue()));
		assertThat(datasource.getConnection()	, is(notNullValue()));
 }

}
