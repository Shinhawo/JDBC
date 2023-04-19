package sample02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.ConnUtils;

// DB 엑세스를 전담하는 클래스
public class ScoreDao {

	/**
	 * 성적정보를 전달받아서 SAMPLE_SCORES 테이블에 추가한다.
	 * @param score 성적정보
	 * @throws SQLException 
	 */
	public void insertScore(Score score) throws SQLException {
		//쿼리 정의
		String sql = "inser into sample_scores "
				+ "(student_no, student_name, kor_score, eng_score, math_score )"
				+ "values "
				+"(sample_score_seq.nextval, ?,?,?.?)";
		
		// Connection 획득
		Connection con = ConnUtils.getConnection();
		
		// 이 쿼리를 표현하는 preparedStatement 획득
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, score.getSudentName());
		pstmt.setInt(2, score.getKor());
		pstmt.setInt(3, score.getEng());
		pstmt.setInt(4, score.getMath());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
	}
	
	/**
	 * SAMPLE_SCORES 테이블의 모든 성적정보를 반환한다. -> 조건 없음
	 * @return 모든 성적정보
	 * @throws SQLException 
	 */
	public List<Score> getAllScores() throws SQLException{
		
		String sql = "select student_no, student_name, kor_score, eng_score, math_score, create_date "
				+ "from sample_scores "
				+ "order by student_no asc ";
		
		List<Score> scoreList = new ArrayList<>();
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int no = rs.getInt("student_no");
			String name = rs.getString("student_name");
			int kor = rs.getInt("kor_score");
			int eng = rs.getInt("eng_score");
			int math = rs.getInt("math_score");
			Date createDate = rs.getDate("create_date");
			
			// 여기서 출력 안함! 왜냐면 출력은 표현 계층에서 한다. 
			// 여기는 영속화 계층 데이터를 제공, 저장하는 놈임
			Score score = new Score();
			score.setStudentNo(no);
			score.setSudentName(name);
			score.setKor(kor);
			score.setEng(eng);
			score.setMath(math);
			score.setCreateDate(createDate);
			
			scoreList.add(score);
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		return scoreList;
	}
	
	/**
	 * 전달받은 학생의 성적정보를 SAMPLE_SCORES 테이블에서 조회해서 반환한다.
	 * @param studentNo 학생번호
	 * @return 성적정보
	 */
	public Score getScoreByStudentNo(int studentNo) {
		
		
		return null;
	}
	
	/**
	 * 전달받은 학생의 성적정보를 SAMPLE_SCORES 테이블에서 삭제한다.
	 * @param studentNo 학생번호
	 */
	public void deleteScore(int studentNo) {
		
	}
	
	/**
	 * 수정된 정보가 포함된 성적정보를 전달받아서 SAMPLE_SCORES 테이블에 반영시킨다.
	 * @param score 성적정보
	 */
	public void updateScore(Score score) {
		
	}
	
}
