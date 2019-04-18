/*package org.sambasoft;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.finalyearproject.entities.Trip;
import org.finalyearproject.entities.User;
import org.finalyearproject.services.TripService;
import org.finalyearproject.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NhSystemApplicationTests {
	
      @Autowired
      private UserService userService;
      @Autowired
      private TripService tripService;
       
      @Before
      public void initDb() {
    	  {
    		  User newUser = new User("testUser@mail.com", "testUser", "123456");
    		  userService.createUser(newUser); 
    	  }
    	  {
    		  User newUser = new User("testAdmin@mail.com", "testAdmin", "123456");
    		  userService.createUser(newUser); 
    	  }
    	  
    	  Trip userTask = new Trip("03/01/2018", "00:11", "11:00", "You need to work today");
    	  User user = userService.findOne("testUser@mail.com");
    	  tripService.addTrip(userTask, user);  
      }
	 
      @Test
      public void testUser() {
    	  User user = userService.findOne("testUser@mail.com");
          assertNotNull(user);
    	  User admin = userService.findOne("testAdmin@mail.com");
    	  assertEquals(admin.getEmail(),"testAdmin@mail.com");
      }
      
      @Test
      public void testTask() {
    	  User user = userService.findOne("testUser@mail.com");
    	  List<Task> task = taskService.findUserTask(user);
    	  assertNotNull(task); 

      }
      
      
      
     
	
}*/
