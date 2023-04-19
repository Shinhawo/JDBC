package sample04;

import java.awt.RadialGradientPaint;
import java.security.PublicKey;
import java.sql.SQLException;
import java.util.List;

import util.KeyboardReader;

public class ProductApp {

	public static void main(String[] args) {
		new ProductApp().menu();
	}
	
	ProductDao dao = new ProductDao();
	KeyboardReader reader = new KeyboardReader();

	public void menu() {
		
		System.out.println("상품 관리 프로그램");

		while(true){
			System.out.println("-----------------------------------------------------------");
			System.out.println("1.전체조회 2.조회 3.저장 0.종료");
			System.out.println("-----------------------------------------------------------");
		
			System.out.println("### 메뉴선택 : ");
			int menuNo = reader.readInt();
			
			try {
				if(menuNo == 1) {
					전체조회();
				} else if(menuNo == 2) {
					조회();
				} else if(menuNo == 3) {
					저장();
				} else if(menuNo == 0) {
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
	
	public void 전체조회() throws SQLException{
		System.out.println("<< 전체조회 >>");
		System.out.println("### 상품 전체정보를 확인하세요.");
		
		List<Product> products = dao.getAllProduct();
		
		System.out.println("----------------------------------------------------------------");
		System.out.println("번호\t이름\t제조사\t가격\t할인률\t재고\t날짜");
		System.out.println("----------------------------------------------------------------");
	
		for(Product product : products) {
			
			System.out.print(product.getNo() + "\t");
			System.out.print(product.getName() + "\t");
			System.out.print(product.getMaker() + "\t");
			System.out.print(product.getPrice() + "\t");
			System.out.print(product.getDiscountRate() + "\t");
			System.out.print(product.getStock() + "\t");
			System.out.println(product.getCreateDate() );
		}
		System.out.println("----------------------------------------------------------------");
		System.out.println();
	}
	
	public void 조회() throws SQLException{
		System.out.println("<< 조회 >>");
		System.out.println("### 상품번호를 입력해서 해당 상품 정보를 확인하세요.");
		System.out.println("### 상품번호 : ");
		int no = reader.readInt();

		Product product = dao.getProductByNo(no);
		if(product == null) {
			System.out.println("상품정보가 존재하지 않습니다.");
		} else {
			
			System.out.println("---------------------------------------------------------");
			System.out.println("["+product.getNo()+"]번의 상품정보");
			System.out.println("---------------------------------------------------------");
			
			System.out.println("번호 : "+product.getNo());
			System.out.println("이름 : "+product.getName());
			System.out.println("제조사 : "+product.getMaker());
			System.out.println("가격 : "+product.getPrice());
			System.out.println("할인률 : "+product.getDiscountRate());
			System.out.println("재고 : "+product.getStock());
			System.out.println("날짜 : "+product.getCreateDate());
		
		}
		
	}
	
	public void 저장() throws SQLException{
		System.out.println("<< 저장 >>");
		System.out.println("### 이름,제조사,가격,할인률,재고를 입력하세요.");
		System.out.println("### 이름 :");
		String name = reader.readString();
		System.out.println("### 제조사 :");
		String maker = reader.readString();
		System.out.println("### 가격");
		int price = reader.readInt();
		System.out.println("### 할인률");
		double discountRate = reader.readDouble();
		System.out.println("### 재고");
		int stock = reader.readInt();
		
		Product product = new Product();
		product.setName(name);
		product.setMaker(maker);
		product.setPrice(price);
		product.setDiscountRate(discountRate);
		product.setStock(stock);
		
		dao.insertProduct(product);
		System.out.println("### 신규저장정보가 저장되었습니다.");
		System.out.println();
		
	}
	
	public void 종료() throws SQLException{
		System.out.println("<< 프로그램 종료 >>");
		System.out.println("### 프로그램 종료하기");
		System.exit(0);
	}
	
}
