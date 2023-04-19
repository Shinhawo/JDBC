package sample01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.ConnUtils;

public class App3 {

	public static void main(String[] args) throws Exception{
		
		// Jobs 테이블의 모든 직종 정보를 조회하기
		String sql = "select JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY "
				+ "from JOBS "
				+ "order by job_id asc ";
		
		Connection conn = ConnUtils.getConnection();
		PreparedStatement pspmt = conn.prepareStatement(sql);
		
		ResultSet rs = pspmt.executeQuery();
		
		System.out.println("=================직종정보=================");
		System.out.println();
		while(rs.next()) {
			
			String Id = rs.getString("JOB_ID");
			String Title = rs.getString("JOB_TITLE");
			int MinSalary = rs.getInt("MIN_SALARY");
			int MaxSalary = rs.getInt("MAX_SALARY");
			
			System.out.println("직무 : "+Id);
			System.out.println("직위 : "+ Title);
			System.out.println("최소급여 : "+MinSalary);
			System.out.println("최대급여 : "+MaxSalary);
			System.out.println();
			System.out.println("---------------------------------------");
			System.out.println();
		}
		
		rs.close();
		pspmt.close();
		conn.close();
	}
}
