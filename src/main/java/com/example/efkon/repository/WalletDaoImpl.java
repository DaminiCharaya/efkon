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
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    public Integer fetchNoOfWalletByBalance() {

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct account.[WALLET_ID]) FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] account WHERE account.INCR_BALANCE_AMT < 100");
        return(Integer) query.uniqueResult();
    }

    @Override
    public Integer fetchNoOfWalletByBalanceAndByCustomerType(Integer customerType) {

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].MD_SM_CUST sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID  where INCR_BALANCE_AMT < 100 and sm_cust.CUST_TYPE=:CUST_TYPE");
        query.setParameter("CUST_TYPE",customerType);
        return(Integer) query.uniqueResult();
    }

    @Override
    public Integer fetchNoOfWalletByBalanceAndByCustomerTypeAndByDate(Integer customerType,String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        SimpleDateFormat changedFormat = new SimpleDateFormat("yyyy-MM");
        String changedDate= changedFormat.format(parsedDate);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct sm_wallet.[WALLET_ID]) FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].MD_SM_CUST sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID  where SM_TOT_AMT < 100 and sm_cust.CUST_TYPE=:CUST_TYPE and CONVERT(VARCHAR(25), sm_wallet.TIME_STAMP, 126)  like '%"+changedDate+"%'");
        query.setParameter("CUST_TYPE",customerType);
        return(Integer) query.uniqueResult();
    }

    @Override
    public Integer fetchNoOfWalletByCustomerTypeAndByDate(Integer customerType,String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        SimpleDateFormat changedFormat = new SimpleDateFormat("yyyy-MM-dd");
        String changedDate= changedFormat.format(parsedDate);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].[MD_SM_CUST] sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID where sm_cust.CUST_TYPE=:CUST_TYPE  and sm_wallet.[WALLET_ID] IS NOT NULL and sm_wallet.[WALLET_ID] != '' and sm_wallet.SM_CUST_ACT_DT LIKE '%"+changedDate+"%'");
        query.setParameter("CUST_TYPE",customerType);
        return(Integer) query.uniqueResult();
    }
    @Override
    public Integer fetchNoOfWalletByBalanceAndByCustomerTypeAndBySameDate(Integer customerType,String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        SimpleDateFormat changedFormat = new SimpleDateFormat("yyyy-MM-dd");
        String changedDate= changedFormat.format(parsedDate);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct sm_wallet.[WALLET_ID]) FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].MD_SM_CUST sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID  where SM_TOT_AMT < 100 and sm_cust.CUST_TYPE=:CUST_TYPE and CONVERT(VARCHAR(25), sm_wallet.TIME_STAMP, 126)  like '%"+changedDate+"%'");
        query.setParameter("CUST_TYPE",customerType);
        return(Integer) query.uniqueResult();
    }

    public List<WalletResponse> fetchWalletCountByDateAndGroupByCustomerType(String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        SimpleDateFormat changedFormat = new SimpleDateFormat("yyyy-MM");
        String changedDate= changedFormat.format(parsedDate);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) count,sm_cust.[CUST_TYPE] customerType FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].[MD_SM_CUST] sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID where sm_wallet.[WALLET_ID] IS NOT NULL and sm_wallet.[WALLET_ID] != '' and sm_wallet.SM_CUST_ACT_DT LIKE '%"+ changedDate +"%' group by sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(WalletResponse.class))
                .list();
    }

    public List<WalletResponse> fetchWalletCountByDateAndByCustomerType(Integer customerType,String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        SimpleDateFormat changedFormat = new SimpleDateFormat("yyyy-MM");
        String changedDate= changedFormat.format(parsedDate);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) count FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].[MD_SM_CUST] sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID where sm_cust.CUST_TYPE=:CUST_TYPE and sm_wallet.[WALLET_ID] IS NOT NULL and sm_wallet.[WALLET_ID] != '' and sm_wallet.SM_CUST_ACT_DT LIKE '%"+ changedDate +"%'");
        query.setParameter("CUST_TYPE",customerType);
        return query.setResultTransformer(Transformers.aliasToBean(WalletResponse.class))
                .list();
    }

    @Override
    public List<WalletResponse> fetchWalletCountByCustomerType(Integer customerType) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct sm_wallet.[WALLET_ID]) count FROM [dbo].[MD_SM_CUST_GROUP_ACCOUNT] sm_wallet inner join [dbo].[MD_SM_CUST] sm_cust on sm_wallet.SM_CUST_ID = sm_cust.SM_CUST_ID where  sm_cust.CUST_TYPE=:CUST_TYPE and sm_wallet.[WALLET_ID] IS NOT NULL and sm_wallet.[WALLET_ID] != ''");
        query.setParameter("CUST_TYPE",customerType);
        return query.setResultTransformer(Transformers.aliasToBean(WalletResponse.class))
                .list();
    }
}
