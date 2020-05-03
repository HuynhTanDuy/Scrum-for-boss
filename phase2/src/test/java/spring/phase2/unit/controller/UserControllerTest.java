//package spring.phase2.unit.controller;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//
//import org.junit.Test;
//import org.junit.Before;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import spring.phase2.entity.User;
//
//
//public class UserControllerTest extends AbstractTest {
//	@Override
//	@Before
//	public void setUp() {
//	    super.setUp();
//	}
//	
//	@Test
//	public void getAllTest() throws Exception {
//	   String uri = "/user";
//	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//	   
//	   int status = mvcResult.getResponse().getStatus();
//	   assertEquals(200, status);
////	   String content = mvcResult.getResponse().getContentAsString();
////	   User[] users = super.mapFromJson(content, User[].class);
////	   assertTrue(users.length > 0);
//	   
//	   //restTemplate 
//	}
//	
//    @Test
//    public void createTest() throws Exception {
//       String uri = "/user";
//       User user = new User();
//       user.setName("Ted");
//       user.setEmail("ted@gmail.com");
//       user.setPassword("1");
//       user.setOffice_id(1);
//       user.setRole_id(1);
//       String inputJson = super.mapToJson(user);
//       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//          .contentType(MediaType.APPLICATION_JSON_VALUE)
//          .content(inputJson)).andReturn();
//      
//       int status = mvcResult.getResponse().getStatus();
//       assertEquals(201, status);
////       String content = mvcResult.getResponse().getContentAsString();
////       assertFalse(content.isEmpty());
//    }
//    @Test
//    public void updateTest() throws Exception {
//       String uri = "/user/4";
//       User user = new User();
//       user.setName("lenon");
//       user.setEmail("lenon@gmail.com");
//       user.setPassword("1");
//       user.setOffice_id(2);
//       user.setRole_id(2);
//       String inputJson = super.mapToJson(user);
//       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
//          .contentType(MediaType.APPLICATION_JSON_VALUE)
//          .content(inputJson)).andReturn();
//       
//       int status = mvcResult.getResponse().getStatus();
//       assertEquals(200, status);
////       String content = mvcResult.getResponse().getContentAsString();
////       assertFalse(content.isEmpty());
//    }
//    
//    @Test
//    public void deleteTest() throws Exception {
//       String uri = "/user/4";
//       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
//       int status = mvcResult.getResponse().getStatus();
//       assertEquals(200, status);
////       String content = mvcResult.getResponse().getContentAsString();
////       assertFalse(content.isEmpty());
//    }    
//}
