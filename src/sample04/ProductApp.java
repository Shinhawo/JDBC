package sample04;

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
			System.out.println("1.전체조회 2.조회 3.저장 4.가격정보 5.삭제 6.변경 0.종료");
			System.out.println("-----------------------------------------------------------");
		
			System.out.println("### 메뉴선택 : ");
			int menuNo = reader.readInt();
			
			try {
				if(menuNo == 1) {
					전체조회();
				} else if(menuNo == 2) {
					상세조회();
				} else if(menuNo == 3) {
					저장();
				} else if(menuNo == 4) {
					가격검색();
				} else if(menuNo == 5) {
					삭제();
				} else if(menuNo == 6) {
					변경();
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
		
		if(products.isEmpty()) { // getAllProduct()에서 ArrayList를 만들고 시작했기 때문에 
								 // List는 절대 null은 아니다. 따라서 isEmpty()를 사용했다.
			System.out.println("등록된 상품정보가 없습니다.");
			System.out.println();
		}
		
		else {
			System.out.println("----------------------------------------------------------------");
			System.out.println("번호\t이름\t제조사\t가격\t할인율\t할인가\t재고\t날짜");
			System.out.println("----------------------------------------------------------------");
		
			for(Product product : products) {
				
				System.out.print(product.getNo() + "\t");
				System.out.print(product.getName() + "\t");
				System.out.print(product.getMaker() + "\t");
				System.out.print(product.getPrice() + "\t");
				System.out.print(product.getDiscountPercent() + "\t");
				System.out.print(product.getDiscountPrice()+ "\t");
				System.out.print(product.getStock() + "\t");
				System.out.println(product.getCreateDate() );
			}
			System.out.println("----------------------------------------------------------------");
			System.out.println();
		}
	}
	
	public void 상세조회() throws SQLException{
		System.out.println("<< 조회 >>");
		System.out.println("### 상품번호를 입력해서 해당 상품 정보를 확인하세요.");
		System.out.println("### 상품번호 : ");
		int no = reader.readInt();

		Product product = dao.getProductByNo(no);
		if(product == null) {
			System.out.println("상품정보가 존재하지 않습니다.");
		} else {
			
			System.out.println("----------------------------------------------------------------");
			System.out.println("번호\t이름\t제조사\t가격\t할인율\t할인가\t재고\t날짜");
			System.out.println("----------------------------------------------------------------");
		
			System.out.print(product.getNo() + "\t");
			System.out.print(product.getName() + "\t");
			System.out.print(product.getMaker() + "\t");
			System.out.print(product.getPrice() + "\t");
			System.out.print(product.getDiscountPercent() + "\t");
			System.out.print(product.getDiscountPrice()+ "\t");
			System.out.print(product.getStock() + "\t");
			System.out.println(product.getCreateDate() );
			
			System.out.println("----------------------------------------------------------------");
			System.out.println();
		}
		
	}
	
	public void 저장() throws SQLException{
		System.out.println("<< 저장 >>");
		System.out.println("### 이름,제조사,가격,할인율,재고를 입력하세요.");
		System.out.println("### 이름 :");
		String name = reader.readString();
		System.out.println("### 제조사 :");
		String maker = reader.readString();
		System.out.println("### 가격");
		int price = reader.readInt();
		System.out.println("### 할인율");
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
	
	public void 가격검색() throws SQLException, NumberFormatException {
		System.out.println("<< 가격 정보 검색 >>");
		System.out.println("### 최소가격과 최대가격을 설정하여 상품정보를 확인하세요.");
		System.out.println("### 최소가격 : ");
		int minPrice = reader.readInt();
		System.out.println("### 최대가격 :");
		int maxPrice = reader.readInt();
		
		List<Product> products = dao.getAllProductByPrice(minPrice, maxPrice);
	
		if(products.isEmpty()) {
			System.out.println("상품정보가 존재하지 않습니다.");
		} else {
			System.out.println("----------------------------------------------------------------");
			System.out.println("번호\t이름\t제조사\t가격\t할인율\t할인가\t재고\t날짜");
			System.out.println("----------------------------------------------------------------");
			
			for(Product product : products) {
				
				System.out.print(product.getNo() + "\t");
				System.out.print(product.getName() + "\t");
				System.out.print(product.getMaker() + "\t");
				System.out.print(product.getPrice() + "\t");
				System.out.print(product.getDiscountPercent() + "\t");
				System.out.print(product.getDiscountPrice()+ "\t");
				System.out.print(product.getStock() + "\t");
				System.out.println(product.getCreateDate() + "\t");
			}
			
			System.out.println("----------------------------------------------------------------");
			System.out.println();
		}
	}
	
	public void 삭제() throws SQLException{
		System.out.println("<< 상품정보삭제>>");
		System.out.println("### 삭제할 상품의 번호를 입력하여 상품정보를 삭제하세요.");
		System.out.println("### 상품번호 : ");
		
		int Productno = reader.readInt();
		dao.deleteProduct(Productno);
		
		System.out.println("### ["+ Productno +"]번 상품의 삭제가 완료되었습니다.");
		System.out.println();
	}
	
	public void 변경() throws SQLException{
		System.out.println("<< 상품정보변경 >>");
		System.out.println("### 변경할 상품의 번호와, 변경할 내용을 입력해주세요.");
		System.out.println("### 상품번호 : ");
		int no = reader.readInt();
		System.out.println("### 이름 :");
		String name = reader.readString();
		System.out.println("### 제조사 :");
		String maker = reader.readString();
		System.out.println("### 가격");
		int price = reader.readInt();
		System.out.println("### 할인율");
		double discountRate = reader.readDouble();
		System.out.println("### 재고");
		int stock = reader.readInt();
		
		Product product = new Product();
		product.setNo(no);
		product.setName(name);
		product.setMaker(maker);
		product.setPrice(price);
		product.setDiscountRate(discountRate);
		product.setStock(stock);
		
		dao.updateProduct(product);
		System.out.println("### ["+ no +"]번 상품정보 변경이 완료되었습니다.");
		
	}
	
	public void 종료() throws SQLException{
		System.out.println("<< 프로그램 종료 >>");
		System.out.println("### 프로그램 종료하기");
		System.exit(0);
	}
	
}
