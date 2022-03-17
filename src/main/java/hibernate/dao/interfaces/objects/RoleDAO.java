package hibernate.dao.interfaces.objects;

import hibernate.dao.interfaces.CommonDAO;
import hibernate.dao.interfaces.FindDAO;
import hibernate.entity.Role;
import hibernate.entity.Stat;

import java.util.List;

/*

Указываем какие интерфейсы будем использовать - это означает, какие возможности будут у объекта
Также можно добавлять любые другие специфичные метода объекта

Роли у нас статичные (не изменяются) - может изменять только админ БД
Роли пользователь сразу получает при выборке User (EAGER)

*/

public interface RoleDAO {
}