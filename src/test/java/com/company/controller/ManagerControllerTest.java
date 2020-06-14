package com.company.controller;

import com.company.entity.Ad;
import com.company.service.AdService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(MockitoJUnitRunner.class)
public class ManagerControllerTest {
    private MockMvc mockMvc;
    @Mock
    private AdService adService;
    @InjectMocks
    ManagerController managerController ;
    @Before
    public void init() {
        mockMvc= MockMvcBuilders.standaloneSetup(managerController).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
   public void editAd() throws Exception {
       Long id = 1L;
        when(adService.getAdById(id)).thenReturn(new Ad());
        mockMvc.perform(get("/edit"+"?id="+id))
                .andExpect(status().isOk())
                //.andExpect(view().name("manageAds"))
                .andExpect(model().attribute("ad", instanceOf(Ad.class)));
    }
}