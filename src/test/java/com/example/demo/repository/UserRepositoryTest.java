package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.User;

@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	SimpleDateFormat secondSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat miliSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	@RepeatedTest(1000)
	public void Javaで生成したDateとMySQLに登録したDateを比較する() throws ParseException {
		Date date = new Date();

		User user = new User();
		user.setId(1);
		user.setDate(date);
		userRepository.save(user);

		// コンソール出力用
		String millformatedJavaDate = miliSdf.format(date);
		String millformatedMySqlDate = miliSdf.format(userRepository.findById(1).get().getDate());
		System.out.println(millformatedJavaDate + "\t" + millformatedMySqlDate);
		
		// JUnit用
		String secondformatedJavaDate = secondSdf.format(date);
		String secondformatedMySqlDate = secondSdf.format(userRepository.findById(1).get().getDate());
		assertEquals(secondformatedJavaDate, secondformatedMySqlDate);
	}
}