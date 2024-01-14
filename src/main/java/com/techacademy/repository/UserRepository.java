package com.techacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techacademy.entity.User;

@Repository //本来、リポジトリクラスには @Repository アノテーションを付ける必要がありますが、 JpaRepository インタフェースを継承した場合には省略可能
public interface UserRepository extends JpaRepository<User, Integer> {
}