package com.example.service;

import com.example.domain.Users;

public interface UserService {

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    Users selectByPrimaryKey(Long id);

    /**
     * 修改
     *
     * @param record
     * @return
     */
    Users updateByPrimaryKey(Users record);

}
