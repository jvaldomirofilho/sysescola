
package br.edu.sysescola.dao;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.edu.sysescola.util.HibernateUtil;


@SuppressWarnings("unchecked")
public class DAO {

	private Session session;
	
	private static DAO singleton = new DAO();

	private DAO() {}

	public synchronized static DAO getInstance() {
		if (singleton == null) {
			singleton = new DAO();
		}
		return singleton;
	}

	public <T> T buscaPorID(Class<T> clazz, Serializable id) {
		session = HibernateUtil.getSession();
		return (T)session.load(clazz, id);
		
	}
	
	public void salvar(Object obj) {
		try{
			HibernateUtil.closeSession();
			session = HibernateUtil.getSession();
			session.save(obj);
			session.beginTransaction().commit();
		}catch(HibernateException he){
			he.printStackTrace();
		}
	}
	
	public void editar(Object obj) {
//		HibernateUtil.closeSession();
		session = HibernateUtil.getSession();
		session.update(obj);
		session.beginTransaction().commit();
	}
	
	public void excluir(Object obj) {
		HibernateUtil.closeSession();
	    session = HibernateUtil.getSession();
		session.delete(obj);
		session.beginTransaction().commit();
	}
	
	public <T> List<T> list(Class<?> obj) {
			Session session = HibernateUtil.getSession();

			Criteria crit = session.createCriteria(obj);
			List<T> list = crit.list();
			return list;	
 	}
	 
}