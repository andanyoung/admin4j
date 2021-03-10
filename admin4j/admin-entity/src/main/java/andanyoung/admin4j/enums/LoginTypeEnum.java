package andanyoung.admin4j.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/2/4 23:59
 */
@AllArgsConstructor
public enum LoginTypeEnum {
    USERNAME_PSD(1);

    @EnumValue
    private final int code;
}
