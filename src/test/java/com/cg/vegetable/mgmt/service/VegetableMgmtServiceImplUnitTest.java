//package com.cg.vegetable.mgmt.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import javax.persistence.EntityManager;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.api.function.Executable;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.cg.vegetable.mgmt.entities.VegetableDTO;
//import com.cg.vegetable.mgmt.exceptions.InvalidVegetableCategoryException;
//import com.cg.vegetable.mgmt.exceptions.InvalidVegetableIdException;
//import com.cg.vegetable.mgmt.exceptions.InvalidVegetableNameException;
//import com.cg.vegetable.mgmt.exceptions.InvalidVegetablePriceException;
//import com.cg.vegetable.mgmt.exceptions.InvalidVegetableQuantityException;
//import com.cg.vegetable.mgmt.exceptions.InvalidVegetableTypeException;
//import com.cg.vegetable.mgmt.repository.IVegetableMgmtRepository;
//
//@ExtendWith(MockitoExtension.class)
//class VegetableMgmtServiceImplUnitTest {
//	
//	@Mock
//	IVegetableMgmtRepository vegetableMgmtRepository;
//	
//	@Mock
//	EntityManager entityManager;
//	
//	@Spy
//	@InjectMocks
//	VegetableMgmtServiceImpl vegetableMgmtServiceImplservice;
//	
//
//	/*
//	 * scenario : success scenario
//	 * 
//	 */
//
//	@Test
//	public void test_Add1() {
//		int vegId=1;
//		String name="potato";
//		String type="root";
//		String category="underground";
//		double price=20.0;
//		int quantity=3;
//		VegetableDTO vegetable = new VegetableDTO(vegId,name,type,category,price,quantity);
//		VegetableDTO result=vegetableMgmtServiceImplservice.addVegetable(vegetable);
//		Assertions.assertNotNull(result);
//		Assertions.assertEquals(vegId, result.getVegId());
//		Assertions.assertEquals(name, result.getName());
//		Assertions.assertEquals(type, result.getType());
//		Assertions.assertEquals(category, result.getCategory());
//		Assertions.assertEquals(price, result.getPrice());
//		Assertions.assertEquals(quantity, result.getQuantity());
//	}
//	
//	
//	/*
//	 * 
//	 *   scenario : vegId is negative 
//	 * 
//	 */
//	
//	@Test
//	public void test_ValidateId() {
//		
//		int vegId=-10;
//		VegetableDTO vegetable = new VegetableDTO(vegId,"potato","root","underground",20.0,3);
//		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
//		Assertions.assertThrows(InvalidVegetableIdException.class, executable);
//		
//	}
//	
//	/*
//	 *  scenario : name is empty
//	 * 
//	 */
//	@Test
//	public void test_ValidateName() {
//		String name="";
//		VegetableDTO vegetable = new VegetableDTO(2,name,"root","underground",20.0,3);
//		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
//		Assertions.assertThrows(InvalidVegetableNameException.class, executable);
//	}
//	
//	/*
//	 *  scenario : type is empty
//	 * 
//	 */
//	@Test
//	public void test_ValidateType() {
//		String type="";
//		VegetableDTO vegetable = new VegetableDTO(2,"potato",type,"underground",20.0,3);
//		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
//		Assertions.assertThrows(InvalidVegetableTypeException.class, executable);
//	}
//	
//	/*
//	 *  scenario : category is empty
//	 * 
//	 */
//	@Test
//	public void test_ValidateCategory() {
//		String category="";
//		VegetableDTO vegetable = new VegetableDTO(2,"potato","root",category,20.0,3);
//		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
//		Assertions.assertThrows(InvalidVegetableCategoryException.class, executable);
//	}
//	
//	/*
//	 *  scenario : price is negative
//	 * 
//	 */
//	@Test
//	public void test_ValidatePrice() {
//		double price=-20.0;
//		VegetableDTO vegetable = new VegetableDTO(2,"potato","root","underground",price,3);
//		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
//		Assertions.assertThrows(InvalidVegetablePriceException.class, executable);
//	}
//	
//	/*
//	 *  scenario : quantity is negative
//	 * 
//	 */
//	@Test
//	public void test_ValidateQuantity() {
//		int quantity=-5;;
//		VegetableDTO vegetable = new VegetableDTO(2,"potato","root","underground",20.0,quantity);
//		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
//		Assertions.assertThrows(InvalidVegetableQuantityException.class, executable);
//	}
//	
//	/*
//	 * scenario : to view the vegetable if id is given and 
//	 * 			  vegetable is found successfully
//	 * 
//	 */
//	@Test
//	public void viewVegetable_1() {
//		int vegId=1;
//		VegetableDTO vegetable = new VegetableDTO(1,"potato","root","underground",20.0,3);
//		VegetableDTO result=vegetableMgmtServiceImplservice.viewVegetable(vegetable);
//		Assertions.assertNotNull(result);
//		Assertions.assertEquals(vegId,result.getVegId());
//		Assertions.assertEquals("potato",result.getName());
//		Assertions.assertEquals("root",result.getType());
//		Assertions.assertEquals("underground",result.getCategory());
//		Assertions.assertEquals(20.0,result.getPrice());
//		Assertions.assertEquals(3,result.getQuantity());	
//	}
//	
//	/*
//	 * scenario : to view the vegetable if id is given and 
//	 * 			  vegetable is not found 
//	 * 
//	 */
//	@Test
//	public void viewVegetable_2() {
//		int vegId=100;
//		Executable executable =()->vegetableMgmtServiceImplservice.viewVegetable(null);
//	}
//	
//}
