package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   private static void getUserByCar() {

      try(Session session = HibernateUtil.getSessionFactory().openSession()) {

         String HQL="FROM Car cars LEFT OUTER JOIN FETCH cars.user WHERE cars.addressId=:addrId";
         Car car = session.createQuery(HQL, Car.class).setParameter("addrId", 1).uniqueResult();
         System.out.println(car);
         System.out.println(car.getUser());
      } catch (HibernateException e) {
         e.printStackTrace();
      }

   }

}
