package cn.andanyoung.admin4j.common.response.enums;

import org.junit.jupiter.api.Test;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/26 22:31
 */
class ResponseEnumTest {

    @Test
    void assertEqualsWithMsg() {

        ResponseEnum.INTERNAL_SERVER_ERROR.assertEqualsWithMsg(Integer.valueOf(1), Integer.valueOf(1), "Integer. error");
    }

}