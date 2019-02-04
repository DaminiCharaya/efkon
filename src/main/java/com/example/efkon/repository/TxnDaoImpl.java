package com.example.efkon.repository;

import com.example.efkon.view.TagResponse;
import com.example.efkon.view.TxnResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Repository
public class TxnDaoImpl implements TxnDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<TxnResponse> fetchCountOfTxnGroupByCardStatusAndDescAndByCreatedDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        SimpleDateFormat changedFormat = new SimpleDateFormat("yyyy-MM");
        String changedDate= changedFormat.format(parsedDate);

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct txn.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc FROM [dbo].[TXN_ISSUER_DETAIL] txn inner join [dbo].[MD_SM_CARD] sm_card on txn.TAG_ID = sm_card.TAG_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where txn.CREATED_AT like '%"+ changedDate +"%' and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC]");
        return query.setResultTransformer(Transformers.aliasToBean(TxnResponse.class))
                .list();
    }


    public Integer fetchAllTxnForDistinctTagsAndByCreatedDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        SimpleDateFormat changedFormat = new SimpleDateFormat("yyyy-MM");
        String changedDate= changedFormat.format(parsedDate);

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct txn.[TAG_ID]) count  FROM [dbo].[TXN_ISSUER_DETAIL] txn where txn.CREATED_AT like '%"+ changedDate+"%' and txn.TAG_ID IS NOT NULL and txn.TAG_ID != ''");
        return (Integer) query.uniqueResult();
    }

    @Override
    public Integer fetchAllTxnForDistinctTags() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count (distinct txn.[TAG_ID]) FROM [dbo].[TXN_ISSUER_DETAIL] txn where txn.TAG_ID IS NOT NULL and txn.TAG_ID != ''");
        return (Integer) query.uniqueResult();
    }

    @Override
    public List<TxnResponse> fetchAllTxnForDistinctTagsAndByCreatedDateAndByCustomerType( Integer customerType,String date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        SimpleDateFormat changedFormat = new SimpleDateFormat("yyyy-MM");
        String changedDate= changedFormat.format(parsedDate);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct txn.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc FROM [dbo].[TXN_ISSUER_DETAIL] txn inner join [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID on txn.TAG_ID = sm_card.TAG_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where txn.CREATED_AT like '%"+ changedDate+"%' and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' and sm_cust.CUST_TYPE=:CUST_TYPE group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC]");
        query.setParameter("CUST_TYPE",customerType);
        return query.setResultTransformer(Transformers.aliasToBean(TxnResponse.class))
                .list();
    }

    @Override
    public List<TxnResponse> fetchAllTxnForDistinctTagsAndByCreatedDateAndGroupByCustomerType(String date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        SimpleDateFormat changedFormat = new SimpleDateFormat("yyyy-MM");
        String changedDate= changedFormat.format(parsedDate);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct txn.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc,sm_cust.[CUST_TYPE] customerType FROM [dbo].[TXN_ISSUER_DETAIL] txn inner join [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID on txn.TAG_ID = sm_card.TAG_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where txn.CREATED_AT like '%"+ changedDate+"%' and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC],sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(TxnResponse.class))
                .list();
    }


    @Override
    public List<?> fetchAllDistinctTxnInSmCard() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct txn.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc FROM [dbo].[TXN_ISSUER_DETAIL] txn inner join [dbo].[MD_SM_CARD] sm_card on txn.TAG_ID = sm_card.TAG_ID  inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS  where sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != ''  group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC]");
        return query.setResultTransformer(Transformers.aliasToBean(TxnResponse.class))
                .list();
    }

    @Override
    public List<?> fetchAllDistinctTxnInSmCardByCustomerType(Integer customerType) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct txn.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc FROM [dbo].[TXN_ISSUER_DETAIL] txn inner join [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID on txn.TAG_ID = sm_card.TAG_ID  inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' and sm_cust.CUST_TYPE=:CUST_TYPE group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC]");
        query.setParameter("CUST_TYPE",customerType);
        return query.setResultTransformer(Transformers.aliasToBean(TxnResponse.class))
                .list();
    }


    @Override
    public List<?> fetchAllDistinctTxnInSmCardGroupByCustomerType() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct txn.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc,sm_cust.[CUST_TYPE] customerType  FROM [dbo].[TXN_ISSUER_DETAIL] txn inner join [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID on txn.TAG_ID = sm_card.TAG_ID  inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != ''  group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC],sm_cust.[CUST_TYPE]");
        return query.setResultTransformer(Transformers.aliasToBean(TxnResponse.class))
                .list();
    }



    @Override
    public List<?> fetchAllDistinctTxnInSmCardForDifferentDates() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct txn.[TAG_ID]) FROM [dbo].[TXN_ISSUER_DETAIL] txn where txn.TAG_ID IS NOT NULL and txn.TAG_ID != '' and (CONVERT(VARCHAR(25), txn.CREATED_AT, 126)  like '2018-10%' or CONVERT(VARCHAR(25), txn.CREATED_AT, 126)  like '2018-11%' or CONVERT(VARCHAR(25), txn.CREATED_AT, 126)  like '2018-12%')");
        return query
                .list();
    }

    @Override
    public List<?> fetchAllDistinctTxnJoinIssuerDetailsAndSmCardForDifferentDates(String firstdate,String seconddate,String thirddate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedFirstDate = sdf.parse(firstdate);
        Date parsedSecondDate = sdf.parse(seconddate);
        Date parsedThirdDate = sdf.parse(thirddate);
        SimpleDateFormat changedFormat = new SimpleDateFormat("yyyy-MM");
        String changedFirstDate= changedFormat.format(parsedFirstDate);
        String changedSecondDate= changedFormat.format(parsedSecondDate);
        String changedThirdDate= changedFormat.format(parsedThirdDate);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct txn.[TAG_ID]) FROM [dbo].[TXN_ISSUER_DETAIL] txn inner join [dbo].[MD_SM_CARD] sm_card on txn.TAG_ID = sm_card.TAG_ID  where txn.TAG_ID IS NOT NULL and txn.TAG_ID != '' and (CONVERT(VARCHAR(25), txn.CREATED_AT, 126)  like '%"+changedFirstDate+"%' or CONVERT(VARCHAR(25), txn.CREATED_AT, 126)  like '%"+changedSecondDate+"%' or CONVERT(VARCHAR(25), txn.CREATED_AT, 126)  like '%"+changedThirdDate+"%')");
        return query
                .list();
    }

    public List<?> fetchAllDistinctTxnJoinIssuerDetailsAndSmCardForDifferentDatesByCustomerType(String firstdate,String seconddate,String thirddate,Integer customerType) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedFirstDate = sdf.parse(firstdate);
        Date parsedSecondDate = sdf.parse(seconddate);
        Date parsedThirdDate = sdf.parse(thirddate);
        SimpleDateFormat changedFormat = new SimpleDateFormat("yyyy-MM");
        String changedFirstDate= changedFormat.format(parsedFirstDate);
        String changedSecondDate= changedFormat.format(parsedSecondDate);
        String changedThirdDate= changedFormat.format(parsedThirdDate);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct txn.[TAG_ID])  FROM [dbo].[TXN_ISSUER_DETAIL] txn inner join [dbo].[MD_SM_CARD] sm_card on txn.TAG_ID = sm_card.TAG_ID inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID  where txn.TAG_ID IS NOT NULL and txn.TAG_ID != '' and sm_cust.CUST_TYPE=:CUST_TYPE and (CONVERT(VARCHAR(25), txn.CREATED_AT, 126)  like '%"+changedFirstDate+"%' or CONVERT(VARCHAR(25), txn.CREATED_AT, 126)  like '%"+changedSecondDate+"%' or CONVERT(VARCHAR(25), txn.CREATED_AT, 126)  like '%"+changedThirdDate+"%')");

        query.setParameter("CUST_TYPE",customerType);
        return query
                .list();
    }
}
