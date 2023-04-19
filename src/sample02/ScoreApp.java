package sample02;


import java.sql.SQLException;
import java.util.List;

import util.KeyboardReader;

public class ScoreApp {

	public static void main(String[] args) throws Exception{
		
		new ScoreApp().menu();
		
	}
	
	KeyboardReader reader = new KeyboardReader();
	ScoreDao dao = new ScoreDao(); 

	public void menu() {
		
		System.out.println("### 성적관리 프로그램 ###");
		
		while(true) {
			System.out.println("---------------------------------------------------------");
			System.out.println("1.전체조회 2.조회 3.저장 0.종료");
			System.out.println("---------------------------------------------------------");
			System.out.println();
		
			System.out.println("### 메뉴 선택 : ");
			int menuNo = reader.readInt();
			System.out.println();
			
			try {
				if(menuNo == 1) {
					전체조회();
				}else if(menuNo == 2) {
					조회();
				}else if(menuNo == 3) {
					저장();
				}else if(menuNo == 0) {
					종료();
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			System.out.println();
			System.out.println();
			System.out.println();
			menu();
		}
		
	}
	
	public void 전체조회() throws SQLException {
		System.out.println("<< 전체조회 >>");
		System.out.println("### 전체 성적정보를 확인하세요.");
		
		List<Score> scores = dao.getAllScores();
		
		System.out.println("--------------------------------------------------------------");
		System.out.println("학번\t이름\t국어\t영어\t수학\t총점\t평균");
		System.out.println("--------------------------------------------------------------");

		for(Score score : scores) {
			
			int totalScore = score.getKor()+score.getEng()+score.getMath();
			int average = totalScore/3;
			
			System.out.print(score.getStudentNo() + "\t");
			System.out.print(score.getSudentName() + "\t");
			System.out.print(score.getKor() + "\t");
			System.out.print(score.getEng() + "\t");
			System.out.print(score.getMath() + "\t");
			System.out.print(totalScore + "\t");
			System.out.println(average);
			
		}
		System.out.println("--------------------------------------------------------------");
		System.out.println();
	}
	
    public void 조회() throws SQLException {
    	System.out.println("<< 조회 >>");
		System.out.println("### 학번을 입력해서 해당 학생의 성적을 확인하세요.");
		System.out.println("### 학번 : ");
		int no = reader.readInt();
		
		Score score = dao.getScoreByStudentNo(no);
		if(score == null) {
			System.out.println("### 성적정보가 존재하지 않습니다.");
		} else {
			
			System.out.println("-------------------------------------------");
			System.out.println("["+score.getSudentName()+"]의 성적정보");
			System.out.println("-------------------------------------------");
			System.out.println("학번 : "+score.getStudentNo());
			System.out.println("이름 : "+score.getSudentName());
			System.out.println("국어 : "+score.getKor());
			System.out.println("영어 : "+score.getEng());
			System.out.println("수학 : "+score.getMath());
			System.out.println("총점 : "+score.getTotalScore());
			System.out.println("평균 : "+score.getAverage());
			System.out.println("-------------------------------------------");
		}
	}
    
    public void 저장() throws SQLException {
    	System.out.println("<< 저장 >>");
    	System.out.println("### 이름, 국어/영어/수학 점수를 입력해서 성적정보를 저장하세요");

    	System.out.println("### 이름 : ");
    	String name = reader.readString();
    	System.out.println("### 국어 : ");
    	int kor = reader.readInt();
    	System.out.println("### 영어 : ");
    	int eng = reader.readInt();
    	System.out.println("### 수학 : ");
    	int math = reader.readInt();
    	
    	Score score = new Score();
    	score.setSudentName(name);
    	score.setKor(kor);
    	score.setEng(eng);
    	score.setMath(math);
    	
    	dao.insertScore(score);
    	
    	System.out.println("### 신규 성적정보가 저장되었습니다.");
    	System.out.println();
    }
	
	public void 종료() throws SQLException {
		System.out.println("<< 프로그램 종료 >>");
		System.out.println("### 프로그램을 종료합니다.");
		System.exit(0);
	}
    
    
}
