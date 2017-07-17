package com.fortumo.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fortumo.AbstractTest;
import com.fortumo.model.Subscription;
import com.fortumo.service.SubscriptionService;

public class SubscriptionServiceTest extends AbstractTest{
	
	@Autowired
	SubscriptionService subscriptionService;
	
	@Test
	public void testGetAllSubscriptions(){
		List<Subscription> subs = subscriptionService.getAllSubscriptions();
		
		Assert.assertNotNull(subs);
		Assert.assertEquals(1, subs.size());
	}
	
	@Test
	public void testFindByName(){
		Subscription sub = subscriptionService.findByName("Premium account for €2.99/month");
		
		Assert.assertNotNull(sub);
		Assert.assertEquals("Premium account for €2.99/month", sub.getName() );
	}

}
