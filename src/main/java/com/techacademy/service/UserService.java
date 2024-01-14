package com.techacademy.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.User;
import com.techacademy.repository.UserRepository;
import com.techacademy.kadaijavareview05.Review05;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired // @Autowired アノテーションは「Spring4.3以上で単一のコンストラクタ」の場合は省略可
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    /** 全件を検索して返す */
    public List<User> getUserList() {
        // リポジトリのfindAllメソッドを呼び出す
        return userRepository.findAll();
    }

    /** Userの登録を行なう */
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    /** Userを1件検索して返す */
    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }
    
    /** Userの削除を行なう */
    @Transactional
    // public void deleteUser(Set<Integer> idck) {
        // for(Integer id : idck) {
            // userRepository.deleteById(id);
        // }
    // }
    
    /** Userの削除を行なう（kadaijavareview05のファイルを流用して実装） */
    public void deleteUser(Set<Integer> idck) {
        for(Integer id : idck) {
            Review05.myDelete((int)id);
        }
    }
}