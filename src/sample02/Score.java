package sample02;

import java.util.Date;

public class Score {

	private int studentNo;
	private String sudentName;
	private int kor;
	private int eng;
	private int math;
	private Date createDate;
	
	public Score() {}

	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

	public String getSudentName() {
		return sudentName;
	}

	public void setSudentName(String sudentName) {
		this.sudentName = sudentName;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
}
