/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.daoImplManager;

import dao.entity.*;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Session;

import util.HibernateUtil;

import org.hibernate.Query;

import dao.daoManager.CompteClientDAO;

/**
 *
 * @author Hp
 */
public class CompteClientDAOImpl implements CompteClientDAO {
    // Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

    
    
	public CompteClient findById(int id) {
		return (CompteClient)session.get(CompteClient.class, id);
		}

   
	public void add(CompteClient compteClient) {
		session.beginTransaction();
		session.save(compteClient);
		
		session.getTransaction().commit();
		
	}


	public CompteClient edit(CompteClient e) {
		
	session.beginTransaction();
	CompteClient p= (CompteClient)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(CompteClient e) {
		
		session.beginTransaction();
		
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<CompteClient> findAll() {
              return session.createQuery("select c from CompteClient c").list();
    }
    
	@Override
	public  List<CompteClient> findListCompteClientByUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from CompteClient c where utilisateurCreation.id=:utilisateur");
		query.setParameter("utilisateur", utilisateur.getId());
		
		
		return query.list();
	}

   

}
