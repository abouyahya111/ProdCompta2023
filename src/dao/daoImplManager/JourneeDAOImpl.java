package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.JourneeDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.Journee;

public class JourneeDAOImpl implements JourneeDAO{
    
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;


  public Journee findById(int id) {

return (Journee)session.get(Journee.class, id);    }


  public void add(Journee journee) {

             session.beginTransaction();
		session.save(journee);
		
		session.getTransaction().commit();    }

  
  public Journee edit(Journee e) {

session.beginTransaction();
	Journee p= (Journee)session.merge(e);
	session.getTransaction().commit();
		return p;

  }

  
  public void delete(Journee e) {

              session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
  }

  public List<Journee> findAll() {

return session.createQuery("select c from Journee c").list();


  }
  
     public List<Journee> findByDateEtat(Date date, String etat) {
	Query query = session.createQuery("select c from Journee c where c.dateOvertute =:date and c.statue=:etat ");
              query.setParameter("date",date);
              query.setParameter("etat",etat);

 
               return query.list();
  
        }
    public Journee findJourneeByDetailOverte(String etat,int depot) {
	Query query = session.createQuery("select c from Journee c where c.statue=:etat and c.depot.id=:depot");

              query.setParameter("etat",etat);
              query.setParameter("depot",depot);

 
               return (Journee) query.uniqueResult();
    }
    
    public Journee findByDateEtatOuverte(Date date, String etat,int depot) {
	Query query = session.createQuery("select c from Journee c where c.dateOvertute =:date and c.statue=:etat and c.depot.id=:depot");
             query.setParameter("date",date);
             query.setParameter("etat",etat);
             query.setParameter("depot",depot);

             return (Journee) query.uniqueResult();
 
       }
    
    
    
}
