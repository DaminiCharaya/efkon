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
import java.util.*;

@Repository
public class TxnDaoImpl implements TxnDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<TxnResponse> fetchCountOfTxnGroupByCardStatusAndDescAndByCreatedDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct txn.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc FROM [dbo].[TXN_ISSUER_DETAIL] txn inner join [dbo].[MD_SM_CARD] sm_card on txn.TAG_ID = sm_card.TAG_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where MONTH(txn.CREATED_AT)='" + month + "' and YEAR(txn.CREATED_AT)='" + year + "' and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC]");
        return query.setResultTransformer(Transformers.aliasToBean(TxnResponse.class))
                .list();
    }


    public Integer fetchAllTxnForDistinctTagsAndByCreatedDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct txn.[TAG_ID]) count  FROM [dbo].[TXN_ISSUER_DETAIL] txn where (MONTH(txn.CREATED_AT)='" + month + "' and YEAR(txn.CREATED_AT)='" + year + "') and txn.TAG_ID IS NOT NULL and txn.TAG_ID != ''");
        return (Integer) query.uniqueResult();
    }

    @Override
    public Integer fetchAllTxnForDistinctTags() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count (distinct txn.[TAG_ID]) FROM [dbo].[TXN_ISSUER_DETAIL] txn where txn.TAG_ID IS NOT NULL and txn.TAG_ID != ''");
        return (Integer) query.uniqueResult();
    }

    @Override
    public List<TxnResponse> fetchAllTxnForDistinctTagsAndByMonth() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count (distinct txn.[TAG_ID]) count,MONTH(txn.[CREATED_AT]) month FROM [dbo].[TXN_ISSUER_DETAIL] txn where txn.TAG_ID IS NOT NULL and txn.TAG_ID != '' group by MONTH(txn.[CREATED_AT]) order by MONTH(txn.[CREATED_AT])");
        return query.setResultTransformer(Transformers.aliasToBean(TxnResponse.class))
                .list();
    }

    @Override
    public List<TxnResponse> fetchAllTxnForDistinctTagsAndByCreatedDateAndByCustomerType(Integer customerType, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct txn.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc FROM [dbo].[TXN_ISSUER_DETAIL] txn inner join [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID on txn.TAG_ID = sm_card.TAG_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where MONTH(txn.CREATED_AT)='" + month + "' and YEAR(txn.CREATED_AT)='" + year + "' and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' and sm_cust.CUST_TYPE=:CUST_TYPE group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC]");
        query.setParameter("CUST_TYPE", customerType);
        return query.setResultTransformer(Transformers.aliasToBean(TxnResponse.class))
                .list();
    }

    @Override
    public List<TxnResponse> fetchAllTxnForDistinctTagsAndByCreatedDateAndGroupByCustomerType(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedDate = sdf.parse(date);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct txn.[TAG_ID]) count,sm_card.[SM_CARD_STATUS] status,card_status.[SM_CARD_DESC] statusDesc,sm_cust.[CUST_TYPE] customerType FROM [dbo].[TXN_ISSUER_DETAIL] txn inner join [dbo].[MD_SM_CARD] sm_card inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID on txn.TAG_ID = sm_card.TAG_ID inner join [dbo].[PT_CARD_STATUS] card_status on sm_card.SM_CARD_STATUS = card_status.SM_CARD_STATUS where MONTH(txn.CREATED_AT)='" + month + "' and YEAR(txn.CREATED_AT)='" + year + "' and sm_card.TAG_ID IS NOT NULL and sm_card.TAG_ID != '' group by sm_card.[SM_CARD_STATUS],card_status.[SM_CARD_DESC],sm_cust.[CUST_TYPE]");
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
        query.setParameter("CUST_TYPE", customerType);
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
    public List<?> fetchTagCount() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("Select tagCount as sequence, count(tagCount) as count from ( Select TAG_ID, count(TAG_ID) as tagCount from ( SELECT  txn.TAG_ID FROM [dbo].[TXN_ISSUER_DETAIL] txn group by  txn.TXN_ID, txn.TAG_ID, txn.PLAZA_ID, txn.READER_TS ) as tag group by tag.TAG_ID ) as countTag group by tagCount");
        return query
                .list();
    }

    @Override
    public List<?> fetchTagCountWithRevenue() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("Select tagCount as sequence, count(tagCount) as count, sum(tagAmount) as revenue from (Select TAG_ID, count(TAG_ID) as tagCount, sum(AMT) as tagAmount from (SELECT txn.TAG_ID, CAST(txn.AMT as DECIMAL) as AMT FROM [dbo].[TXN_ISSUER_DETAIL] txn group by  txn.TXN_ID, txn.TAG_ID, txn.PLAZA_ID, txn.READER_TS, CAST(txn.AMT as DECIMAL)) as tag group by tag.TAG_ID ) as countTag group by tagCount");
        return query
                .list();
    }

    @Override
    public List<?> fetchAllDistinctTxnInSmCardForDifferentDates(String firstdate, String seconddate, String thirddate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedFirstDate = sdf.parse(firstdate);
        Date parsedSecondDate = sdf.parse(seconddate);
        Date parsedThirdDate = sdf.parse(thirddate);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedFirstDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Cal.setTime(parsedSecondDate);
        Integer monthofseconddate = Cal.get(Calendar.MONTH) + 1;
        Integer yearofseconddate = Cal.get(Calendar.YEAR);
        Cal.setTime(parsedThirdDate);
        Integer monthofthirddate = Cal.get(Calendar.MONTH) + 1;
        Integer yearofthirddate = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT  count(distinct txn.[TAG_ID]) FROM [dbo].[TXN_ISSUER_DETAIL] txn where txn.TAG_ID IS NOT NULL and txn.TAG_ID != ''  and ( MONTH(txn.CREATED_AT)='" + month + "' and YEAR(txn.CREATED_AT)='" + year + "' )or MONTH(txn.CREATED_AT)='" + monthofseconddate + "' and YEAR(txn.CREATED_AT)='" + yearofseconddate + "' or  MONTH(txn.CREATED_AT)='" + monthofthirddate + "' and YEAR(txn.CREATED_AT)='" + yearofthirddate + "'");
        return query
                .list();
    }

    @Override
    public List<?> fetchAllDistinctTxnJoinIssuerDetailsAndSmCardForDifferentDates(String firstdate, String seconddate, String thirddate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedFirstDate = sdf.parse(firstdate);
        Date parsedSecondDate = sdf.parse(seconddate);
        Date parsedThirdDate = sdf.parse(thirddate);
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedFirstDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Cal.setTime(parsedSecondDate);
        Integer monthofseconddate = Cal.get(Calendar.MONTH) + 1;
        Integer yearofseconddate = Cal.get(Calendar.YEAR);
        Cal.setTime(parsedThirdDate);
        Integer monthofthirddate = Cal.get(Calendar.MONTH) + 1;
        Integer yearofthirddate = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct txn.[TAG_ID]) FROM [dbo].[TXN_ISSUER_DETAIL] txn inner join [dbo].[MD_SM_CARD] sm_card on txn.TAG_ID = sm_card.TAG_ID  where txn.TAG_ID IS NOT NULL and txn.TAG_ID != '' and ( MONTH(txn.CREATED_AT)='" + month + "' and YEAR(txn.CREATED_AT)='" + year + "' )or  MONTH(txn.CREATED_AT)='" + monthofseconddate + "' and YEAR(txn.CREATED_AT)='" + yearofseconddate + "' or  MONTH(txn.CREATED_AT)='" + monthofthirddate + "' and YEAR(txn.CREATED_AT)='" + yearofthirddate + "')");
        return query
                .list();
    }

    public List<?> fetchAllDistinctTxnJoinIssuerDetailsAndSmCardForDifferentDatesByCustomerType(String firstdate, String seconddate, String thirddate, Integer customerType) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH);
        Date parsedFirstDate = sdf.parse(firstdate);
        Date parsedSecondDate = sdf.parse(seconddate);
        Date parsedThirdDate = sdf.parse(thirddate);
        SimpleDateFormat changedFormat = new SimpleDateFormat("yyyy-MM");
        Calendar Cal = new GregorianCalendar();
        Cal.setTime(parsedFirstDate);
        Integer month = Cal.get(Calendar.MONTH) + 1;
        Integer year = Cal.get(Calendar.YEAR);
        Cal.setTime(parsedSecondDate);
        Integer monthofseconddate = Cal.get(Calendar.MONTH) + 1;
        Integer yearofseconddate = Cal.get(Calendar.YEAR);
        Cal.setTime(parsedThirdDate);
        Integer monthofthirddate = Cal.get(Calendar.MONTH) + 1;
        Integer yearofthirddate = Cal.get(Calendar.YEAR);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT count(distinct txn.[TAG_ID])  FROM [dbo].[TXN_ISSUER_DETAIL] txn inner join [dbo].[MD_SM_CARD] sm_card on txn.TAG_ID = sm_card.TAG_ID inner join [dbo].[MD_SM_CUST] sm_cust on sm_card.SM_CUST_ID = sm_cust.SM_CUST_ID  where txn.TAG_ID IS NOT NULL and txn.TAG_ID != '' and sm_cust.CUST_TYPE=:CUST_TYPE and ( MONTH(txn.CREATED_AT)='" + month + "' and YEAR(txn.CREATED_AT)='" + year + "' )or  MONTH(txn.CREATED_AT)='" + monthofseconddate + "' and YEAR(txn.CREATED_AT)='" + yearofseconddate + "' or  MONTH(txn.CREATED_AT)='" + monthofthirddate + "' and YEAR(txn.CREATED_AT)='" + yearofthirddate + "')");

        query.setParameter("CUST_TYPE", customerType);
        return query
                .list();
    }
}
