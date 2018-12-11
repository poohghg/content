package org.zerock.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.member.dto.Criteria;
import org.zerock.member.dto.LoginDTO;

@Repository
public class MemberDao {

	@Inject
	private SqlSession sqlSession;
	
	private String namespace = "org.zerock.mapper.MemberMapper.";
	
	//로그인을 시도하면 해쉬맵에 아이디와 비밀번호를 담아서 session으로 보내준다.
	public LoginDTO login(String email) {
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		setLoginTrue(email); //로그인 상태에 1을 넣어주는 쿼리 함수를 실행한다.
		return sqlSession.selectOne(namespace+"login", map);
	}
	
	//로그아웃 시도시에 로그인 상태를 0으로 만들어서 로그아웃을 표시해주는 함수
	public void logout(String email) {
		setLoginFalse(email); //로그인 상태에 0을 넣어주는 쿼리함수를 실행한다.
	}
	
	//로그인 상태를 얻어와 반환해주는 함수, 상태는 1아니면 0
	public int getLogin(String email) {
		return sqlSession.selectOne(namespace + "getLogin", email);
	}
	
	//현재 로그인 상태를 1로 만들어줘서 로그인 됨을 알려주는 함수 
	public void setLoginTrue(String email) {
		System.out.println("DAO 이메일 : " + email);
		sqlSession.update(namespace + "setLoginTrue", email);
	}
	
	//현재 로그인 상태를 0으로 만들어 로그아웃 되었음을 나타내주는 함수
	public void setLoginFalse(String email) {
		sqlSession.update(namespace + "setLoginFalse", email);
	}

	//dto를 DB에 보내서 회원가입을 한다.
	public void join(LoginDTO dto) {
		sqlSession.insert(namespace+"join", dto);
	}
	
	// 이메일 중복확인할때 DB에 존재하는 이메일인지 검사하는 함수. 메일이 있으면 메일을 반환한다.
	public String checkEmail(String email) {
		return sqlSession.selectOne(namespace + "checkEmail", email);
	}
	
	//유저의 인증키를 업데이트한다. 이메일이 일치하는 유저만 키를 업데이트
	public void createAuthKey(String user_email, String user_authCode) throws Exception {
		LoginDTO dto = new LoginDTO();
		dto.setUser_authCode(user_authCode);
		dto.setEmail(user_email);
		sqlSession.selectOne(namespace + "createAuthKey", dto);
	}
	
	
	//유저의 이메일에 일치하는 DB 레코드를 조회하여 user_authStatus를 1로 업데이트 한다.
	public void userAuth(String user_email) throws Exception {
		sqlSession.update(namespace + "userAuth", user_email);
	}
	
	//유저의 인증상태가 1인지 0인지 조사하기 위해 DB에서 user_authStatus를 조회한다.
	public String selectUserAuth(String user_email) throws Exception {
		return sqlSession.selectOne(namespace + "selectUserAuth", user_email);
	}

	//암호화된 비밀번호를 가져오는 메서드. 로그인할 때 쓰인다.
	public String selectCryptPw(String email) {
		return sqlSession.selectOne(namespace + "selectCryptPw", email);
	}

	//아이디 찾기에 사용된 DAO 메서드
	public String searchId(String newHp, String name, String age) {
		Map<String, String> map = new HashMap<>();
		map.put("newHp", newHp);
		map.put("name", name);
		map.put("age", age);
		return sqlSession.selectOne(namespace + "searchId", map);	
	}

	//비밀번호 찾기시 새로운 비밀번호가 암호화되어서 넘어오는데 DB에 저장해주는 메서드
	public void setPw(String pw, String email) {
		Map<String, String> map = new HashMap<>();
		map.put("pw", pw);
		map.put("email", email);
		sqlSession.insert(namespace + "setPw", map);
	}
	
	//리스트를 호출하는 메서드
	public List<LoginDTO> list(Criteria cri) {
		return sqlSession.selectList(namespace + "list", cri);
	}

	//view에서 보여줄 dto를 
	public LoginDTO view(String email) {
		return sqlSession.selectOne(namespace + "view", email);
	}

	//dto를 이용해 DB에 업데이트를 실시한다.
	public void update(LoginDTO dto) {
		sqlSession.update(namespace + "update", dto);
	}

	//회원탈퇴메서드 DB로 email을 보낸다.
	public void secession(String email) {
		sqlSession.update(namespace + "secession", email);
	}

	//회원정보 수정 메서드
	public void modify(LoginDTO dto) {
		System.out.println("DAO 모디파이");
		System.out.println(dto);
		sqlSession.update(namespace + "modify", dto);
	}
}
