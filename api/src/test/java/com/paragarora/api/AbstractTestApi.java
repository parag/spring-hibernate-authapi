package com.paragarora.api;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = { "classpath:api-context.xml", "classpath:appdata-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
public class AbstractTestApi {
	
}
