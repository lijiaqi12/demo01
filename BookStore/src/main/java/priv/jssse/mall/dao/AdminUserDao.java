package priv.jssse.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import priv.jssse.mall.entity.AdminUser;

public interface AdminUserDao extends JpaRepository<AdminUser, Integer> {
    AdminUser findByUsernameAndPassword(String username, String pwd);
}
