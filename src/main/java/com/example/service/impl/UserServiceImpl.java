package com.example.service.impl;

import com.example.dao.UsersMapper;
import com.example.domain.Users;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersMapper usersMapper;

    //value:缓存名     key:缓存的key
    @Override
//    @Cacheable(value = "user", key = "#id")
    @Cacheable(value = "user")
    public Users selectByPrimaryKey(Long id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override
    @CachePut(value = "user", key = "#record.id")
    public Users updateByPrimaryKey(Users record) {
        int res = usersMapper.updateByPrimaryKey(record);
        if (res > 0) {
            return usersMapper.selectByPrimaryKey(record.getId());
        }
        return null;
    }
}
