package com.cg.vegetable.mgmt.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
	
	private final String datePattern="dd LLLL yyyy";
	private final String dateTimePattern = "dd/MM/yy hh:mm:ss";

    public String toText(LocalDate date, String datePattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        String text=formatter.format(date);
        return text;
    }

    public String toText(LocalDate date){
        String text= toText(date,datePattern);
        return text;
    }
    
    public String toText(LocalDateTime datetime, String dateTimePattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
        String text=formatter.format(datetime);
        return text;
    }

    public String toText(LocalDateTime datetime){
        String text= toText(datetime,dateTimePattern);
        return text;
    }
    
    

    public LocalDate toLocalDate(String dateText, String datePattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        LocalDate date= LocalDate.parse(dateText,formatter);
        return date;
    }


    public LocalDate toLocalDate(String dateText){
        LocalDate date= toLocalDate(dateText,datePattern);
        return date;
    }

}
