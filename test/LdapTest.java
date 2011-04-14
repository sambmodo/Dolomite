import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;



public class LdapTest extends UnitTest {

 @Before
  public void setUp(){	
    new LdapUser("flora.dupont@utt.fr", "test", "Flora", "Dupont", "flora.dupont").addUser();
  }
  
  public void create() 
   {
	int i = new LdapUser("firstname.lastname@utt.fr", "password", "Firstname", "Lastname", "firstname.lastname").addUser();
   assertEquals(0, i);      	
   }
    public void connect() {
	LdapUser flo = LdapUser.connect("flora.dupont", "test");
	assertNotNull(flo);
	}
	
	@Test	
  public void connect_no_user() {	
    LdapUser user = LdapUser.connect("user", "password");
    assertNull(user);  	
  }
 		
  @Test	
  public void connect_O_password() {	
     LdapUser flora = LdapUser.connect("flora.dupont", "wrong_password");	
     assertNull(flora);  	
  }
	
  @Test	
    public void getUsrInformation() 	
  {		
    LdapUser flor = LdapUser.connect("flora.dupont", "test");  	
    assertEquals("Flora", flor.getFirstname());	
    assertEquals("Dupont", flor.getLastname());
    assertEquals("flora.dupont@utt.fr", flor.getEmail()); 	
    }
	public void updateUsrInformation(){	
     LdapUser flora = LdapUser.connect("flora.dupont", "test");
	 flora.updateUser("flora.dupont@utt.fr", "new_password", "arolf", "tnopud");
	 LdapUser floModified = LdapUser.connect("flora.dupont", "new_password");	
     LdapUser floWithOldPwd = LdapUser.connect("flora.dupont", "test");
	 assertNull(floWithOldPwd);
	 assertNotNull(floModified);
     assertEquals("flora.dupont@utt.fr", floModified.getEmail());
	 assertEquals("arolf", floModified.getFirstname());	
     assertEquals("tnopud", floModified.getLastname());  
   }
    public void deleteUser() 
 	
  {
    new LdapUser("firstname.lastname@utt.fr", "password", "Firstname", "Lastname", "firstname.lastname").addUser();
    LdapUser user = LdapUser.connect("firstname.lastname", "password"); 		
    user.deleteUser();
    LdapUser userDeleted = LdapUser.connect("firstname.lastname", "password");	
    assertNotNull(user); 
    assertNull(userDeleted); 	
    }
	
 // @After

 
 		
 	
 		

 }
