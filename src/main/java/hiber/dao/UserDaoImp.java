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
   public void getUserByCar() {

//      User user = null;

//      try(Session session = HibernateUtil.getSessionFactory().openSession()) {

         String HQL="FROM User WHERE car.car_id=:id";
//         Car car = session.createQuery(HQL, Car.class).setParameter("id", 1).uniqueResult();
         TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(HQL);
         query.setParameter("id", 1);
         System.out.println(query.getSingleResult());
//         System.out.println(car.getUser());
//         user = car.getUser();
//      } catch (HibernateException e) {
//         e.printStackTrace();
//      }

//      return user;
   }


   @Override
   public User getUserByCar(Car car) {
      String hql = "FROM User WHERE car.model = :model AND car.series = :series";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setParameter("model", car.getModel());
      query.setParameter("series", car.getSeries());
      return query.getSingleResult();
   }


   
}
