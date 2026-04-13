package org.cloud.service;

import org.cloud.dto.MemberDto;

public interface MemberService {

	void insertMember(MemberDto member) throws Exception;
}
