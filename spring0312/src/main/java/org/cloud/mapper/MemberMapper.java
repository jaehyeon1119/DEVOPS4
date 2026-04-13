package org.cloud.mapper;

import org.cloud.dto.MemberDto;

public interface MemberMapper {
	void insertMember(MemberDto member) throws Exception;
	MemberDto selectMemberById(String userId) throws Exception;
}
