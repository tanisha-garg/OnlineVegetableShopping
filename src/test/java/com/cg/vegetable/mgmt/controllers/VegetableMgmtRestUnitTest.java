package com.cg.vegetable.mgmt.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.util.List;

import com.cg.vegetable.mgmt.controllers.VegetableRestController;
import com.cg.vegetable.mgmt.dto.AddVegetableRequest;
import com.cg.vegetable.mgmt.dto.DeleteVegetableRequest;
import com.cg.vegetable.mgmt.dto.UpdateVegetableCategoryRequest;
import com.cg.vegetable.mgmt.dto.UpdateVegetableNameRequest;
import com.cg.vegetable.mgmt.dto.UpdateVegetablePriceRequest;
import com.cg.vegetable.mgmt.dto.UpdateVegetableQuantityRequest;
import com.cg.vegetable.mgmt.dto.UpdateVegetableTypeRequest;
import com.cg.vegetable.mgmt.dto.VegetableDetails;
import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.service.IVegetableMgmtService;
import com.cg.vegetable.mgmt.util.VegetableUtil;

@ExtendWith(MockitoExtension.class)
class VegetableMgmtRestUnitTest {
	
	@Mock
	IVegetableMgmtService vegService;
	
	@Mock
	VegetableUtil vegUtil;
	
	@Spy
	@InjectMocks
	VegetableRestController vegController;
	
	
	@Test
	public void add_AddStudent() {
		Vegetable vegetable = mock(Vegetable.class);
		Vegetable saved = mock(Vegetable.class);
		VegetableDetails vegDetails=mock(VegetableDetails.class);
		AddVegetableRequest request = mock(AddVegetableRequest.class);
		when(vegService.addVegetable(any(Vegetable.class))).thenReturn(saved);
		when(vegUtil.toDetails(any(Vegetable.class))).thenReturn(vegDetails);
		VegetableDetails result = vegController.addVegetable(request);
		assertNotNull(result);
		assertSame(vegDetails,result);
		verify(vegService).addVegetable(any(Vegetable.class));
		verify(vegUtil).toDetails(any(Vegetable.class));
	}
	
	@Test
	public void test_fetchVegetable() {
		int vegId=2;
		Vegetable vegetable = mock(Vegetable.class);
		VegetableDetails vegDetails = mock(VegetableDetails.class);
		when(vegService.viewVegetable(vegId)).thenReturn(vegetable);
		when(vegUtil.toDetails(vegetable)).thenReturn(vegDetails);
		VegetableDetails result =vegController.fetchVegetable(vegId);
		assertSame(vegDetails,result);
		verify(vegService).viewVegetable(vegId);
		verify(vegUtil).toDetails(vegetable);
		
	}
	
	@Test
	public void test_deleteVegetable() {
		int vegId=4;
		DeleteVegetableRequest request = mock(DeleteVegetableRequest.class);
		Vegetable vegetable = mock(Vegetable.class);
		when(request.getVegId()).thenReturn(vegId);
		when(vegService.viewVegetable(vegId)).thenReturn(vegetable);
		vegController.delete(request);
		verify(vegService).removeVegetable(vegetable);
	}
	@Test
	public void test_changeName() {
		int vegId=4;
		UpdateVegetableNameRequest request = mock(UpdateVegetableNameRequest.class);
		Vegetable vegetable =mock(Vegetable.class);
		when(request.getVegId()).thenReturn(vegId);
		when(request.getName()).thenReturn("potato");
		when(vegService.viewVegetable(vegId)).thenReturn(vegetable);
		when(vegService.updateVegetable(vegetable)).thenReturn(vegetable);
		VegetableDetails details=mock(VegetableDetails.class);
		when(vegUtil.toDetails(vegetable)).thenReturn(details);
		VegetableDetails result=vegController.changeName(request);
		assertNotNull(result);
		assertSame(details,result);
		verify(vegService).viewVegetable(vegId);
		verify(vegService).updateVegetable(vegetable);
		verify(vegUtil).toDetails(vegetable);
		
	}
	
	@Test
	public void test_changeType() {
		int vegId=4;
		UpdateVegetableTypeRequest request = mock(UpdateVegetableTypeRequest.class);
		Vegetable vegetable =mock(Vegetable.class);
		when(request.getVegId()).thenReturn(vegId);
		when(request.getType()).thenReturn("potato");
		when(vegService.viewVegetable(vegId)).thenReturn(vegetable);
		when(vegService.updateVegetable(vegetable)).thenReturn(vegetable);
		VegetableDetails details=mock(VegetableDetails.class);
		when(vegUtil.toDetails(vegetable)).thenReturn(details);
		VegetableDetails result=vegController.changeType(request);
		assertNotNull(result);
		assertSame(details,result);
		verify(vegService).viewVegetable(vegId);
		verify(vegService).updateVegetable(vegetable);
		verify(vegUtil).toDetails(vegetable);
		
	}
	
