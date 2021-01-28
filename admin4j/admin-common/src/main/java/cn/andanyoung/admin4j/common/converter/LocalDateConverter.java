package cn.andanyoung.admin4j.common.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/28 21:15
 */
@Component
public class LocalDateConverter implements Converter<String, LocalDate> {


    @Override
    public LocalDate convert(String source) {
        
        int length = StringUtils.length(source);
        if (length < 11) {
            return null;
        } else if (length > 11) {
            source = source.substring(0, 10);
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(source, dateTimeFormatter);
    }
}
