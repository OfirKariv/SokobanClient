package db;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HbrntDBManager implements DBManager {

	private SessionFactory factory;

	public HbrntDBManager() {

		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		Configuration config = new Configuration();
		config.configure();
		factory = config.buildSessionFactory();

	}

	@Override
	public List<DbObject> getTable(String s) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<DbObject> DBdata = new LinkedList<>();
		tx = session.beginTransaction();
		Query<DbObject> temp = session.createQuery(s);
		tx.commit();

		List<DbObject> fromDB = temp.list();

		for (DbObject db : fromDB) {
			DBdata.add(db);

		}
		session.close();
		return DBdata;

	}

	public int LevelNameToID(String name) {

		if (name.isEmpty())
			return 0;
		Session session = factory.openSession();
		Transaction tx = null;
		List<LevelInfo> LevelInfo = new LinkedList<LevelInfo>();
		tx = session.beginTransaction();
		Query<LevelInfo> temp = session.createQuery("from level where LevelName = '" + name + "'");

		if (temp != null)
			System.out.println("temp!=null");
		tx.commit();

		List<LevelInfo> fromDB = temp.list();

		int x = fromDB.get(0).getLevelID();
		session.close();
		System.out.println(x);
		return x;

	}

	public boolean searchInUserTable(String value) {
		Session session = factory.openSession();
		Transaction tx = null;
		// List<DbObject> DBdata = new LinkedList<>();

		tx = session.beginTransaction();
		List<User> userDB = session.createQuery("from users").list();
		/*
		 * for (Iterator iterator = fromDB.iterator(); iterator.hasNext();) {
		 * 
		 * User db = (User) iterator.next(); if();
		 * 
		 * }
		 */
		for (User us : userDB) {
			if (us.getUsername().equals(value)) {
				session.close();
				return true;
			}

		}
		// System.out.println(DBdata.toString());
		session.close();
		return false;
	}

	public void addUser(String name) {

		if (searchInUserTable(name) == true)
			return;

		User user = new User(name);

		Transaction tx = null;
		Session session = factory.openSession();

		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	public int addLevel(String name) {
		int lvlID = checkName(name);
		if (lvlID != -1)
			return lvlID;

		LevelInfo lvl = new LevelInfo(name);

		Transaction tx = null;
		Session session = factory.openSession();

		try {
			tx = session.beginTransaction();
			lvlID = (Integer) session.save(lvl);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		lvl.setLevelID(lvlID);
		return lvlID;
	}

	@Override
	public int checkName(String name)

	{

		Session session = factory.openSession();
		try {
			Query<LevelInfo> query = session.createQuery("from level");
			List<LevelInfo> list = query.list();

			for (LevelInfo li : list) {
				{
					if (li.getLevelName().equals(name)) {
						int id = li.getLevelID();
						session.close();
						return id;
					}

				}

			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println("do we get here?");
		return -1;
	}

	public void addUserData(String userName, int lvlID, int stepCount, int timer) {
		UserData ud = new UserData(userName, lvlID, stepCount, timer);
		Session session = null;
		Transaction tx = null;
		session = factory.openSession();

		try {
			tx = session.beginTransaction();
			session.save(ud);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public void stop() {
		factory.close();

	}

}
