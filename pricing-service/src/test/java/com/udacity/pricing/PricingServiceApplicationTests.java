package com.udacity.pricing;

import com.udacity.pricing.api.PricingController;
import com.udacity.pricing.service.PricingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(PricingController.class)
public class PricingServiceApplicationTests {


	@Autowired
	private MockMvc mockMvc; // to fake an http request

	@MockBean
	PricingService pricingService; // to inject fake data**/


	@Test
	public void contextLoads() {
	}


	// test ("/services/price?vehicleId={id}") url
	@Test
	public void getPricedById() throws Exception{
		mockMvc.perform(get("/services/price?vehicleId=1"))
				.andExpect(status().isOk());

	//	verify(pricingService, times(1)).getPrice((long)1);
	}

	/**
	// test ("/{id}/breed") url
	@Test
	public void gestBreedById() throws Exception{
		mockMvc.perform(get("/1/breed")
				.andExpect(status().isOk());

		verify(dogService, times(1)).retrieveDogBreedById((long)1);
	}**/

}
