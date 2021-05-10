package com.streammovie.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.streammovie.dto.MemberDTO;
import com.streammovie.mybatis.SqlMapConfig;

public class MemberDAO {
	
	private MemberDAO() {  }

	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
	
	public int selectIdDupeCheck(String id) {
		
		int result = 0;
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			result = sqlSession.selectOne("idDupeCheck", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int selectLoginCheck(String id, String pwd) {
		
		MemberDTO member = null;
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			member = sqlSession.selectOne("selectMember", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		int result = 0;

		if (member != null && member.getUseyn().equals("y")) {
			result = member.getPwd().equals(pwd) ? 2 : 1; 
		}

		return result;
	}
	
	public MemberDTO selectMember(String id) {
		
		MemberDTO member = null;
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			member = sqlSession.selectOne("selectMember", id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;
	}
	
	public int insertRegisterMember(MemberDTO member) {
		
		int result = 0;
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			result = sqlSession.insert("registerMember", member);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void updateLoginDate(String id) {
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			sqlSession.update("updateLoginDate", id);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MemberDTO modifyMember(MemberDTO member) {
		
		MemberDTO modifiedMember = null;
	
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
		
			int result = sqlSession.update("modifyMember", member);
			if (result > 0) {
				sqlSession.commit();
				modifiedMember = sqlSession.selectOne("selectMember", member.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return modifiedMember;
	}

	public int updatePassword(String id, String newPassword) {
		
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("newPassword", newPassword);
		
		int result = 0;
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			result = sqlSession.update("updatePassword", paramMap);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateLeaveMember(String id) {
		
		int result = 0;
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			result = sqlSession.update("leaveMember", id);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
