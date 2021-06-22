package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;
   private Session HibernateUtil;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void getUserByCar(Car car) {

      try(Session session = HibernateUtil.getSessionFactory().openSession()) {

         String HQL="FROM Car cars LEFT OUTER JOIN FETCH cars.user WHERE cars.car_id=:id";
         car = session.createQuery(HQL, Car.class).setParameter("id", 1).uniqueResult();
         System.out.println(car);
         System.out.println(car.getUser());
      } catch (HibernateException e) {
         e.printStackTrace();
      }

//      return user;
   }
   
}
