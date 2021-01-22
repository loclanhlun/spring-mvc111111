package com.huynhbaoloc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.huynhbaoloc.constant.SystemConstant;
import com.huynhbaoloc.dto.MyUser;
import com.huynhbaoloc.entity.RoleEntity;
import com.huynhbaoloc.entity.UserEntity;
import com.huynhbaoloc.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
		if(userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(RoleEntity roles : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(roles.getCode()));
		}
		//put thong tin vao security duy tri he thong do khi user login vao he thong
		MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(), true, true, true, true, authorities);
		
		myUser.setFullName(userEntity.getFullName());
		return myUser ;
	}

}
