package sample04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.ConnUtils;

public class ProductDao {

	 //신규 상품정보를 저장하는 기능
     public void insertProduct(Product product) throws SQLException {
    	 String url = "insert into sample_products "
    			 + "(product_no, product_name, product_maker, product_price, product_discount_rate, product_stock) "
    			 + "values "
    			 // 시퀀스 이름뒤에 .nextval 꼭 붙여야함. 그렇지 않으면 시퀀스명을 컬럼으로 인식해 
    			 // '열(컬럼)을 사용할 수 없습니다.' 라는 오류가 발생
    			 + "(SAMPLE_PRODUCT_SEQ.nextval, ?,?,?,?,?) ";
    	 
    	 Connection con = ConnUtils.getConnection();
    	 PreparedStatement ptsmt = con.prepareStatement(url);
    	 
    	 ptsmt.setString(1, product.getName());
    	 ptsmt.setString(2, product.getMaker());
    	 ptsmt.setInt(3, product.getPrice());
    	 ptsmt.setDouble(4, product.getDiscountRate());
    	 ptsmt.setInt(5, product.getStock());
    	
    	 ptsmt.executeUpdate();
    	 
    	 ptsmt.close();
    	 con.close();
     }
	
	 //모든 상품정보를 조회해서 반환하는 기능
     public List<Product> getAllProduct() throws SQLException {
    	 
    	 String sql = "select product_no, "
    			 + "          product_name, "
    			 + "          product_maker, "
    			 + "          product_price, "
    			 + "          product_discount_rate, " 
    			 + "          product_stock, " 
    			 + "          product_create_date "
    			 + "from sample_products "
    			 + "order by product_no desc ";
    	 
    	 List<Product> productList = new ArrayList<>();

    	 Connection con = ConnUtils.getConnection();
    	 PreparedStatement ptsmt = con.prepareStatement(sql);
    	 
    	 // ResultSet에 executeQuery로 조회된 테이블과 커서가 담긴다.
    	 ResultSet rs = ptsmt.executeQuery();
    	 while(rs.next()) {
    		
    		 Product product = new Product();
    		 product.setNo(rs.getInt("product_no"));
    		 product.setName(rs.getString("product_name"));
    		 product.setMaker(rs.getString("product_maker"));
    		 product.setPrice(rs.getInt("product_price"));
    		 product.setDiscountRate(rs.getDouble("product_discount_rate"));
    		 product.setStock(rs.getInt("product_stock"));
    		 product.setCreateDate(rs.getDate("product_create_date"));
    		 
    		 productList.add(product);
    	 }
    	 
    	 rs.close();
    	 ptsmt.close();
    	 con.close();
    	 
    	 return productList;
     }
	
	 //상품번호를 전달받아서 해당하는 상품정보를 반환하는 기능
     public Product getProductByNo (int productNo) throws SQLException {
    	 
    	 String sql = "select product_no, "
    			 + "          product_name, "
    			 + "          product_maker, "
    			 + "          product_price, "
    			 + "          product_discount_rate, " 
    			 + "          product_stock, " 
    			 + "          product_create_date "
    			 + "from sample_products "
    			 + "where product_no = ? ";
    	 
    	 Product product = null;
    	 
    	 Connection con = ConnUtils.getConnection();
    	 PreparedStatement ptsmt = con.prepareStatement(sql);
    	 
    	 ptsmt.setInt(1, productNo);
    	 
    	 ResultSet rs = ptsmt.executeQuery();
    	 while(rs.next()) {
    		 product = new Product();
    		 product.setNo(rs.getInt("product_no"));
    		 product.setName(rs.getString("product_name"));
    		 product.setMaker(rs.getString("product_maker"));
    		 product.setPrice(rs.getInt("product_price"));
    		 product.setDiscountRate(rs.getDouble("product_discount_rate"));
    		 product.setStock(rs.getInt("product_stock"));
    		 product.setCreateDate(rs.getDate("product_create_date"));
    	 }
    	 
    	 rs.close();
    	 ptsmt.close();
    	 con.close();
    	 
    	 return product;
     }
	
