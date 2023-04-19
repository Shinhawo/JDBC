package sample04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.JoinRowSet;

import sample02.Score;
import util.ConnUtils;

public class ProductDao {

	 //신규 상품정보를 저장하는 기능
     public void insertProduct(Product product) throws SQLException {
    	 String url = "insert into sample_products "
    			 + "(product_no, product_name, product_maker, product_price, product_discount_rate, product_stock) "
    			 + "values "
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
    	 
    	 String sql = "select product_no, product_name, product_maker, product_price, product_discount_rate, product_stock, product_create_date  "
    			 + "from sample_products "
    			 + "order by product_no asc ";
    	 
    	 
    	 List<Product> productList = new ArrayList<>();

    	 Connection con = ConnUtils.getConnection();
    	 PreparedStatement ptsmt = con.prepareStatement(sql);
    	 ResultSet rs = ptsmt.executeQuery();
    	 
    	 while(rs.next()) {
    		 
    		 int no = rs.getInt("product_no");
    		 String name = rs.getString("product_name");
    		 String maker = rs.getString("product_maker");
    		 int price = rs.getInt("product_price");
    		 double discountRate = rs.getDouble("product_discount_rate");
    		 int stock = rs.getInt("product_stock");
    		 Date createDate = rs.getDate("product_create_date");
    		 
    		 Product product = new Product();
    		 product.setNo(no);
    		 product.setName(name);
    		 product.setMaker(maker);
    		 product.setPrice(price);
    		 product.setDiscountRate(discountRate);
    		 product.setStock(stock);
    		 product.setCreateDate(createDate);
    		 
    		 productList.add(product);
    	 }
    	 
    	 rs.close();
    	 ptsmt.close();
    	 con.close();
    	 
    	 return productList;
     }
	
	
	
	 //상품번호를 전달받아서 해당하는 상품정보를 반환하는 기능
     public Product getProductByNo (int productNo) throws SQLException {
    	 
    	 String sql = "select product_no, product_name, product_maker, product_price, product_discount_rate, product_stock, product_create_date "
    			 + "from sample_products "
    			 + "where product_no = ? ";
    	 
    	 Product product = null;
    	 
    	 Connection con = ConnUtils.getConnection();
    	 PreparedStatement ptsmt = con.prepareStatement(sql);
    	 
    	 ptsmt.setInt(1, productNo);
    	 ResultSet rs = ptsmt.executeQuery();
    	 
    	 while(rs.next()) {
    		 
    		 int no = rs.getInt("product_no");
    		 String name = rs.getString("product_name");
    		 String maker = rs.getString("product_maker");
    		 int price = rs.getInt("product_price");
    		 double discountRate = rs.getDouble("product_discount_rate");
    		 int stock = rs.getInt("product_stock");
    		 Date date = rs.getDate("product_create_date");
    		 
    		 product = new Product();
    		 
    		 product.setNo(no);
    		 product.setName(name);
    		 product.setMaker(maker);
    		 product.setPrice(price);
    		 product.setDiscountRate(discountRate);
    		 product.setStock(stock);
    		 product.setCreateDate(date);
    	 }
    	 
    	 rs.close();
    	 ptsmt.close();
    	 con.close();
    	 
    	 return product;
     }
	
	
	
	 //최소가격, 최대가격을 전달받아서 해당 가격범위에 포함된 상품정보를 반환하는 기능
     
	
	
	
	 //상품번호를 전달받아서 상품정보를 삭제하는 기능
}
