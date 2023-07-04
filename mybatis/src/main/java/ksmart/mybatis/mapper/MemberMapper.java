package ksmart.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.mybatis.dto.Member;
import ksmart.mybatis.dto.MemberLevel;

@Mapper
public interface MemberMapper {
	// 회원 수정
	public int modifyMember(Member member);
	
	// 회원별 상세조회
	public Member getMemberInfoById(String memberId);
	
	// 회원가입
	public int addMember(Member member);
	
	// 회원중복아이디 체크
	public boolean idCheck(String memberId);

	// 회원등급 조회
	public List<MemberLevel> getMemberLevelList();
	
	
	// 회원목록 조회
	public List<Member> getMemberList();
}
