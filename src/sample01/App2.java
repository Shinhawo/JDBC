package sample01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.ConnUtils;

public class App2 {

	public static void main(String[] args) throws Exception {
		
		// ""끝에 한칸 띄우기 -> 안띄우면 에러뜸 : ORA-00933: SQL 명령어가 올바르게 종료되지 않았습니다.
		String sql = "select student_no, student_name, kor_score, eng_score, math_score "
				+ "from sample_scores "
				+ "order by student_no asc ";
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		// select구문에 대해 사용
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println("====학생정보====");
		while(rs.next()) {
			// next()는 이동할 칸에 데이터가 존재 해야만 true를 반환함.
			int no = rs.getInt("student_no");
			String name = rs.getString("student_name");
			int kor = rs.getInt("kor_score");
			int eng = rs.getInt("eng_score");
			int math = rs.getInt("math_score");
			
			System.out.println("번호 : "+no);
			System.out.println("이름 : "+name);
			System.out.println("국어 : "+kor);
			System.out.println("영어 : "+eng);
			System.out.println("수학 : "+math);
			System.out.println("-------------");
		}
	}
}