	 //최소가격, 최대가격을 전달받아서 해당 가격범위에 포함된 상품정보를 반환하는 기능
     public List<Product> getAllProductByPrice(int minPrice, int maxPrice) throws SQLException{
    	 
    	 String sql = "select product_no, "
    			 + "          product_name, "
    			 + "          product_maker, "
    			 + "          product_price, "
    			 + "          product_discount_rate, " 
    			 + "          product_stock, " 
    			 + "          product_create_date "
    			 + "from sample_products "
    			 + "where product_price between ? and ? "
    			 + "order by product_price asc ";
    	 
    	 List<Product> productList = new ArrayList<>(); 
    	 
    	 Connection con = ConnUtils.getConnection();
    	 PreparedStatement ptsmt = con.prepareStatement(sql);
    	 ptsmt.setInt(1, minPrice);
    	 ptsmt.setInt(2, maxPrice);
    	 
    	 ResultSet rs = ptsmt.executeQuery();
    	 
    	 while(rs.next()) {
    		 Product product = new Product();
    		 product.setNo(rs.getInt("product_no"));
    		 product.setName(rs.getString("product_name"));
    		 product.setMaker(rs.getString("product_maker"));
    		 product.setPrice(rs.getInt("product_price"));
    		 product.setDiscountRate(rs.getDouble("product_discount_rate"));
    		 product.setStock(rs.getInt("product_stock"));
    		 product.setCreateDate(rs.getDate("product_create_date"));
    		 
    		 productList.add(product);
    	 }
    	 
    	 rs.close();
    	 ptsmt.close();
    	 con.close();
    	 
    	 return productList;
     }
	
	
	
	 //상품번호를 전달받아서 상품정보를 삭제하는 기능
     /*
      * sql에서 삭제후 같은 행을 App에서 또 삭제하면 프로그램이 멈춘다.
      *  -> sql에서 수정,삭제 중 오라클은 그 행을 행잠금을 한다. (다른 곳에서 수정 불가, 읽어가기는 가능)
      *  -> rollback이나 commit을 실행하면 행잠금이 해제된다.
      *  => commit이나 rollback을 해야 sql developer가 작업을 끝내고, App에서도 수정&삭제가 가능해진다! 
      *     트랜잭션의 격리성
      */
     
     public void deleteProduct(int productNo) throws SQLException {
    	 
    	 String sql = "delete from sample_products where product_no = ?";
    	 
    	 Connection con = ConnUtils.getConnection();
    	 PreparedStatement ptsmt = con.prepareStatement(sql);
    	 
    	 
    	 ptsmt.setInt(1, productNo);
    	 ptsmt.executeUpdate();
    	 
    	 ptsmt.close();
    	 con.close();
    	 
     }
     
     public void updateProduct(Product product) throws SQLException{
    	 
    	 String sql = "update sample_products "
    			 + "set "
    			 + "product_name = ?, "
    			 + "product_maker = ?, "
    			 + "product_price = ?, "
    			 + "product_discount_rate = ?, "
    			 + "product_stock = ? "
    			 + "where product_no = ? ";
    	 
    	 Connection con = ConnUtils.getConnection();
    	 PreparedStatement ptsmt = con.prepareStatement(sql);
    	 ptsmt.setString(1, product.getName());
    	 ptsmt.setString(2, product.getMaker());
    	 ptsmt.setInt(3, product.getPrice());
    	 ptsmt.setDouble(4, product.getDiscountRate());
    	 ptsmt.setInt(5, product.getStock());
    	 ptsmt.setInt(6, product.getNo());
    	 
    	 ptsmt.executeUpdate();
    	 
    	 ptsmt.close();
    	 con.close();
     }
}