	@Test
	public void test_changeCategory() {
		int vegId=4;
		UpdateVegetableCategoryRequest request = mock(UpdateVegetableCategoryRequest.class);
		Vegetable vegetable =mock(Vegetable.class);
		when(request.getVegId()).thenReturn(vegId);
		when(request.getCategory()).thenReturn("potato");
		when(vegService.viewVegetable(vegId)).thenReturn(vegetable);
		when(vegService.updateVegetable(vegetable)).thenReturn(vegetable);
		VegetableDetails details=mock(VegetableDetails.class);
		when(vegUtil.toDetails(vegetable)).thenReturn(details);
		VegetableDetails result=vegController.changeCategory(request);
		assertNotNull(result);
		assertSame(details,result);
		verify(vegService).viewVegetable(vegId);
		verify(vegService).updateVegetable(vegetable);
		verify(vegUtil).toDetails(vegetable);
		
	}
	
	@Test
	public void test_changePrice() {
		int vegId=4;
		UpdateVegetablePriceRequest request = mock(UpdateVegetablePriceRequest.class);
		Vegetable vegetable =mock(Vegetable.class);
		when(request.getVegId()).thenReturn(vegId);
		when(request.getPrice()).thenReturn(15.0);
		when(vegService.viewVegetable(vegId)).thenReturn(vegetable);
		when(vegService.updateVegetable(vegetable)).thenReturn(vegetable);
		VegetableDetails details=mock(VegetableDetails.class);
		when(vegUtil.toDetails(vegetable)).thenReturn(details);
		VegetableDetails result=vegController.changePrice(request);
		assertNotNull(result);
		assertSame(details,result);
		verify(vegService).viewVegetable(vegId);
		verify(vegService).updateVegetable(vegetable);
		verify(vegUtil).toDetails(vegetable);
		
	}
	
	@Test
	public void test_changeQuantity() {
		int vegId=4;
		UpdateVegetableQuantityRequest request = mock(UpdateVegetableQuantityRequest.class);
		Vegetable vegetable =mock(Vegetable.class);
		when(request.getVegId()).thenReturn(vegId);
		when(request.getQuantity()).thenReturn(15);
		when(vegService.viewVegetable(vegId)).thenReturn(vegetable);
		when(vegService.updateVegetable(vegetable)).thenReturn(vegetable);
		VegetableDetails details=mock(VegetableDetails.class);
		when(vegUtil.toDetails(vegetable)).thenReturn(details);
		VegetableDetails result=vegController.changeQuantity(request);
		assertNotNull(result);
		assertSame(details,result);
		verify(vegService).viewVegetable(vegId);
		verify(vegService).updateVegetable(vegetable);
		verify(vegUtil).toDetails(vegetable);
		
	}
	
	@Test
	public void test_viewAllVegetable() {
		List<Vegetable>vegList=mock(List.class);
		when(vegService.viewAllVegetables()).thenReturn(vegList);
		List<VegetableDetails>desiredList=mock(List.class);
		when(vegUtil.toDetailsList(vegList)).thenReturn(desiredList);
		List<VegetableDetails>result=vegController.findAllVegetables();
		assertNotNull(result);
		assertSame(desiredList,result);
		verify(vegService).viewAllVegetables();
		verify(vegUtil).toDetailsList(vegList);
	}
	
	@Test
	public void test_viewAllVegetableByName() {
		String name="potato";
		List<Vegetable>vegList=mock(List.class);
		when(vegService.viewVegetableByName(name)).thenReturn(vegList);
		List<VegetableDetails>desiredList=mock(List.class);
		when(vegUtil.toDetailsList(vegList)).thenReturn(desiredList);
		List<VegetableDetails>result=vegController.findVegetablesByName(name);
		assertNotNull(result);
		assertSame(desiredList,result);
		verify(vegService).viewVegetableByName(name);
		verify(vegUtil).toDetailsList(vegList);
	}
	
	@Test
	public void test_viewAllVegetableByCategory() {
		String category="underground";
		List<Vegetable>vegList=mock(List.class);
		when(vegService.viewVegetableByCategory(category)).thenReturn(vegList);
		List<VegetableDetails>desiredList=mock(List.class);
		when(vegUtil.toDetailsList(vegList)).thenReturn(desiredList);
		List<VegetableDetails>result=vegController.findVegetablesByCategory(category);
		assertNotNull(result);
		assertSame(desiredList,result);
		verify(vegService).viewVegetableByCategory(category);
		verify(vegUtil).toDetailsList(vegList);
	}
	
	

}
