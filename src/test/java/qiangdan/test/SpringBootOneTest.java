package qiangdan.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gxuwz.fx.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)// 指定spring-boot的启动类 
public class SpringBootOneTest {
	
	 @Test
	 public void bijiao() {
		 Date d1 = new Date(System.currentTimeMillis()-1000);
		 System.out.println(d1);
		 Date d2 = new Date(System.currentTimeMillis());
		 System.out.println(d2);
		 System.out.println(d1.compareTo(d2));
	 }
	
}
