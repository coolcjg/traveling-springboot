package com.cjg.traveling.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjg.traveling.common.Encrypt;
import com.cjg.traveling.common.Jwt;
import com.cjg.traveling.domain.User;
import com.cjg.traveling.dto.UserDto;
import com.cjg.traveling.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Jwt jwt;
	
	@Autowired
	private Encrypt encrypt;
	
	public Map<String,Object> existsById(String userId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		boolean exists = userRepository.existsByUserId(userId);
		
		map.put("code", "200");
		
		if(exists == true) {
			map.put("count", 1);
		}else {
			map.put("count", 0);
		}
		
		return map;
	}
	
	public User findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}
	
	
	
	
	
	// 회원 등록
	public Map<String, Object> insertUser(UserDto userDTO) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		User user = new User();
		String salt = encrypt.getSalt();
		
		user.setUserId(userDTO.getUserId());
		user.setSalt(salt);
		user.setPassword(encrypt.getEncrypt(userDTO.getPassword(), salt));
		user.setName(userDTO.getName());
		user.setBirthDay(userDTO.getBirthDay());
		
		User resultUser = userRepository.save(user);
		
		if(resultUser.getUserId() == user.getUserId()) {
			map.put("code", "200");
		}else {
			map.put("code", "E-USER-001");
		}
		
		return map; 
	}
	
	// 로그인
	public Map<String, Object> login(UserDto userDTO, HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		User user = userRepository.findByUserId(userDTO.getUserId());
		
		if(user == null) {
			map.put("code", "E-USER-002");
		}else {
			
			if(user.getPassword().equals(encrypt.getEncrypt(userDTO.getPassword(), user.getSalt()))) {
				
				String accessToken = jwt.createAccessToken(user);
				String refreshToken = jwt.createRefreshToken(user);
				
				user.setRefreshToken(refreshToken);
				userRepository.save(user);
								
				map.put("code", "200");
				map.put("accessToken", accessToken);
				map.put("refreshToken", refreshToken);
				map.put("id", user.getUserId());
				map.put("name", user.getName());
			}else {
				map.put("code", "E-USER-003");
			}
		}
		
		return map;
	}
	
}
