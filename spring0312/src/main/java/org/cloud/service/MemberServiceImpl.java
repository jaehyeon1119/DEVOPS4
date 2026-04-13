package org.cloud.service;

import org.cloud.dto.MemberDto;
import org.cloud.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService, UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void insertMember(MemberDto member) throws Exception {
		// TODO Auto-generated method stub
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberMapper.insertMember(member);
	}
	

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		try {
			MemberDto member = memberMapper.selectMemberById(userId);
			if (member == null) {
				throw new UsernameNotFoundException("아이디 없음");
			}
			
			return User.builder()
					.username(member.getUserId())
					.password(member.getPassword())
					.roles("USER")
					.build();
		} catch (Exception e) {
			// TODO: handle exception
			throw new UsernameNotFoundException("DB 오류");
		}
	}
}





