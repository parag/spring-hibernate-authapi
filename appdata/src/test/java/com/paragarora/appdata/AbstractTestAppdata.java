package com.paragarora.appdata;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = { "classpath:appdata-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
public class AbstractTestAppdata {
	
}
