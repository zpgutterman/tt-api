package post;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginRequest {
	@XmlElement public String email;
	@XmlElement public String password;
}
