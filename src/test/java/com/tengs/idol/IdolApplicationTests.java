package com.tengs.idol;


import com.tengs.idol.entity.User;
import com.tengs.idol.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdolApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void contextLoads() {
		User user = new User();
		user.setId(1111111);
		user.setUserName("sheteng");
		user.setOpenId("11111");
		user.setImageUrl("1212313");
		userMapper.insert(user);
	}

}
