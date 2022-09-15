package com.yjc.authdemo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author admin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotNull(message = "id不能为空")
    private Integer id;

    @NotBlank(message="姓名不能为空")
    @Length(min = 2, max = 4, message = "name 姓名长度必须在 {min} - {max} 之间")
    private String username;

    @NotBlank(message="密码不能为空")
    @Length(min = 5, max = 10, message = "password 密码长度必须在 {min} - {max} 之间")
    private String password;

    @NotBlank(message="手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号不能为空")
    private String phone;

}
