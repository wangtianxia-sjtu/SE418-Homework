package wtx.se418.WordLadder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WordLadderApplicationTests {

	@Autowired
    private MockMvc mockMvc;

	/*
	 * Test the REST API without assigning any parameters 
	 * This should return an JSON array from "code" to "data"
	 */

	@Test
	public void noParamShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/search")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("5"));
    }
	
	/*
	 * Test the REST API while assigning "good" and "last" as parameters
	 */

	@Test
	public void ParamShouldReturnCorrectMessage1() throws Exception {

        this.mockMvc.perform(get("/search?start=good&end=last")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("good"))
				.andExpect(jsonPath("$[1]").value("food"))
				.andExpect(jsonPath("$[2]").value("foot"));
    }

	/*
	 * Test the REST API while assigning "strong" and "beauty" as parameters
	 * The ladder does not exist
	 */

	@Test
	public void ParamShouldReturnCorrectMessage2() throws Exception {

        this.mockMvc.perform(get("/search?start=strong&end=beauty")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("0"));
    }
}
