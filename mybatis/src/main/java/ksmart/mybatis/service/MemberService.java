package ksmart.mybatis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import ksmart.mybatis.dto.Member;
import ksmart.mybatis.dto.MemberLevel;
import ksmart.mybatis.mapper.MemberMapper;

/**
 * @Transactional: 트랜잭션 처리 A(원자성)C(일관성)I(고립성)D(영속성)
 * SQLException dao 관련된 작업 rollback
 * 정상적으로 수행이 되었다면? commit
 * @author ASUS
 *
 */
@Service
@Transactional
public class MemberService {
	
	private static final Logger log = LoggerFactory.getLogger(MemberService.class);
	
	private final MemberMapper memberMapper;

	// 생성자 메소드 의존성 주입방식
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	@PostConstruct
	public void memberServiceInit() {
		System.out.println("memberService 객체 생성");
	}
	
	/**
	 * 회원여부 검증
	 * @param memberId, memberPw
	 * @return Map<String,Object>  검증성공 : (검증여부, 검증이 완료된 회원객체), 검증실패(검증여부) 
	 */
	public Map<String, Object> isValidMember(String memberId, String memberPw) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		boolean isValid = false;
		
		// 회원 검증 
		Member member = memberMapper.getMemberInfoById(memberId);
		if(member != null) {
			String checkPw = member.getMemberPw();
			if(checkPw.equals(memberPw)) {
				isValid = true;
				resultMap.put("memberInfo", member);
			}
		}
		
		resultMap.put("isValid", isValid);
		
		return resultMap;
	}
	
	/**
	 *  회원 정보 수정
	 * @param Member
	 * @return excuteUpdate() 메소드 리턴 값: (수정처리 완료:1, 미완료:0)
	 */
	public int modifyMember(Member member) {
		int result = memberMapper.modifyMember(member);
		return result;
	}
	
	/**
	 * 회원별 상세조회
	 * @param memberId(회원아이디)
	 * @return Member (회원정보)
	 */
	public Member getMemberInfoById(String memberId) {
		Member memberInfo = memberMapper.getMemberInfoById(memberId);
		return memberInfo;
	}
	
	/**
	 * 회원가입
	 * @param member
	 */
	public void addMember(Member member) {
		memberMapper.addMember(member);
	}
	
	/**
	 * 회원등급 조회
	 * @return
	 */
	public List<MemberLevel> getMemberLevelList(){
		
		List<MemberLevel> memberLevelList = memberMapper.getMemberLevelList();
			
		return memberLevelList;
	}
	
	/**
	 * 회원 목록 조회
	 * @return
	 */
	public List<Member> getMemberList(){
		List<Member> memberList = memberMapper.getMemberList();
		String[] memberLevelArr = {"관리자", "판매자", "구매자", "테스트"};
		log.info("memberList : {}", memberList);
		if(memberList != null) {			
			memberList.forEach(member -> {
				int memberLevel = member.getMemberLevel();
				String memberLevelName = memberLevelArr[memberLevel-1];
				member.setMemberLevelName(memberLevelName);
			});
		}
		return memberList;
	}
}











