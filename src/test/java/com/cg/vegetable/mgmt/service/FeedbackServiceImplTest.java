package com.cg.vegetable.mgmt.service;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.vegetable.mgmt.entities.Feedback;
import com.cg.vegetable.mgmt.exceptions.InvalidCustIdException;
import com.cg.vegetable.mgmt.exceptions.InvalidFeedbackCommentException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableIdException;
import com.cg.vegetable.mgmt.repository.IFeedbackRepository;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FeedbackServiceImplTest {

	@Mock
	IFeedbackRepository feedbackRepository;

	@Spy
	@InjectMocks
	FeedbackServiceImpl feedbackService;

	/*
	 * scenario:addfeedback-success
	 */
	@Test
	void testAddFeedback() {
		Feedback feedback = Mockito.mock(Feedback.class);
		Feedback saved = Mockito.mock(Feedback.class);
		when(feedbackRepository.save(feedback)).thenReturn(saved);
		Feedback result = feedbackService.addFeedback(feedback);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(saved, result);

	}
	/*
	 * Scenario:addfeedback-Blank input
	 */	
	@Test
	public void testAddBill_2() {
		String transactionMode = "";
		Feedback feedback = Mockito.mock(Feedback.class);
		doThrow(InvalidFeedbackCommentException.class).when(feedbackService).validateFeedback(feedback);
		Executable executable = ()->feedbackService.addFeedback(feedback);
		Assertions.assertThrows(InvalidFeedbackCommentException.class, executable);
		verify(feedbackRepository, never()).save(feedback);
		
	}

	/*
	 * scenario:viewallfeedback-success
	 */
	@Test
	void testViewAllFeedback1() {
		int vegetableId = 1;
		List<Feedback> allfeedbacks = mock(List.class);
		when(feedbackRepository.findAll()).thenReturn(allfeedbacks);
		List<Feedback> result = feedbackService.viewAllFeedbacks(vegetableId);
		Assertions.assertSame(allfeedbacks, result);
		verify(feedbackRepository).findAll();
	}
	
	/*
	 * Scenario:exception
	 * */
	@Test
	void testviewAllFeedback2(){
		int vegetableId = 1;
		List<Feedback> fetchList = mock(List.class);
		Mockito.when(feedbackRepository.findAll()).thenReturn(fetchList);
		Mockito.when(fetchList.isEmpty()).thenReturn(true);
		Executable executable =()->feedbackService.viewAllFeedbacks(vegetableId);
		Assertions.assertThrows(InvalidVegetableIdException.class, executable);
	}

	/*
	 * scenario:viewfeedback-success
	 */
	@Test
	void testViewFeedback() {
		int custId = 2;
		List<Feedback> feedbacks = mock(List.class);
		when(feedbackRepository.findAll()).thenReturn(feedbacks);
		List<Feedback> result = feedbackService.viewFeedbacks(custId);
		Assertions.assertSame(feedbacks, result);
		verify(feedbackRepository).findAll();
	}
	
	/*
	 * Scenario:exception
	 * */
	@Test
	void testviewFeedback2(){
		int custId = 2;
		List<Feedback> fetchList = mock(List.class);
		Mockito.when(feedbackRepository.findAll()).thenReturn(fetchList);
		Mockito.when(fetchList.isEmpty()).thenReturn(true);
		Executable executable =()->feedbackService.viewFeedbacks(custId);
		Assertions.assertThrows(InvalidCustIdException.class, executable);
	}
}
