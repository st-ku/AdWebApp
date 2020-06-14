package com.company.controller;

import com.company.entity.User;
import com.company.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@PrepareForTest(AdminController.class)
@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    @InjectMocks
    AdminController adminController ;
    @Before
    public void init() {
       mockMvc= MockMvcBuilders.standaloneSetup(adminController).build();
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testList() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        when(userService.allUsers()).thenReturn((List) userList);
        mockMvc.perform((get("/admin/")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("allUsers", hasSize(userService.allUsers().size())));

    }



}