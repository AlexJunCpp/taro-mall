package org.rxjava.service.user.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static org.springframework.data.mongodb.core.index.IndexDirection.DESCENDING;

/**
 * @author happy 2019-04-22 20:11
 * 用户授权信息表
 */
@Data
@Document
@CompoundIndexes(
        @CompoundIndex(
                name = "identityType_identifier",
                unique = true,
                def = "{'identityType':1,'identifier':1}"
        )
)
public class UserAuth {
    @Id
    private String id;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 登录类型（手机号,邮箱,用户名）或第三方应用名称（微信 微博等）
     */
    private String identityType;
    /**
     * 标识（手机号,邮箱,用户名或第三方应用的唯一标识）
     */
    private String identifier;
    /**
     * 密码凭证（站内的保存密码，站外的不保存）
     */
    private String credential;
    /**
     * 创建日期
     */
    @CreatedDate
    @Indexed(direction = DESCENDING)
    private LocalDateTime createDate;
    /**
     * 更新日期
     */
    @LastModifiedDate
    private LocalDateTime updateDate;
}
