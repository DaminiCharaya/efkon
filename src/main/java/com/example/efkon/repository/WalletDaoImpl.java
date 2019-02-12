package com.example.efkon.repository;

import com.example.efkon.view.TagResponse;
import com.example.efkon.view.WalletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class WalletDaoImpl implements WalletDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WalletResponse> fetchWalletCountGroupByCustomerType() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) count,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].[MD_SM_CUST] sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID where sm_wallet.[WALLET_ID] IS NOT NULL and sm_wallet.[WALLET_ID] != '' group by sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(WalletResponse.class))
                .list();
    }

    @Override
    public List<WalletResponse> fetchWalletCountGroupByCustomerTypeAndSortByMonth() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) count,sm_cust.[CUST_TYPE] customerType,MONTH(sm_wallet.[SM_CUST_ACT_DT]) month FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].[MD_SM_CUST] sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID where sm_wallet.[WALLET_ID] IS NOT NULL and sm_wallet.[WALLET_ID] != '' group by sm_cust.[CUST_TYPE],MONTH(sm_wallet.[SM_CUST_ACT_DT]) order by MONTH(sm_wallet.[SM_CUST_ACT_DT])");
        return query.setResultTransformer(Transformers.aliasToBean(WalletResponse.class))
                .list();
    }

    @Override
    public Integer fetchNoOfWalletByBalance() {

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct account.[WALLET_ID]) FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] account WHERE account.INCR_BALANCE_AMT < 100");
        return (Integer) query.uniqueResult();
    }

    @Override
    public Integer fetchNoOfWalletByBalanceAndByCustomerType(Integer customerType) {

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].MD_SM_CUST sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID  where INCR_BALANCE_AMT < 100 and sm_cust.CUST_TYPE=:CUST_TYPE");
        query.setParameter("CUST_TYPE", customerType);
        return (Integer) query.uniqueResult();
    }


    public List<WalletResponse> fetchNoOfWalletByBalanceAndGroupByCustomerType() {

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) count,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].MD_SM_CUST sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID  where INCR_BALANCE_AMT < 100 group by sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(WalletResponse.class))
                .list();
    }

    @Override
    public Integer fetchNoOfWalletByBalanceAndByCustomerTypeAndByDate(Integer customerType, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct sm_wallet.[WALLET_ID]) FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].MD_SM_CUST sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID  where SM_TOT_AMT < 100 and sm_cust.CUST_TYPE=:CUST_TYPE and MONTH( sm_wallet.TIME_STAMP)='" + month + "' and YEAR( sm_wallet.TIME_STAMP)='" + year + "'");
        query.setParameter("CUST_TYPE", customerType);
        return (Integer) query.uniqueResult();
    }

    @Override
    public List<WalletResponse> fetchNoOfWalletByBalanceAndGroupByCustomerTypeAndByDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct sm_wallet.[WALLET_ID]) count,sm_cust.[CUST_TYPE] customerType  FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].MD_SM_CUST sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID  where SM_TOT_AMT < 100 and  MONTH( sm_wallet.TIME_STAMP)='" + month + "' and YEAR( sm_wallet.TIME_STAMP)='" + year + "' group by sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(WalletResponse.class))
                .list();
    }

    @Override
    public Integer fetchNoOfWalletByCustomerTypeAndByDate(Integer customerType, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].[MD_SM_CUST] sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID where sm_cust.CUST_TYPE=:CUST_TYPE  and sm_wallet.[WALLET_ID] IS NOT NULL and sm_wallet.[WALLET_ID] != '' and MONTH(sm_wallet.SM_CUST_ACT_DT)='" + month + "' and YEAR(sm_wallet.SM_CUST_ACT_DT)='" + year + "'");
        query.setParameter("CUST_TYPE", customerType);
        return (Integer) query.uniqueResult();
    }

    @Override
    public Integer fetchNoOfWalletByBalanceAndByCustomerTypeAndBySameDate(Integer customerType, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Integer day = Cal.get(Calendar.DAY_OF_MONTH);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct sm_wallet.[WALLET_ID]) FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].MD_SM_CUST sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID  where SM_TOT_AMT < 100 and sm_cust.CUST_TYPE=:CUST_TYPE and MONTH(sm_wallet.TIME_STAMP)='" + month + "' and YEAR(sm_wallet.TIME_STAMP)='" + year + "' and DAY(sm_wallet.TIME_STAMP)='" + day + "'");
        query.setParameter("CUST_TYPE", customerType);
        return (Integer) query.uniqueResult();
    }

    public List<WalletResponse> fetchWalletCountByDateAndGroupByCustomerType(String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) count,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].[MD_SM_CUST] sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID where sm_wallet.[WALLET_ID] IS NOT NULL and sm_wallet.[WALLET_ID] != '' and MONTH(sm_wallet.SM_CUST_ACT_DT)='" + month + "' and YEAR(sm_wallet.SM_CUST_ACT_DT)='" + year + "' group by sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(WalletResponse.class))
                .list();
    }

    public List<WalletResponse> fetchWalletCountByDateAndByCustomerType(Integer customerType, String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) count FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].[MD_SM_CUST] sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID where sm_cust.CUST_TYPE=:CUST_TYPE and sm_wallet.[WALLET_ID] IS NOT NULL and sm_wallet.[WALLET_ID] != '' and MONTH(sm_wallet.SM_CUST_ACT_DT)='" + month + "' and YEAR(sm_wallet.SM_CUST_ACT_DT)='" + year + "' ");
        query.setParameter("CUST_TYPE", customerType);
        return query.setResultTransformer(Transformers.aliasToBean(WalletResponse.class))
                .list();
    }

    @Override
    public List<WalletResponse> fetchWalletCountByCustomerType(Integer customerType) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) count FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].[MD_SM_CUST] sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID where  sm_cust.CUST_TYPE=:CUST_TYPE and sm_wallet.[WALLET_ID] IS NOT NULL and sm_wallet.[WALLET_ID] != ''");
        query.setParameter("CUST_TYPE", customerType);
        return query.setResultTransformer(Transformers.aliasToBean(WalletResponse.class))
                .list();
    }


    public List<WalletResponse> fetchWalletCountByCustomerTypeAndSortByMonth(Integer customerType) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) count,MONTH(sm_wallet.[SM_CUST_ACT_DT]) month FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].[MD_SM_CUST] sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID where  sm_cust.CUST_TYPE=:CUST_TYPE and sm_wallet.[WALLET_ID] IS NOT NULL and sm_wallet.[WALLET_ID] != '' group by MONTH(sm_wallet.[SM_CUST_ACT_DT]) order by MONTH(sm_wallet.[SM_CUST_ACT_DT])");
        query.setParameter("CUST_TYPE", customerType);
        return query.setResultTransformer(Transformers.aliasToBean(WalletResponse.class))
                .list();
    }
}


