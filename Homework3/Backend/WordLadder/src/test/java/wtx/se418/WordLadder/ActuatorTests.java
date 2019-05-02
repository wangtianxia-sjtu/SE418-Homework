package wtx.se418.WordLadder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import junit.framework.Assert;
import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username="admin",roles={"ADMIN"})
public class ActuatorTests {

	@Autowired
    private MockMvc mockMvc;

    /*
	 * Test the actuator /actuator API
	 */

	@Test
	public void checkActuator() throws Exception {
        this.mockMvc.perform(get("/actuator")).andDo(print()).andExpect(status().isOk()).andReturn();
    }

    /*
	 * Test the actuator /actuator/health API
	 */

	@Test
	public void checkHealth() throws Exception {
        this.mockMvc.perform(get("/actuator/health")).andDo(print()).andExpect(status().isOk()).andReturn();
    }

    /*
	 * Test the actuator /actuator/info API
	 */

	@Test
	public void checkInfo() throws Exception {
        this.mockMvc.perform(get("/actuator/info")).andDo(print()).andExpect(status().isOk()).andReturn();
    }

    /*
	 * Test the actuator /actuator/env API
	 */

	@Test
	public void checkEnv() throws Exception {
        this.mockMvc.perform(get("/actuator/env")).andDo(print()).andExpect(status().isOk()).andReturn();
    }

	/*
	 * Test the actuator /actuator/health API
	 */

	@Test
	public void checkHealthData() throws Exception {
        MvcResult result;
        String response_string;
        result = this.mockMvc.perform(get("/actuator/health")).andDo(print()).andExpect(status().isOk()).andReturn();
        response_string = result.getResponse().getContentAsString();
        Assert.assertEquals("Status: UP", response_string, "{\"status\":\"UP\"}");
    }
}
