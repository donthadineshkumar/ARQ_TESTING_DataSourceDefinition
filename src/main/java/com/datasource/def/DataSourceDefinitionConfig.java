package com.datasource.def;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;



@DataSourceDefinition(
		name="java:app/MyDatasource",
		className="org.h2.jdbcx.JdbcDataSource",
		url="jdbc:h2:mem:test")
@Stateless
public class DataSourceDefinitionConfig {

}
