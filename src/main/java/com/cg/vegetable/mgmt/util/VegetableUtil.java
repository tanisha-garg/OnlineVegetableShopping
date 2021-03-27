package com.cg.vegetable.mgmt.util;



public class VegetableUtil {
	
	public StudentDetails toDetails(Student student){
        StudentDetails details=new StudentDetails();
        details.setId(student.getId());
        details.setName(student.getName());
        details.setScore(student.getScore());
        Course course=student.getCourse();
        if(course!=null) {
            details.setCourseId(course.getCourseId());
            details.setCourseName(course.getName());
        }
        return details;
    }

}
