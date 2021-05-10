package com.streammovie.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.streammovie.dto.BoardDTO;
import com.streammovie.mybatis.SqlMapConfig;

public class BoardDAO {
	
	private BoardDAO() {  }

	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	
	private SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
	
	public int createNewArticle(BoardDTO article) {
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

			article.setRef(0);
			article.setRestep(1);
			article.setRelevel(0);
			sqlSession.insert("insertBoard", article);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article.getArticleno();
	}
	
	public int updateNewArticleContent(int articleno, String content) {
		
		int result = 0;
		Map<String, Object> paramMap = new HashMap<>();
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

			paramMap.put("ref", articleno);
			paramMap.put("content", content);
			paramMap.put("articleno", articleno);
			result = sqlSession.update("updateContent", paramMap);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<BoardDTO> selectBoardList(Map<String, Object> boardMap) {
		
		List<BoardDTO> boardList = null;
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

			boardList = sqlSession.selectList("selectBoardList", boardMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boardList;
	}

	public BoardDTO selectBoard(int articleno) {
		
		BoardDTO article = null;
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

			article = sqlSession.selectOne("selectBoard", articleno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return article;
	}

	public int updateReadcount(int articleno) {

		int result = 0;

		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			
			result = sqlSession.update("updateReadcount", articleno);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteArticle(int articleno) {
		
		int result = 0;
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

			result = sqlSession.delete("deleteArticle", articleno);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateArticle(BoardDTO newArticle) {
		
		int result = 0;
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

			result = sqlSession.update("updateArticle", newArticle);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateNewReplyContent(Map<String, Integer> parentParamMap, int articleno, String content) {
		
		int result = 0;
		Map<String, Object> paramMap = new HashMap<>();
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

			paramMap.put("ref", parentParamMap.get("ref"));
			paramMap.put("content", content);
			paramMap.put("articleno", articleno);
			
			int replyOrder = sqlSession.selectOne("selectBoardReplyOrder", parentParamMap);

			if (replyOrder == 0) {
				int maxReStep = sqlSession.selectOne("selectMaxReStep", parentParamMap.get("ref"));
				
				paramMap.put("restep", maxReStep);
				paramMap.put("relevel", parentParamMap.get("relevel") + 1);
				
				result = sqlSession.update("updateReply", paramMap);
				sqlSession.commit();
				
		    } else {
		    	int maxReStep = sqlSession.selectOne("selectMaxReStep2", parentParamMap);
		    	paramMap.put("restep", maxReStep);

		    	sqlSession.update("updateReplyReStep", parentParamMap);
				paramMap.put("relevel", parentParamMap.get("relevel") + 1);
				
				result = sqlSession.update("updateReply", paramMap);
				sqlSession.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int selectAllBoardCount(Map<String, Object> boardCountMap) {
		
		int boardCount = 0;
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

			boardCount = sqlSession.selectOne("selectAllBoardCount", boardCountMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boardCount;
	}
}
