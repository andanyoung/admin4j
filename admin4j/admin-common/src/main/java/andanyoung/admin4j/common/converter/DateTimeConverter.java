package andanyoung.admin4j.common.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/28 21:15
 */
@Component
public class DateTimeConverter implements Converter<String, LocalDateTime> {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public LocalDateTime convert(String source) {
        
        return LocalDateTime.parse(source, dateTimeFormatter);
    }
}
